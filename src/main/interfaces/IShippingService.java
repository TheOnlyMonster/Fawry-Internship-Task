package main.interfaces;

import java.util.List;

import main.models.CartItem;

public interface IShippingService {
    public void ship(List<CartItem> shippableItems);
    public double calculateShippingFee(List<CartItem> shippableItems);
}