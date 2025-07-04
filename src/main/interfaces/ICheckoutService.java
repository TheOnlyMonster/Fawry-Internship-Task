package main.interfaces;

import main.models.Customer;
import main.models.Cart;
public interface ICheckoutService {
  public void checkout(Customer customer, Cart cart);
}
