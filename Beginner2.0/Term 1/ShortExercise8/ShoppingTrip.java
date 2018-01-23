//Program to simulate a customer (or pirate) browsing and purchasing products from a shop using gold coins.

import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingTrip {

  public static void main(String[] args) {
    Product product1 = new Product("Diamond", 40);
    Product product2 = new Product("Crown Jewels", 100);
    Product product3 = new Product("Silver Locket", 60);
    ArrayList<Product> productList = new ArrayList<Product>();

    productList.add(product1);
    productList.add(product2);
    productList.add(product3);

    //System.out.println(product1);
    //System.out.println(product2);
    //System.out.println(product3);

    Shop shop1 = new Shop("Hidden Hideaway", 125, productList);
    //System.out.println(shop1);

    Customer customer1 = new Customer("BlackBeard", 100);
    //System.out.println(customer1.printCustomerStatus());

    System.out.println("Welcome to " + shop1.getName() + ", " + customer1.getName() + "!");
    System.out.println("Enter 'add product' to add a product to your shopping basket,");
    System.out.println("Enter 'remove product' to remove a product from your shopping basket,");
    System.out.println("Enter 'purchase' to purchase the products currently in your shopping basket.");

    String userInp = "";
    Scanner scan = new Scanner(System.in);
    Product tempProduct;

    while (!userInp.equals("exit")) {
      //Shop logic
      System.out.println("Products in stock:");
      for (Product p : shop1.getProducts()) {
        System.out.println(p);
      }//END for-each

      System.out.println(customer1.printInventory());

      System.out.println("What would you like to do? ('add product', 'remove product', 'purchase' or 'exit')");
      userInp = scan.nextLine();


      if (userInp.equals("add product")) {
        System.out.println("Enter the name of the product you would like to add to your shopping basket: ");
        tempProduct = shop1.searchProduct(scan.nextLine());

        customer1.addToShoppingBasket(tempProduct);

        //Remove from shelf so cannot be bought again
        shop1.removeProduct(tempProduct);
      }//END if


      if (userInp.equals("remove product")) {
        System.out.println("Enter the name of the product you would like to remove from your shopping basket: ");
        tempProduct = customer1.searchShoppingBasket(scan.nextLine());

        customer1.removeFromShoppingBasket(tempProduct);

        //Place back on shop shelf to be bought again
        shop1.addProduct(tempProduct);
      }//END if

      if (userInp.equals("purchase")) {
        customer1.purchaseProducts(shop1);
      }//END if
    }//END while

  }//END main

}//END class ShoppingTrip
