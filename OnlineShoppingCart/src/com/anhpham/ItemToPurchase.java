package com.anhpham;

public class ItemToPurchase {
    private String itemName;
    private String itemDescription;
    private int itemQuantity = 0;
    private int itemPrice;

    public ItemToPurchase() {
        this.itemDescription = "none";
    }

    public ItemToPurchase(String itemName, String itemDescription, int itemPrice, int itemQuantity) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public String getName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return itemQuantity;
    }

    public void setQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getPrice() {
        return itemPrice;
    }

    public void setPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getDescription() {
        return itemDescription;
    }

    public void setDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * Outputs the item name followed by
     * the quantity, price, and subtotal
     */
    public void printItemCost(){
        // Expected print:
        // Bottled Water 10 @ $1 = $10
        System.out.println(itemName +" " + itemQuantity +" @ $" + itemPrice +" = $" + (itemPrice*itemQuantity) +"");
    }

    /**
     * Outputs the item's
     * name, description
     */
    public void printItemDescription(){
        // Expected print:
        // Bottled Water: Deer Park, 12 oz
        System.out.println(itemName +": "+ itemDescription);
    }
}