package com.example;

import main.models.*;
import main.services.*;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        ShippingService shippingService = new ShippingService();
        CartService cartService = new CartService(shippingService);
        
        Product cheese = new Product("Cheese", 100.0, 10);
        cheese.addFeature(new ExpirationFeature(LocalDate.now().plusDays(7)));
        cheese.addFeature(new ShippingFeature(0.2)); 
        
        Product biscuits = new Product("Biscuits", 150.0, 5);
        biscuits.addFeature(new ExpirationFeature(LocalDate.now().plusDays(30)));
        biscuits.addFeature(new ShippingFeature(0.7)); 
        
        Product tv = new Product("TV", 5000.0, 3);
        tv.addFeature(new ShippingFeature(15.0)); 
        
        Product mobile = new Product("Mobile", 8000.0, 2);
        mobile.addFeature(new ShippingFeature(0.5)); 
        
        Product scratchCard = new Product("ScratchCard", 50.0, 100);
        
        Customer customer = new Customer("John Doe", 10000.0);
        
        Cart cart = new Cart();
        
        System.out.println("=== Test Case 1: Successful Checkout ===");
        try {
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(scratchCard, 1);
            
            cartService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Test Case 2: Empty Cart ===");
        try {
            Cart emptyCart = new Cart();
            cartService.checkout(customer, emptyCart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Test Case 3: Insufficient Balance ===");
        try {
            Customer poorCustomer = new Customer("Poor Customer", 100.0);
            Cart expensiveCart = new Cart();
            expensiveCart.add(tv, 1);
            
            cartService.checkout(poorCustomer, expensiveCart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Test Case 4: Out of Stock ===");
        try {
            Cart cart2 = new Cart();
            cart2.add(cheese, 20);
            
            cartService.checkout(customer, cart2);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Test Case 5: Expired Product ===");
        try {
            Product expiredCheese = new Product("Expired Cheese", 100.0, 5);
            expiredCheese.addFeature(new ExpirationFeature(LocalDate.now().minusDays(1)));
            expiredCheese.addFeature(new ShippingFeature(0.2));
            
            Cart cart3 = new Cart();
            cart3.add(expiredCheese, 1);
            
            cartService.checkout(customer, cart3);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Test Case 6: Mixed Items with Heavy Shipping ===");
        try {
            Cart cart4 = new Cart();
            cart4.add(tv, 1);
            cart4.add(mobile, 1);
            cart4.add(scratchCard, 2);
            
            cartService.checkout(customer, cart4);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Test Case 7: Product Already in Cart ===");
        try {
            Cart cart5 = new Cart();
            cart5.add(cheese, 1);
            cart5.add(cheese, 1); 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Test Case 8: Zero Quantity ===");
        try {
            Cart cart6 = new Cart();
            cart6.add(cheese, 0); 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\nRemaining customer balance: " + customer.getBalance());
        System.out.println("Remaining cheese stock: " + cheese.getQuantity());
        System.out.println("Remaining biscuits stock: " + biscuits.getQuantity());
        System.out.println("Remaining TV stock: " + tv.getQuantity());
        System.out.println("Remaining mobile stock: " + mobile.getQuantity());
        System.out.println("Remaining scratch card stock: " + scratchCard.getQuantity());
    }
}