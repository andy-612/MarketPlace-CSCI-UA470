package data;

import model.Product;
import model.Profile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataUtil {
    private static final String PRODUCT_FILE = "data/products.txt";
    private static final String PROFILE_FILE = "data/profiles.txt";

    public static void saveProducts(ArrayList<Product> products) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PRODUCT_FILE))) {
            for (Product p : products) {
                writer.println(p.getName() + "," + p.getPrice() + "," + p.getQuantity());
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
                String[] parts = line.split(",");
                products.add(new Product(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2])));
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
