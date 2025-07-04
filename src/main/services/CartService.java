package main.services;

import main.models.*;
import main.interfaces.IShippingService;
import main.interfaces.ICartService;
import java.util.List;
import java.util.ArrayList;

public class CartService implements ICartService  {
    private IShippingService shippingService;
    
    public CartService(IShippingService shippingService) {
        this.shippingService = shippingService;
    }
    
    @Override
    public void checkout(Customer customer, Cart cart) {

        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }
        
        validateCartItems(cart);
        
        double subtotal = cart.getSubtotal();
        double shippingFee = shippingService.calculateShippingFee(cart.getItems());
        double totalAmount = subtotal + shippingFee;
        
        if (customer.getBalance() < totalAmount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        
        customer.deductBalance(totalAmount);
        
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
        
        shippingService.ship(cart.getItems());
        
        printReceipt(cart, subtotal, shippingFee, totalAmount, customer);
        
        cart.clear();
    }
    
    private void validateCartItems(Cart cart) {
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            
            if (product.getQuantity() < item.getQuantity()) {
                throw new IllegalArgumentException("Product " + product.getName() + " is out of stock");
            }
            
            if (product.hasFeature(ExpirationFeature.class)) {
                ExpirationFeature expFeature = product.getFeature(ExpirationFeature.class);
                if (expFeature.isExpired()) {
                    throw new IllegalArgumentException("Product " + product.getName() + " has expired");
                }
            }
        }
    }
    
    
    private void printReceipt(Cart cart, double subtotal, double shippingFee, double totalAmount, Customer customer) {
        System.out.println("** Checkout receipt **");
        
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + (int)item.getTotalPrice());
        }
        
        System.out.println("----------------------");
        System.out.println("Subtotal " + (int)subtotal);
        System.out.println("Shipping " + (int)shippingFee);
        System.out.println("Amount " + (int)totalAmount);
        System.out.println("Customer balance after payment: " + customer.getBalance());
        System.out.println("END.");
    }
}