package cart;

import model.Product;
import java.io.Serializable;
public class CartItem implements Serializable {
    private Product product;
    private double price;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Product product, double price, int quantity) {
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int quantity) {
        // TODO Auto-generated method stub
        if (quantity <= 0) quantity = 1;
        this.quantity += quantity;
    }
    public double getTotalPrice() {
        return this.price * this.quantity;
    }
}