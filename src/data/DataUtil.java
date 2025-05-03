package data;

import model.Product;
import model.Profile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DataUtil {
    private static final String DATA_DIR     = "data";
    private static final String PRODUCT_FILE = DATA_DIR + File.separator + "products.txt";
    private static final String PROFILE_FILE = DATA_DIR + File.separator + "profiles.txt";


public static void saveProducts(ArrayList<Product> products) {
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

    public static ArrayList<Product> loadProducts() {
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

    public static void saveProfiles(Map<String, Profile> profiles) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PROFILE_FILE))) {
            for (Profile p : profiles.values()) {
                writer.println(p.getUsername() + "," + p.getName() + "," + p.getPhoneNumber() + "," + p.getPassword());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, Profile> loadProfiles() {
        HashMap<String, Profile> profiles = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PROFILE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    profiles.put(parts[0], new Profile(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profiles;
    }
}
