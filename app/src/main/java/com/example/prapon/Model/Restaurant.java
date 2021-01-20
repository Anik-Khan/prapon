package com.example.prapon.Model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name, address, contactNo, emailId, rating, details, picUrl;
    private List<String> menuTopic;
    private List<Menu> menu;

    public Restaurant() {
        menuTopic = new ArrayList<>();
        menu = new ArrayList<>();
    }

    public List<String> getMenuTopic() {
        return menuTopic;
    }

    public void setMenuTopic(List<String> menuTopic) {
        this.menuTopic = menuTopic;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
