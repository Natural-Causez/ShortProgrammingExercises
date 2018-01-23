//Product object class to represent a product in the shop that a customer can browse and buy.

public class Product {
  private String name;
  private int price;

  public Product (String n, int p) {
    name = n;
    price = p;
  }//END Constructor

  public int getPrice() {
    return price;
  }//END getPrice

  public String getName() {
    return name;
  }//END getName

  public String toString() {
    return "Product[name=" + name + ", price=" + price +  "]";
  }//END toString
}//END class Product
