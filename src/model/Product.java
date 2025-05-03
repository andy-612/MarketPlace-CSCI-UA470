package model;

import java.util.ArrayList;
import java.util.List;

import data.DataUtil;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private List<String> reviews = new ArrayList<>();

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addReview(String review) {
        reviews.add(review);
    }

    public List<String> getReviews() {
        return reviews;
    }

    public String getLatestReview() {
        if (reviews.isEmpty())
            return "";
        return reviews.get(reviews.size() - 1);
    }

    public String getAllReviewsAsString() {
        return String.join(" | ", reviews);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Product product = (Product) obj;
        return name.equals(product.name);
    }
}
