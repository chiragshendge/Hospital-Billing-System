package com.hospital.gui;

import com.hospital.core.Bill;
import com.hospital.core.Patient;
import com.hospital.core.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The main GUI window for the Hospital Billing System.
 * Uses Java Swing components.
 */
public class BillingSystemGUI extends JFrame {

    // GUI Components
    private JTextField patientIdField;
    private JTextField patientNameField;
    private JCheckBox consultationCheckBox;
    private JCheckBox xrayCheckBox;
    private JCheckBox bloodTestCheckBox;
    private JCheckBox mriCheckBox;
    private JTextArea billTextArea;
    private JButton generateBillButton;

    // Services
    private Service consultation = new Service("Consultation", 50.00);
    private Service xray = new Service("X-Ray", 150.00);
    private Service bloodTest = new Service("Blood Test", 80.00);
    private Service mri = new Service("MRI Scan", 400.00);

    public BillingSystemGUI() {
        // --- Frame Setup ---
        setTitle("Hospital Billing System");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout(10, 10)); // Main layout

        // --- Input Panel (Patient Info + Services) ---
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10)); // 0 rows, 2 cols
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Patient Info
        inputPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        inputPanel.add(patientIdField);

        inputPanel.add(new JLabel("Patient Name:"));
        patientNameField = new JTextField();
        inputPanel.add(patientNameField);

        // Services Title
        JLabel servicesTitle = new JLabel("Services Rendered:");
        servicesTitle.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(servicesTitle);
        inputPanel.add(new JLabel()); // Empty placeholder

        // Service Checkboxes
        consultationCheckBox = new JCheckBox(consultation.getName() + " ($" + consultation.getCost() + ")");
        inputPanel.add(consultationCheckBox);

        xrayCheckBox = new JCheckBox(xray.getName() + " ($" + xray.getCost() + ")");
        inputPanel.add(xrayCheckBox);

        bloodTestCheckBox = new JCheckBox(bloodTest.getName() + " ($" + bloodTest.getCost() + ")");
        inputPanel.add(bloodTestCheckBox);

        mriCheckBox = new JCheckBox(mri.getName() + " ($" + mri.getCost() + ")");
        inputPanel.add(mriCheckBox);

        // --- Bill Area (Text Area) ---
        billTextArea = new JTextArea();
        billTextArea.setEditable(false);
        billTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        billTextArea.setBorder(BorderFactory.createTitledBorder("Final Bill"));
        JScrollPane scrollPane = new JScrollPane(billTextArea);

        // --- Button Panel ---
        JPanel buttonPanel = new JPanel();
        generateBillButton = new JButton("Generate Bill");
        buttonPanel.add(generateBillButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // --- Add Panels to Frame ---
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Event Listener for the Button ---
        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });
    }

    /**
     * Gathers data from the GUI, calculates the bill, and displays it.
     */
    private void generateBill() {
        // 1. Get Patient Details
        String pId = patientIdField.getText();
        String pName = patientNameField.getText();

        if (pId.isEmpty() || pName.isEmpty()) {
            // A simple validation
            JOptionPane.showMessageDialog(this, "Please enter Patient ID and Name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Patient patient = new Patient(pId, pName);

        // 2. Get Selected Services
        List<Service> selectedServices = new ArrayList<>();
        if (consultationCheckBox.isSelected()) {
            selectedServices.add(consultation);
        }
        if (xrayCheckBox.isSelected()) {
            selectedServices.add(xray);
        }
        if (bloodTestCheckBox.isSelected()) {
            selectedServices.add(bloodTest);
        }
        if (mriCheckBox.isSelected()) {
            selectedServices.add(mri);
        }

        if (selectedServices.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one service.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Create Bill and Generate Report
        Bill bill = new Bill(patient, selectedServices);
        String billReport = bill.generateBillReport();

        // 4. Display Bill
        billTextArea.setText(billReport);
    }
}

