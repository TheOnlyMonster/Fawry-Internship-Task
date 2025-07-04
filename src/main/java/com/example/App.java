package com.example;
import main.models.*;
import java.time.LocalDate;

public class App 
{
    public static void main( String[] args )
    {
        Product cheese = new Product("Cheese", 100.0, 10);
        
        cheese.addFeature(new ExpirationFeature(LocalDate.now().plusDays(7)));
        cheese.addFeature(new ShippingFeature(0.2));
        
        
        boolean expFeature = cheese.hasFeature(ExpirationFeature.class);
        if (expFeature) {
            System.out.println("Expiration date: " + cheese.getFeature(ExpirationFeature.class).getExpirationDate());
        }
        
        boolean shipFeature = cheese.hasFeature(ShippingFeature.class);
        if (shipFeature) {
            System.out.println("Shipping weight: " + cheese.getFeature(ShippingFeature.class).getWeight());
        }
    }
}
