package com.tarde.data;

public class Item {
    private String itemName;
    private String itemValue;
    private String code;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override public String toString() {
        return "Item{" +
        "itemName='" + itemName + '\'' +
        ", itemValue='" + itemValue + '\'' +
        ", code='" + code + '\'' +
        '}';
    }
}
