package com.hospital.core;

import java.util.List;

/**
 * This class handles the business logic for calculating
 * and formatting the bill.
 */
public class Bill {
    private Patient patient;
    private List<Service> services;
    private double totalCost;

    public Bill(Patient patient, List<Service> services) {
        this.patient = patient;
        this.services = services;
        this.totalCost = calculateTotalCost();
    }

    /**
     * Calculates the total cost by summing up all services.
     * @return The total cost as a double.
     */
    private double calculateTotalCost() {
        double total = 0.0;
        for (Service service : services) {
            total += service.getCost();
        }
        return total;
    }

    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Generates a formatted string report of the entire bill.
     * @return A string representing the final bill.
     */
    public String generateBillReport() {
        // StringBuilder is efficient for building strings
        StringBuilder report = new StringBuilder();

        report.append("========================================\n");
        report.append("           HOSPITAL BILL\n");
        report.append("========================================\n\n");

        report.append(patient.toString()).append("\n\n");

        report.append("----------------------------------------\n");
        report.append("Services Rendered:\n");
        report.append("----------------------------------------\n");

        for (Service service : services) {
            report.append(service.toString()).append("\n");
        }

        report.append("----------------------------------------\n");
        report.append(String.format("%-20s $%.2f", "TOTAL", totalCost));
        report.append("\n----------------------------------------\n");

        return report.toString();
    }
}
