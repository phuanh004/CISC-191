package com.anhpham;

import java.util.Scanner;

/**
 * @author Anh Pham
 * @version 1.0.0
 */
public class ShoppingCartManager {

    private static Scanner scanner;

    public static void main(String[] args) {
        String customerName;
        String todayDate;

        scanner = new Scanner(System.in);

        System.out.println("Enter Customer's Name:");
        customerName = scanner.nextLine();

        System.out.println("Enter Today's Date:");
        todayDate = scanner.nextLine();


        ShoppingCart shoppingCart = new ShoppingCart(customerName, todayDate);

        System.out.println();
        System.out.println("Customer Name: " + shoppingCart.getCustomerName());
        System.out.println("Today's Date: " + shoppingCart.getDate());

        while (true) {
            printMenu(shoppingCart);
        }
    }

    /**
     * Print out the menu
     * Handling user's option
     * @param shoppingCart user's cart
     */
    protected static void printMenu(ShoppingCart shoppingCart) {
        char userOption;

        System.out.println();
        System.out.println("MENU\n" +
                "a - Add item to cart\n" +
                "d - Remove item from cart\n" +
                "c - Change item quantity\n" +
                "i - Output items' descriptions\n" +
                "o - Output shopping cart\n" +
                "q - Quit\n" +
                "\n" +
                "Choose an option:");

        userOption = scanner.next().charAt(0);

        char validOption = optionsPromptTilRight(userOption);

        switch (validOption) {
            case 'a':
                addItemToCart(shoppingCart);
                break;
            case 'd':
                removeItemFromCart(shoppingCart);
                break;
            case 'c':
                changeItemQuantity(shoppingCart);
                break;
            case 'i':
                outputItemsDescription(shoppingCart);
                break;
            case 'o':
                outputShoppingCart(shoppingCart);
                break;
            case 'q':
                System.exit(0);
                break;
            default:
                break;
        }
    }

    /**
     * Add an item to the cart function
     * @param shoppingCart user's cart
     */
    protected static void addItemToCart(ShoppingCart shoppingCart) {
        String name, description;
        int price, quantity;

        System.out.println("ADD ITEM TO CART");

        System.out.println("Enter the item name:");
        name = scanner.nextLine();
        name = checkEmptyStringInScanner(name);

        System.out.println("Enter the item description:");
        description = scanner.nextLine();

        System.out.println("Enter the item price:");
        price = scanner.nextInt();

        System.out.println("Enter the item quantity:");
        quantity = scanner.nextInt();

        ItemToPurchase itemToPurchase = new ItemToPurchase(name, description, price, quantity);
        shoppingCart.addItem(itemToPurchase);
    }

    /**
     * Remove an item from the cart by name
     * @param shoppingCart user's cart
     */
    protected static void removeItemFromCart(ShoppingCart shoppingCart) {
        String name;

        System.out.println("REMOVE ITEM FROM CART");

        System.out.println("Enter name of item to remove:");
        name = scanner.nextLine();
        name = checkEmptyStringInScanner(name);

        shoppingCart.removeItem(name);
    }

    /**
     * Modify the an item in user's cart by name
     * @param shoppingCart user's cart
     */
    protected static void changeItemQuantity(ShoppingCart shoppingCart) {
        String name;
        int qty;

        System.out.println("CHANGE ITEM QUANTITY");

        System.out.println("Enter the item name:");
        name = scanner.nextLine();
        name = checkEmptyStringInScanner(name);

        System.out.println("Enter the new quantity:");
        qty = scanner.nextInt();

        ItemToPurchase item = new ItemToPurchase();
        item.setItemName(name);
        item.setQuantity(qty);

        shoppingCart.modifyItem(item);
    }

    /**
     * Print out all items in the user's cart with the total price
     * @param shoppingCart user's cart
     */
    protected static void outputShoppingCart(ShoppingCart shoppingCart) {
        System.out.println("OUTPUT SHOPPING CART");
        shoppingCart.printTotal();
    }

    /**
     * Print out all items in the user's cart with description
     * @param shoppingCart user's cart
     */
    protected static void outputItemsDescription(ShoppingCart shoppingCart) {
        System.out.println("OUTPUT ITEMS' DESCRIPTIONS");
        shoppingCart.printDescriptions();
    }

    /**
     * Recursion validate user's option
     * Prompt "Choose an option:" until user input a correct option
     * @param userOption user's option
     * @return the first character of the correct option
     */
    protected static char optionsPromptTilRight(char userOption){
        final char[] validChars = {'a', 'd', 'c', 'i', 'o', 'q'};
        char option = userOption;

        for (int i = 0; i < validChars.length; i++) {
            if (userOption == validChars[i]) {
                option = validChars[i];
                return option;
            }
        }

        System.out.println("Choose an option:");
        return optionsPromptTilRight(scanner.next().charAt(0));
    }

    /**
     * Check empty value, which is generated by println
     * and skip scan that line if it empty
     * @param field the value from the Scanner
     * @return not an empty value
     */
    protected static String checkEmptyStringInScanner(String field) {
        if (field.isEmpty()){
            return scanner.nextLine();
        } else {
            return field;
        }
    }

}