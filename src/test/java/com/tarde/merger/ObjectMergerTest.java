package com.tarde.merger;

import com.tarde.data.Item;
import com.tarde.data.ProductLine;
import com.tarde.data.ShoppingCart;
import org.junit.Test;

import java.math.BigDecimal;

import static com.tarde.merger.ObjectMerger.mergerOf;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ObjectMergerTest {

    @Test
    public void simpleStructureShouldBeMerged() {
        ShoppingCart source = new ShoppingCart();
        source.setShopCartName("new name");

        ShoppingCart target = DataProvider.target();
        mergerOf(source, target).merge();

        assertThat(target.getShopCartName(), is(equalTo("new name")));
    }

    @Test
    public void innerObjectShouldBePatchedFirstLevelOfNesting() {
        ProductLine productLine = new ProductLine();
        productLine.setQuantity(222);

        ShoppingCart source = new ShoppingCart();
        source.setProductLines(singletonList(productLine));

        ShoppingCart target = DataProvider.target();
        mergerOf(source, target).merge();

        assertThat(target.getProductLines().get(0).getQuantity(), is(equalTo(222)));
    }

    @Test
    public void innerObjectShouldBePatchedFirstLevelOfNesting2() {
        ProductLine productLine = new ProductLine();
        productLine.setAmount(new BigDecimal(22.22).setScale(2, HALF_UP));

        ShoppingCart source = new ShoppingCart();
        source.setProductLines(singletonList(productLine));

        ShoppingCart target = DataProvider.target();
        mergerOf(source, target).merge();

        assertThat(target.getProductLines().get(0).getAmount(), is(equalTo(new BigDecimal(22.22).setScale(2, HALF_UP))));
    }

    @Test
    public void secondElementInNestedCollectionShouldBeUpdated() {
        ProductLine productLine = new ProductLine();
        productLine.setAmount(new BigDecimal(22.22).setScale(2, HALF_UP));

        ShoppingCart source = new ShoppingCart();
        source.setProductLines(asList(new ProductLine(), productLine));

        ShoppingCart target = DataProvider.target();
        mergerOf(source, target).merge();

        assertThat(target.getProductLines().get(1).getAmount(), is(equalTo(new BigDecimal(22.22).setScale(2, HALF_UP))));
    }

    @Test
    public void thirdElementInThirdLevelNestedCollectionShouldBePartiallyUpdated() {
        Item item = new Item();
        item.setItemName("name item updated");

        ProductLine productLine = new ProductLine();
        productLine.setItems(asList(new Item(), new Item(), item));

        ShoppingCart source = new ShoppingCart();
        source.setProductLines(asList(new ProductLine(), productLine));

        ShoppingCart target = DataProvider.target();
        mergerOf(source, target).merge();

        assertThat(target.getProductLines().get(1).getItems().get(2).getItemName(), is(equalTo("name item updated")));
    }

    @Test
    public void infiniteLoopShouldBePrevented() {
        ShoppingCart source = new ShoppingCart();
        ProductLine line = new ProductLine();
        line.setName("product line cyclic dependency prevented");
        line.setShoppingCart(source);

        source.setProductLines(singletonList(line));

        ShoppingCart target = DataProvider.target();
        mergerOf(source, target).merge();

        assertThat(target.getProductLines().get(0).getName(), is(equalTo("product line cyclic dependency prevented")));
    }
}
