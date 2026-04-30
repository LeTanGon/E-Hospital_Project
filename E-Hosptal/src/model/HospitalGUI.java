package model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/*
 * Project: E-Hospital Management System
 * Developer: Le Tan Gon - Applied Mathematics Student
 */
public class HospitalGUI extends JFrame {
    private HospitalManager manager = HospitalManager.getInstance();
    private DefaultTableModel tableModel;
    
    // Input Fields
    private JTextField txtId, txtName, txtAge, txtDiagnosis, txtFee;
    private JLabel lblAverage;

    public HospitalGUI() {
        setTitle("E-Hospital Pro - Record Management");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. TOP: Input Panel
        add(createInputPanel(), BorderLayout.NORTH);

        // 2. CENTER: Table
        add(createTablePanel(), BorderLayout.CENTER);

        // 3. BOTTOM: Status/Math Panel
        add(createStatusPanel(), BorderLayout.SOUTH);

        updateStats(); // Initial math update
    }

    private JPanel createInputPanel() {
        JPanel p = new JPanel(new GridLayout(3, 4, 10, 10));
        p.setBorder(BorderFactory.createTitledBorder("Patient Registration Form"));

        // Creating components
        txtId = new JTextField();
        txtName = new JTextField();
        txtAge = new JTextField();
        txtDiagnosis = new JTextField();
        txtFee = new JTextField();
        
        JButton btnAdd = new JButton("Add Record");
        btnAdd.setBackground(new Color(70, 130, 180));
        btnAdd.setForeground(Color.WHITE);

        // Layout: Label - Field - Label - Field
        p.add(new JLabel("Patient ID:")); p.add(txtId);
        p.add(new JLabel("Full Name:")); p.add(txtName);
        p.add(new JLabel("Age:")); p.add(txtAge);
        p.add(new JLabel("Diagnosis:")); p.add(txtDiagnosis);
        p.add(new JLabel("Base Fee ($):")); p.add(txtFee);
        p.add(new JLabel("")); // Empty space
        p.add(btnAdd);

        btnAdd.addActionListener(e -> handleAdd());

        return p;
    }

    private JPanel createTablePanel() {
        JPanel p = new JPanel(new BorderLayout());
        String[] columns = {"ID", "Full Name", "Age", "Diagnosis", "Fee ($)"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        
        refreshTable();
        p.add(new JScrollPane(table), BorderLayout.CENTER);
        return p;
    }

    private JPanel createStatusPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblAverage = new JLabel("Average Hospital Fee: $0.00");
        lblAverage.setFont(new Font("Arial", Font.BOLD, 15));
        p.add(lblAverage);
        return p;
    }

    private void handleAdd() {
        try {
            // Collecting data from UI
            String id = txtId.getText();
            String name = txtName.getText();
            int age = Integer.parseInt(txtAge.getText());
            String diag = txtDiagnosis.getText();
            double fee = Double.parseDouble(txtFee.getText());

            // Create and save
            Patient p = new Patient(id, name, age, diag, fee);
            manager.addPatient(p);

            // Update UI
            refreshTable();
            updateStats();
            clearFields();
            
            JOptionPane.showMessageDialog(this, "Patient added successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: Please check if Age and Fee are numbers.");
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Patient p : manager.getPatientList()) {
            tableModel.addRow(new Object[]{p.getId(), p.getFullName(), p.getAge(), p.getDiagnosis(), p.getBaseFee()});
        }
    }

    private void updateStats() {
        double avg = manager.getAverageFee();
        lblAverage.setText(String.format("Average Hospital Fee (Mean): $%.2f", avg));
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtDiagnosis.setText("");
        txtFee.setText("");
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
        SwingUtilities.invokeLater(() -> new HospitalGUI().setVisible(true));
    }
}