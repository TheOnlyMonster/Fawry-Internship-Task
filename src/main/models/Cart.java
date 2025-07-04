package main.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;
    
    public Cart() {
        this.items = new ArrayList<>();
    }
    
    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }
        
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                throw new IllegalArgumentException("Product already in cart");
            }
        }
        
        items.add(new CartItem(product, quantity));
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public double getSubtotal() {
        return items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
    
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }
    
    public void clear() {
        items.clear();
    }
}