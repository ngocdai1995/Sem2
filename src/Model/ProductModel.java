/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Admin
 */
public class ProductModel {

    private int id;
    private CategoryModel category;
    private String name;
    private float price;
    private String thumbnail;
    String description;
    private int quantity;
    private String created_at, updated_at;

    public ProductModel() {
    }
    
    
    public ProductModel(int id, CategoryModel category, String name, float price, String thumbnail, String description, int quantity, String created_at, String updated_at) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.description = description;
        this.quantity = quantity;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public ProductModel(int id, String name, float price, String thumbnail, String description, int quantity, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.description = description;
        this.quantity = quantity;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    
    
    public ProductModel(CategoryModel category, String name, float price, String thumbnail, String description, int quantity, String created_at, String updated_at) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.description = description;
        this.quantity = quantity;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public ProductModel(String name, float price, String thumbnail, String description, int quantity, String created_at, String updated_at) {
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.description = description;
        this.quantity = quantity;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductModel{" + "category=" + category + ", name=" + name + ", price=" + price + ", thumbnail=" + thumbnail + ", description=" + description + ", quantity=" + quantity + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }

   

}
