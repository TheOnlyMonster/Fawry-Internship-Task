package main.models;

import main.interfaces.IShippable;

public class CartItem implements IShippable {
    private Product product;
    private int quantity;
    
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
    
    public Product getProduct() {
      return product;
    }
    

    public int getQuantity() {
      return quantity;
    }

    @Override
    public String getName() {
      return product.getName();
    }
    
    @Override
    public double getWeight() {
      if (product.getFeature(ShippingFeature.class) == null) {
        return 0;
      }
      return product.getFeature(ShippingFeature.class).getWeight();
    }
    

}
