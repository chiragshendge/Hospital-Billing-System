package com.hospital.core;

/**
 * A simple data class (POJO) to hold service information.
 */
public class Service {
    private String name;
    private double cost;

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        // Using String.format for clean currency formatting
        return String.format("%-20s $%.2f", name, cost);
    }
}

