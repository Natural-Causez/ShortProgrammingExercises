//Customer object class storing arrays of current basket, already owned products and a purse containing gold coins.

import java.util.ArrayList;

public class Customer {
  private String name;
  private ArrayList<Product> shoppingBasket = new ArrayList<Product>();
  private ArrayList<Product> ownedProducts = new ArrayList<Product>();
  private ArrayList<GoldCoin> purse = new ArrayList<GoldCoin>();

  public Customer (String n, int coins) {
    name = n;

    //Populate purse
    for (int i = 0; i < coins; i++) {
      purse.add(new GoldCoin());
    }//END for
  }//END constructor

  public String getName() {
    return name;
  }//END getName

  public void addToShoppingBasket(Product p) {
    if (p != null) {
      shoppingBasket.add(p);
    }//END if
  }//END addToShoppingBasket

  public int removeFromShoppingBasket(Product p) {
    try {
      shoppingBasket.remove(p);
      return 1;
    }//END try
    catch (Exception ex) {
      return -1;
    }//END catch
  }//END removeFromShoppingBasket

  public Product searchShoppingBasket(String name) {
    for (Product p : shoppingBasket) {
      if (p.getName().equals(name)) {
        return p;
      }//END if
    }//END for-each

    System.out.println("Product not found in shopping basket!");
    return null;
  }//END searchShoppingBasket

  public void addOwnedProduct(Product p) {
    ownedProducts.add(p);
  }//END addOwnedProduct

  public void addCoin(GoldCoin coin) {
    purse.add(coin);
  }//END addCoin

  public int purchaseProducts(Shop shop) {
    //Calculate total cost of shopping basket
    if (shoppingBasket.size() == 0) {
      System.out.println("Shopping basket is empty!");
      return -1;
    }//END if

    int totalCost = 0;
    for (Product p : shoppingBasket) {
      totalCost += p.getPrice();
    }//END for-each

    if (totalCost > purse.size()) {
      System.out.println("Not enough coins!");
      return -1;
    }//END if

    //GoldCoins from purse to coinBox
    for (int i = 0; i < totalCost; i++) {
      shop.addGoldCoin(purse.get(0));
      purse.remove(0);
    }//END for

    //Transfer products from basket to ownedProducts
    for (Product p : shoppingBasket) {
      ownedProducts.add(p);
    }//END for-each

    //Then clear the shoppingBasket
    shoppingBasket.clear();

    //Update customerTotalSpend
    shop.updateTotalSpend(this, totalCost);

    return 1;
  }//END purchaseProducts

  public String printCustomerStatus() {
    return "Customer[name=" + name + ", coins=" + purse.size() + "]";
  }//END printInventory

  public String printInventory() {
    String basket = "";
    int i = 1;
    for (Product p : shoppingBasket) {
      basket += p.getName();
      if (i < shoppingBasket.size()) {
        basket += ",";
      }//END if
      i++;
    }//END for-each

    return "Customer[shoppingBasket=" + basket + ", coins=" + purse.size() + "]";
  }//END printInventory

  public String toString() {
    return "Customer[name=" + name + ", shoppingBasket=" + shoppingBasket + ", ownedProducts=" +
          ownedProducts + ", purse=" + purse.size() + "]";
  }//END toString

}//END class Customer
