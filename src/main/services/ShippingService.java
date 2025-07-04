package main.services;

import main.interfaces.IShippingService;
import main.models.*;
import java.util.List;

public class ShippingService implements IShippingService {
    private static final double SHIPPING_RATE_PER_KG = 30.0; 
    @Override
    public void ship(List<CartItem> shippableItems) {
        if (shippableItems.isEmpty()) {
            return;
        }
        
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        
        for (CartItem item : shippableItems) {
            int quantity = item.getQuantity();
            double itemWeight = item.getWeight();
            String name = item.getName();
            
            System.out.println(quantity + "x " + name + " " + (int)(itemWeight * 1000) + "g");
            totalWeight += itemWeight * quantity;
        }
        
        System.out.println("Total package weight " + String.format("%.1f", totalWeight) + "kg");
    }
    @Override
    public double calculateShippingFee(List<CartItem> shippableItems) {
        double totalWeight = 0;
        
        for (CartItem item : shippableItems) {
            int quantity = item.getQuantity();
            double itemWeight = item.getWeight();
            totalWeight += itemWeight * quantity;
        }
        
        return totalWeight * SHIPPING_RATE_PER_KG;
    }
}