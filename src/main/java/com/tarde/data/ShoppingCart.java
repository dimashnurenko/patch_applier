package com.tarde.data;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCart {
    private String shopCartName;
    private BigDecimal totalAmount;
    private String customerEmail;

    private List<ProductLine> productLines;

    public String getShopCartName() {
        return shopCartName;
    }

    public void setShopCartName(String shopCartName) {
        this.shopCartName = shopCartName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<ProductLine> getProductLines() {
        return productLines;
    }

    public void setProductLines(List<ProductLine> productLines) {
        this.productLines = productLines;
    }

    @Override public String toString() {
        return "ShoppingCart{" +
        "shopCartName='" + shopCartName + '\'' +
        ", totalAmount=" + totalAmount +
        ", customerEmail='" + customerEmail + '\'' +
        ", productLines=" + productLines +
        '}';
    }
}
