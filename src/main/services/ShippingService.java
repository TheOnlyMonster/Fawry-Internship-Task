package main.services;
import main.interfaces.IShippingService;
import main.models.*;
import java.util.List;

public class ShippingService implements IShippingService {
    private static final double SHIPPING_RATE_PER_KG = 30.0; 
    
    public void ship(List<CartItem> shippableItems) {
        if (shippableItems.isEmpty()) {
            return;
        }
        
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        
        for (CartItem item : shippableItems) {
            Product product = item.getProduct();
            ShippingFeature shippingFeature = product.getFeature(ShippingFeature.class);
            if(shippingFeature == null) {
                continue;
            }
            int quantity = item.getQuantity();
            double itemWeight = shippingFeature.getWeight();
            
            System.out.println(quantity + "x " + product.getName() + " " + (int)(itemWeight * 1000) + "g");
            totalWeight += itemWeight * quantity;
        }
        
        System.out.println("Total package weight " + totalWeight + "kg");
    }
    
    public double calculateShippingFee(List<CartItem> shippableItems) {
        double totalWeight = 0;
        
        for (CartItem item : shippableItems) {
            Product product = item.getProduct();
            ShippingFeature shippingFeature = product.getFeature(ShippingFeature.class);
            if (shippingFeature == null) {
                continue;
            }
            totalWeight += shippingFeature.getWeight() * item.getQuantity();
        }
        
        return totalWeight * SHIPPING_RATE_PER_KG;
    }
}