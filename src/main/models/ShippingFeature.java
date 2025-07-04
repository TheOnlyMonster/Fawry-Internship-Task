package main.models;

import main.interfaces.IProductFeature;

public class ShippingFeature implements IProductFeature<ShippingFeature> {
    private double weight;
    
    public ShippingFeature(double weight) {
        this.weight = weight;
    }
    
    public double getWeight() {
        return weight;
    }
    
    @Override
    public Class<ShippingFeature> getFeatureType() {
        return ShippingFeature.class;
    }
}