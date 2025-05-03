package manager;

import model.Product;
import data.DataUtil;

import java.util.ArrayList;

public class ProductManager {
    private ArrayList<Product> products;

    public ProductManager() {
        products = DataUtil.loadProducts();
        // mock data
        if (products.isEmpty()) {
            addProduct("Laptop", 999.99, 10);
            addProduct("Phone", 499.99, 5);
            addProduct("Headphones", 199.99, 7);
            addProduct("Monitor", 299.99, 3);
        }
    }

    public void addProduct(String name, double price, int quantity) {
        products.add(new Product(name, price, quantity));
        DataUtil.saveProducts(products);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean reduceQuantity(String productName) {
        for (Product p : products) {
            if (p.getName().equals(productName) && p.getQuantity() > 0) {
                p.setQuantity(p.getQuantity() - 1);
                DataUtil.saveProducts(products);
                return true;
            }
        }
        return false;
    }

    public boolean increaseQuantity(String productName) {
        for (Product p : products) {
            if (p.getName().equals(productName)) {
                p.setQuantity(p.getQuantity() + 1);
                DataUtil.saveProducts(products);
                return true;
            }
        }
        return false;
    }

    public void reload() {
        products = DataUtil.loadProducts();

    }

}
