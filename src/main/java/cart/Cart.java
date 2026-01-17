package cart;

import java.util.*;
import java.io.Serializable;
import model.Product;

public class Cart implements Serializable {
    Map<Integer, CartItem> data;

    public Cart() {
        data = new HashMap<>();
    }
    public void addProduct(Product product, int quantity) {
        if (data.containsKey(product.getId())) {
            data.get(product.getId()).increaseQuantity(quantity);
        } else data.put(product.getId(), new CartItem(product, product.getPrice(), quantity));
    }
    public CartItem deleteProduct(int productId){
        return data.remove(productId);
    }
    public ArrayList<CartItem> deleteAll(){
        Collection<CartItem> values = data.values();
        data.clear();
        return new ArrayList<>(values);
    }
    public List<CartItem> getAllItems() {
        return new ArrayList<>(data.values());
    }
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : data.values()) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (CartItem item : data.values()) {
            totalQuantity += item.getQuantity();
        }
        return totalQuantity;
    }
    public boolean update(int id, Product p){

        return false;
    }
}
