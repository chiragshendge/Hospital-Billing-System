package com.hospital.main;

import com.hospital.gui.BillingSystemGUI;
import javax.swing.SwingUtilities;

/**
 * Main class to run the Hospital Billing System.
 * This class ensures that the GUI is created on the
 * Event Dispatch Thread (EDT).
 */
public class Main {
    public static void main(String[] args) {
        // Swing applications should be run on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and show the main GUI window
                new BillingSystemGUI().setVisible(true);
            }
        });
    }
}
