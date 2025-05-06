package model;

import java.util.*;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private int unitsSold;
    private double revenue;
    private List<String> reviews = new ArrayList<>();

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.unitsSold = 0;
        this.revenue = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double p) {
        price = p;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int q) {
        quantity = q;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public double getRevenue() {
        return revenue;
    }

    public List<String> getReviews() {
        return Collections.unmodifiableList(reviews);
    }

    public String getLatestReview() {
        if (reviews.isEmpty()) {
            return "";
        } else {
            String temp = reviews.get(reviews.size() - 1);
            temp = temp.replaceAll("<p>", "");
            return temp;
        }
    }

    public void recordSale() {
        quantity--;
        unitsSold++;
        revenue += price;
    }

    public void recordReturn() {
        quantity++;
        if (unitsSold > 0) {
            unitsSold--;
            revenue -= price;
        }
    }

    public void addReview(String review) {
        reviews.add("<p>Buyer: " + review + "<p>");
    }

    public void addResponse(String response, String comment) {
        if (!reviews.isEmpty()) {
            int index = reviews.indexOf(comment);
            String temp = comment + "<p>Seller: " + response + "<p>";
            reviews.set(index, temp);
        }
    }

    public void addReviewRaw(String review) {
        this.reviews.add(review);
    }
}
