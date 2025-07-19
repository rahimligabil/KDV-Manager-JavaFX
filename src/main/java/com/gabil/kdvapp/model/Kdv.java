package com.gabil.kdvapp.model;

import java.time.LocalDate;

public class Kdv {
    private int id;
    private double amount;
    private double rate;
    private String description;
    private LocalDate date;

    public Kdv() {}

    public Kdv(int id, double amount, double rate, String description, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.rate = rate;
        this.description = description;
        this.date = date;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
