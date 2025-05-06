package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Profile implements Serializable {
    private String username;
    private String name;
    private String phoneNumber;
    private String password;
    private Map<String, Integer> purchasedProducts;

    public Profile(String username, String name, String phoneNumber, String password) {
        this.username = username;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.purchasedProducts = new HashMap<>();
    }

    public Profile(String username, String name, String phoneNumber, String password,
            Map<String, Integer> purchasedProducts) {
        this.username = username;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.purchasedProducts = purchasedProducts;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addPurchasedProduct(Product product) {
        String name = product.getName();
        purchasedProducts.put(name, purchasedProducts.getOrDefault(name, 0) + 1);
    }

    public Map<String, Integer> getPurchasedProducts() {
        return purchasedProducts;
    }

    public boolean removePurchasedProduct(Product product) {
        String name = product.getName();
        int current = purchasedProducts.getOrDefault(name, 0);
        if (current > 0) {
            if (current == 1) {
                purchasedProducts.remove(name);
            } else {
                purchasedProducts.put(name, current - 1);
            }
            return true;
        }
        return false;
    }

    public int getQuantityPurchased(String productName) {
        return purchasedProducts.getOrDefault(productName, 0);
    }
}
