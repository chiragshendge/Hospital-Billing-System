import streamlit as st

# ----------------------------
# Data Classes (Equivalent to Patient.java and Service.java)
# ----------------------------

class Patient:
    def __init__(self, patient_id, name):
        self.id = patient_id
        self.name = name

    def __str__(self):
        return f"Patient ID: {self.id}\nPatient Name: {self.name}"


class Service:
    def __init__(self, name, cost):
        self.name = name
        self.cost = cost

    def __str__(self):
        return f"{self.name:<20} ₹{self.cost:.2f}"


# ----------------------------
# Core Billing Logic (Equivalent to Bill.java)
# ----------------------------

class Bill:
    def __init__(self, patient, services):
        self.patient = patient
        self.services = services
        self.total_cost = self.calculate_total_cost()

    def calculate_total_cost(self):
        return sum(service.cost for service in self.services)

    def generate_bill_report(self):
        report = []
        report.append("========================================")
        report.append("           HOSPITAL BILL")
        report.append("========================================\n")
        report.append(str(self.patient))
        report.append("\n----------------------------------------")
        report.append("Services Rendered:")
        report.append("----------------------------------------")
        for service in self.services:
            report.append(str(service))
        report.append("----------------------------------------")
        report.append(f"{'TOTAL':<20} ₹{self.total_cost:.2f}")
        report.append("----------------------------------------")
        return "\n".join(report)


# ----------------------------
# Streamlit GUI (Equivalent to BillingSystemGUI.java)
# ----------------------------

def main():
    st.set_page_config(page_title="Hospital Billing System", layout="centered")
    st.title("🏥 Hospital Billing System")

    # --- Patient Info ---
    st.header("🧾 Patient Details")
    col1, col2 = st.columns(2)
    with col1:
        patient_id = st.text_input("Patient ID")
    with col2:
        patient_name = st.text_input("Patient Name")

    # --- Services ---
    st.header("💉 Services Rendered")
    service_list = [
        Service("Consultation", 50.00),
        Service("X-Ray", 150.00),
        Service("Blood Test", 80.00),
        Service("MRI Scan", 400.00)
    ]

    selected_services = []
    for service in service_list:
        if st.checkbox(f"{service.name} (₹{service.cost:.2f})"):
            selected_services.append(service)

    # --- Generate Bill Button ---
    if st.button("Generate Bill"):
        if not patient_id or not patient_name:
            st.error("Please enter both Patient ID and Name.")
        elif not selected_services:
            st.error("Please select at least one service.")
        else:
            patient = Patient(patient_id, patient_name)
            bill = Bill(patient, selected_services)
            report = bill.generate_bill_report()

            st.success("✅ Bill Generated Successfully!")
            st.text_area("Final Bill", report, height=400)


# Run the app
if __name__ == "__main__":
    main()
