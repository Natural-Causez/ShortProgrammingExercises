//Shop object class which can store a unique set of products.
//Keeps track of total previous customer purchases and current number of gold coins.

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.TreeMap;

public class Shop {
  private String name;
  private ArrayList<Product> products = new ArrayList<Product>();
  private ArrayList<GoldCoin> coinBox = new ArrayList<GoldCoin>();
  private TreeMap<String, Integer> customerTotalSpend = new TreeMap<String, Integer>();

  public Shop(String n, int coins, ArrayList<Product> productList) {
    name = n;

    //Populate coinBox
    for (int i = 0; i < coins; i++) {
      addGoldCoin(new GoldCoin());
    }//END for

    products = productList;
  }//END Constructor

  public void addProduct(Product p) {
    if (p != null) {
      products.add(p);
    }//END if
  }//END addProduct

  public int removeProduct(Product p) {
    try {
      if (p != null) {
        products.remove(p);
      }//END if
      return 1;
    }//END try
    catch (Exception ex) {
      return -1;
    }//END catch

  }//END removeProduct

  public Product searchProduct(String name) {
    for (Product p : products) {
      if (p.getName().equalsIgnoreCase(name)) {
        return p;
      }//END if

    }//END for-each

    System.out.println("Product not found in shop stock!");
    return null;
  }//END searchProduct

  public void addGoldCoin(GoldCoin coin) {
    coinBox.add(coin);
  }//END addCoin

  public void updateTotalSpend(Customer c, int amount) {
    customerTotalSpend.put(c.getName(), amount);
  }//END updateTotalSpend

  public String toString() {
    return "Shop[name=" + name + ", products=" + products + ", coins="
        + coinBox.size() + "]";
  }//END toString

  public String getName() {
    return name;
  }//END getName

  public ArrayList<Product> getProducts() {
    return products;
  }//END getProducts
}//END class Shop
