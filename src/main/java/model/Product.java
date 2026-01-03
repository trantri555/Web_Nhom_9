////package model;
////
////import java.io.Serializable;
////
////public class Product implements Serializable {
////    private int id;
////    private String name;
////    private double price;
////    private int volume;
////    private String supplierName; // Nên dùng camelCase
////    private int quantity;
////    private String img;
////    private String description;
////
////    public Product() {} // Constructor mặc định cho linh hoạt
////
////    public Product(int id, String name, double price, int volume, String supplierName, int quantity, String img, String description) {
////        this.id = id;
////        this.name = name;
////        this.price = price;
////        this.volume = volume;
////        this.supplierName = supplierName;
////        this.quantity = quantity;
////        this.img = img;
////        this.description = description;
////    }
////
////    // BẮT BUỘC PHẢI CÓ GETTER ĐỂ JSP ĐỌC ĐƯỢC DỮ LIỆU
////    public int getId() { return id; }
////    public String getName() { return name; }
////    public double getPrice() { return price; }
////    public int getVolume() { return volume; }
////    public String getSupplierName() { return supplierName; }
////    public int getQuantity() { return quantity; }
////    public String getImg() { return img; }
////    public String getDescription() { return description; }
////
////    @Override
////    public String toString() {
////        return "Product{id=" + id + ", name='" + name + "'}";
////    }
////}
//package model;
//
//import java.io.Serializable;
//
//public class Product implements Serializable {
//    private int id;
//    private String name;
//    private double price;
//    private int volume;
//    private String supplier_name;
//    private int quantity;
//    private String img;
//    private String description;
//
//    public Product(int id, String name, double price, int volume, String supplier_name, int quantity, String img, String description) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.volume = volume;
//        this.supplier_name = supplier_name;
//        this.quantity = quantity;
//        this.img = img;
//        this.description = description;
//    }
//
//    // --- SỬA TRỰC TIẾP: THÊM GETTER ---
//    public int getId() { return id; }
//    public String getName() { return name; }
//    public double getPrice() { return price; }
//    public int getVolume() { return volume; }
//    public String getSupplier_name() { return supplier_name; }
//    public int getQuantity() { return quantity; }
//    public String getImg() { return img; }
//    public void setImg(String img){
//        this.img = img;
//    }
//    public String getDescription() { return description; }
//
//    @Override
//    public String toString() {
//        return "Product{" + "id=" + id + ", name='" + name + '\'' + '}';
//    }
//}
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
    private String description;

    public Product(int id, String name, double price, int volume, String supplier_name, int quantity, String img, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.volume = volume;
        this.supplier_name = supplier_name;
        this.quantity = quantity;
        this.img = img;
        this.description = description;
    }

    public Product() {

    }

    // --- GETTERS & SETTERS ---
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getVolume() { return volume; }
    public String getSupplier_name() { return supplier_name; }

    public String getImg() { return img; }
    public void setImgage(String img) { this.img = img; } // Cần thiết cho Service xử lý ảnh mặc định

    public int getQuantity() { return quantity; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    public void setName(String name) { this.name = name;
    }

    public void setPrice(double price) {this.price = price;
    }

    public void setCategory(String category) {
    }

    public void setQuantity(int quantity) { this.quantity = quantity;
    }

}