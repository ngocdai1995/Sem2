/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class UsersModel {

    private int id;
    private String fullname, email, phoneNumber, address, password, birthday;
    RoleModel role;
    private String created_at, updated_at, gender;

    public UsersModel() {
    }

    public UsersModel(RoleModel role) {
        this.role = role;
    }
    
    // no role
    public UsersModel(int id, String fullname, String email, String phoneNumber, String address, String password, String birthday, String created_at, String updated_at, String gender) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.birthday = birthday;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.gender = gender;
    }
    
    public UsersModel(String fullname, String email, String phoneNumber, String address, String password, String birthday, RoleModel role,String created_at, String updated_at, String gender) {
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.birthday = birthday;
        this.role = role;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.gender = gender;
    }

    public UsersModel(String fullname, String email, String phoneNumber, String address, String password, String birthday, String created_at, String updated_at, String gender) {
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.birthday = birthday;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.gender = gender;
    }

    public UsersModel(int id, String fullname, String email, String phoneNumber, String address, String password, String birthday, RoleModel role, String created_at, String updated_at, String gender) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.birthday = birthday;
        this.role = role;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.gender = gender;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    @Override
    public String toString() {
        return fullname ;
    }

    
    
}
