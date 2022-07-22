/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author acer
 */
public class OrderModel {

    int id;
    int staffId;
    String createdAt, updatedAt;

    public OrderModel() {
    }

    public OrderModel(int id, int staffId, String createdAt, String updatedAt) {
        this.id = id;
        this.staffId = staffId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public OrderModel(int staffId, String createdAt, String updatedAt) {
        this.staffId = staffId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "OrderModel{" + "id=" + id + ", staffId=" + staffId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
}
