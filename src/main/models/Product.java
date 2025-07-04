package main.models;
import main.interfaces.IProductFeature;
import java.util.Set;
import java.util.HashSet;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private Set<IProductFeature<?>> features;
    
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.features = new HashSet<>();
    }
    
    public void addFeature(IProductFeature<?> feature) {
        if (hasFeature(feature.getFeatureType())) {
            throw new IllegalArgumentException("Feature already added");
        }
        features.add(feature);
    }
    
    public boolean hasFeature(Class<?> featureType) {
        return features.stream()
                .anyMatch(feature -> feature.getFeatureType().equals(featureType));
    }

    public <T extends IProductFeature> T getFeature(Class<T> featureType) {
        for (IProductFeature feature : features) {
            if (featureType.isInstance(feature)) {
                return featureType.cast(feature);
            }
        }
        return null;
    }
    
    public void reduceQuantity(int amount) {
        if (quantity < amount) {
            throw new IllegalArgumentException("Insufficient quantity available");
        }
        quantity -= amount;
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public Set<IProductFeature<?>> getFeatures() { 
        return new HashSet<>(features); 
    }
}