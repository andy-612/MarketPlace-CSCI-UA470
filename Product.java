import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product implements Serializable {
    private static List<Product> products = new ArrayList<>();
    public  static void addProduct(Product p)         { products.add(p); }
    public  static void removeProduct(Product p)      { products.remove(p); }
    public  static List<Product> listProducts()       { return Collections.unmodifiableList(products); }

    private String name;
    private String price;
    private String quantity;        
    private String unitsSold;        
    private String revenue;        
    private List<String> comments = new ArrayList<>();

    public Product(String name, String price, String quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getName()          { return name; }
    public void   setName(String n)  { name = n; }
    public String getPrice()         { return price; }
    public void   setPrice(String p) { price = p; }
    public String    getQuantity()      { return quantity; }
    public void   setQuantity(String q) { quantity = q; }
    public String    getUnitsSold()     { return unitsSold; }
    public String getRevenue()       { return revenue; }
    public List<String> getComments(){ return comments; }
    public void addComment(String c){ comments.add(c); }
    
}

