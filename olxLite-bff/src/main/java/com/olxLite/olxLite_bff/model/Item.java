package com.olxLite.olxLite_bff.model;

import java.util.UUID;

public class Item {

    private UUID id;
    private String name;
    private int quantity;
    private String user;
    private String description;
    private String opinion;

    public Item() {
    }

    public Item(UUID id, String name, int quantity, String user, String description, String opinion) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.user = user;
        this.description = description;
        this.opinion = opinion;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}
