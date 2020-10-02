package com.anhpham;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Anh Pham
 */
public class ShoppingCart {
    private final String customerName;
    private final String currentDate;
    private ArrayList<ItemToPurchase> cartItems = new ArrayList<>();

    public ShoppingCart() {
        this.customerName = "none";
        this.currentDate = "January 1, 2016";
    }

    public ShoppingCart(String customerName, String currentDate) {
        this.customerName = customerName;
        this.currentDate = currentDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDate() {
        return currentDate;
    }

    /**
     * Adds an item to cartItems array.
     * @param item the item will be put on cart
     */
    public void addItem(ItemToPurchase item){
        cartItems.add(item);
    }

    /**
     * Removes item from cartItems array.
     * @param itemName the item will be removed out of the cart
     */
    public void removeItem(String itemName){
        Iterator<ItemToPurchase> items = cartItems.iterator();
        boolean hasItem = false;

        // Find the item by name
        // & Removes item from cartItems array.
        // Has a string (an item's name) parameter. Does not return anything.
        while (items.hasNext()) {
            ItemToPurchase item = items.next();

            if (item.getName().equals(itemName)){
                items.remove();
                hasItem = true;
            }
        }

        // If item name cannot be found, output this message:
        //  Item not found in cart. Nothing removed.
        if (!hasItem) {
            System.out.println("Item not found in cart. Nothing removed.");
        }
    }

    /**
     * Modifies an item's description, price, and/or quantity.
     * @param itemToPurchase the item that will be modified
     */
    public void modifyItem(ItemToPurchase itemToPurchase){
        // If item can be found (by name) in cart,
        // check if parameter has default values for description, price, and quantity.
        Iterator<ItemToPurchase> items = cartItems.iterator();
        boolean hasItem = false;

        // If item can be found in cart, modify item in cart.
        while (items.hasNext()) {
            ItemToPurchase item = items.next();

            if (item.getName().equals(itemToPurchase.getName())){
                String description = !itemToPurchase.getDescription().isEmpty()
                        ? itemToPurchase.getDescription() : item.getDescription();
                int qty = itemToPurchase.getQuantity() != 0
                        ? itemToPurchase.getQuantity() : item.getQuantity();
                int price = itemToPurchase.getPrice() != 0
                        ? itemToPurchase.getPrice() : item.getPrice();

                item.setDescription(description);
                item.setQuantity(qty);
                item.setPrice(price);

                hasItem = true;
            }
        }

        // If item cannot be found (by name) in cart, output this message:
        //  Item not found in cart. Nothing modified.
        if (!hasItem) {
            System.out.println("Item not found in cart. Nothing modified.");
        }
    }

    /**
     *
     * @return quantity of all items in cart
     */
    public int getNumItemsInCart(){
        int items = 0;

        for (ItemToPurchase cartItem : cartItems) {
            items += cartItem.getQuantity();
        }

        return items;
    }

    /**
     * Determines and returns the total cost of items in the cart
     * @return total cost of items in the cart
     */
    public int getCostOfCart(){
        int total = 0;

        for (int i = 0; i < cartItems.size(); i++) {
            total += (cartItems.get(i).getPrice() * cartItems.get(i).getQuantity());    // Price * Quantity
        }

        return total;
    }

    /**
     * Outputs total of objects in cart.
     */
    public void printTotal(){
        System.out.println(customerName + "'s Shopping Cart - " + currentDate);
        System.out.println("Number of Items: " + getNumItemsInCart());
        System.out.println();

        // If cart is empty, output this message: SHOPPING CART IS EMPTY
        // If not, print items in the cart
        if (cartItems.size() == 0) {
            System.out.println("SHOPPING CART IS EMPTY");
        } else {
            for (int i = 0; i < cartItems.size(); i++) {
                cartItems.get(i).printItemCost();
            }
        }

        System.out.println();
        System.out.println("Total: $" + getCostOfCart());
    }

    /**
     * Print the description all the items in cart
     */
    public void printDescriptions(){
        System.out.println(customerName +"'s Shopping Cart - "+ currentDate +"");
        System.out.println();
        System.out.println("Item Descriptions");

        for (ItemToPurchase cartItem : cartItems) {
            cartItem.printItemDescription();
        }
    }
}