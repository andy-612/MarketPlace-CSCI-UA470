package manager;

import model.Product;

import java.util.ArrayList;
import model.Product;
import model.Profile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductManager {
    private static final String DATA_DIR     = "data";
    private static final String PRODUCT_FILE = DATA_DIR + File.separator + "products.txt";
    private ArrayList<Product> products;



    static {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            new File(PRODUCT_FILE).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public ProductManager() {
        products = loadProducts();
    }


    public void saveProducts(ArrayList<Product> products) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PRODUCT_FILE))) {
            for (Product p : products) {
                String revs = p.getReviews().stream()
                    .map(r -> r.replace("\n", " "))        
                    .collect(Collectors.joining(";"));     
    
                writer.printf("%s,%.2f,%d,%d,%.2f,%s%n",
                    p.getName(),
                    p.getPrice(),
                    p.getQuantity(),
                    p.getUnitsSold(),
                    p.getRevenue(),
                    revs
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Product> loadProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 6);
                String name    = parts[0];
                double price   = Double.parseDouble(parts[1]);
                int qty        = Integer.parseInt(parts[2]);
                int sold       = Integer.parseInt(parts[3]);
                ArrayList<String> revs = new ArrayList<>();
                if (parts.length == 6 && !parts[5].isEmpty()) {
                    revs = new ArrayList<>(Arrays.asList(parts[5].split(";")));
                }

                Product p = new Product(name, price, qty);
                for (int i = 0; i < sold; i++) p.recordSale();
                p.setQuantity(qty);
                for (String r : revs) p.addReviewRaw(r);

                products.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void addProduct(String name, double price, int quantity) {
        products.add(new Product(name, price, quantity));
        saveProducts(products);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean reduceQuantity(String productName) {
        for (Product p : products) {
            if (p.getName().equals(productName) && p.getQuantity() > 0) {
                p.recordSale();
                saveProducts(products);
                return true;
            }
        }
        return false;
    }

    public boolean increaseQuantity(String productName) {
        for (Product p : products) {
            if (p.getName().equals(productName)) {
                p.recordReturn();
                saveProducts(products);
                return true;
            }
        }
        return false;
    }

    public void reload() {
        products = loadProducts();

    }

}
