package com.tarde.merger;

import com.tarde.data.Item;
import com.tarde.data.ProductLine;
import com.tarde.data.ShoppingCart;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static java.util.Arrays.asList;

public class DataProvider {

    static ShoppingCart target() {
        ShoppingCart shoppingCart = new ShoppingCart();

        Item other1 = new Item();
        other1.setCode("code1");
        other1.setItemName("code1_name1");
        other1.setItemValue("code1_value1");
        Item other2 = new Item();
        other2.setCode("code1");
        other2.setItemName("code1_name1");
        other2.setItemValue("code1_value1");
        Item other3 = new Item();
        other3.setCode("code1");
        other3.setItemName("code1_name1");
        other3.setItemValue("code1_value1");
        Item other4 = new Item();
        other4.setCode("code1");
        other4.setItemName("code1_name1");
        other4.setItemValue("code1_value1");
        Item other5 = new Item();
        other5.setCode("code1");
        other5.setItemName("code1_name1");
        other5.setItemValue("code1_value1");

        Item bigBrother1 = new Item();
        bigBrother1.setCode("code1");
        bigBrother1.setItemName("code1_name1");
        bigBrother1.setItemValue("code1_value1");

        Item bigBrother2 = new Item();
        bigBrother2.setCode("code1");
        bigBrother2.setItemName("code1_name1");
        bigBrother2.setItemValue("code1_value1");

        ProductLine other = new ProductLine();
        other.setName("other new line");
        other.setQuantity(5);
        other.setAmount(new BigDecimal(0.05).setScale(2, HALF_UP));
        other.setItems(asList(other1, other2, other3, other4, other5));
        other.setShoppingCart(shoppingCart);

        ProductLine bigBrother = new ProductLine();
        bigBrother.setName("big brothers new album");
        bigBrother.setQuantity(2);
        bigBrother.setAmount(new BigDecimal(200));
        bigBrother.setItems(asList(bigBrother1, bigBrother2));
        bigBrother.setShoppingCart(shoppingCart);

        shoppingCart.setCustomerEmail("customer@email.com");
        shoppingCart.setShopCartName("shop_cart_name");
        shoppingCart.setTotalAmount(new BigDecimal(200.25).setScale(2, HALF_UP));
        shoppingCart.setProductLines(asList(bigBrother, other));

        return shoppingCart;
    }
}
