package model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private double price;
    private int volume;
    private String supplier_name;
    private int quantity;
    private String img;
    private enum Status {active, inactive};
    private String description;

    public Product(double price, int id, String name, int volume, String supplier_name, int quantity, String description, String img) {
        this.price = price;
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.supplier_name = supplier_name;
        this.quantity = quantity;
        this.description = description;
        this.img = img;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                ", supplier_name='" + supplier_name + '\'' +
                ", quantity=" + quantity +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}