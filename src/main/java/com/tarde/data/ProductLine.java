package com.tarde.data;

import java.math.BigDecimal;
import java.util.List;

public class ProductLine {
    private String name;
    private Integer quantity;
    private BigDecimal amount;

    private ShoppingCart shoppingCart;

    private List<Item> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override public String toString() {
        return "ProductLine{" +
        "name='" + name + '\'' +
        ", quantity=" + quantity +
        ", amount=" + amount +
        ", items=" + items +
        '}';
    }
}
