package model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HospitalGUI extends JFrame {
    private HospitalManager manager = HospitalManager.getInstance();
    
    // Patient Fields
    private JTextField txtId, txtName, txtAge, txtDiag, txtFee;
    private DefaultTableModel patientModel;

    // Doctor Fields
    private JTextField txtDocId, txtDocName, txtDocAge, txtSpec;
    private DefaultTableModel doctorModel;
    
    private JLabel lblAverage;

    public HospitalGUI() {
        setTitle("E-Hospital - Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // create tab system
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Patient Management", createPatientPanel());
        tabs.addTab("Doctor Management", createDoctorPanel());
        
        setLayout(new BorderLayout());
        add(tabs, BorderLayout.CENTER);
        add(createStatusPanel(), BorderLayout.SOUTH);

        updateStats();
    }

    // visual patient manager
    private JPanel createPatientPanel() {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel form = new JPanel(new GridLayout(3, 4, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Patient Registration"));
        txtId = new JTextField(); txtName = new JTextField(); txtAge = new JTextField();
        txtDiag = new JTextField(); txtFee = new JTextField();
        JButton btnAddP = new JButton("Add Patient");
        btnAddP.addActionListener(e -> handleAddPatient());

        form.add(new JLabel("ID:")); form.add(txtId);
        form.add(new JLabel("Name:")); form.add(txtName);
        form.add(new JLabel("Age:")); form.add(txtAge);
        form.add(new JLabel("Diagnosis:")); form.add(txtDiag);
        form.add(new JLabel("Fee:")); form.add(txtFee);
        form.add(new JLabel("")); form.add(btnAddP);

        patientModel = new DefaultTableModel(new String[]{"ID", "Name", "Age", "Diagnosis", "Fee"}, 0);
        refreshPatientTable();

        p.add(form, BorderLayout.NORTH);
        p.add(new JScrollPane(new JTable(patientModel)), BorderLayout.CENTER);
        return p;
    }

    // visual doctor
    private JPanel createDoctorPanel() {
    JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JPanel form = new JPanel(new GridLayout(3, 4, 10, 10));
    form.setBorder(BorderFactory.createTitledBorder("Doctor Registration"));

    txtDocId = new JTextField();
    txtDocName = new JTextField();
    txtDocAge = new JTextField();
    txtSpec = new JTextField();
    JButton btnAddDoc = new JButton("Add Doctor");

    form.add(new JLabel("ID:")); form.add(txtDocId);
    form.add(new JLabel("Name:")); form.add(txtDocName);
    form.add(new JLabel("Age:")); form.add(txtDocAge);
    form.add(new JLabel("Specialty:"));form.add(txtSpec);

    form.add(new JLabel("")); // Spacer
    form.add(new JLabel("")); // Spacer
    form.add(new JLabel("")); // Spacer
    form.add(btnAddDoc);

    String[] columns = {"ID", "Name", "Age", "Specialty"};
    doctorModel = new DefaultTableModel(columns, 0);
    JTable table = new JTable(doctorModel);
    
    mainPanel.add(form, BorderLayout.NORTH);
    mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

    btnAddDoc.addActionListener(e -> handleAddDoctor());

    return mainPanel;
}

    private JPanel createStatusPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblAverage = new JLabel("Average Hospital Fee: $0.00");
        p.add(lblAverage);
        return p;
    }

    // logic
    private void handleAddPatient() {
        try {
            manager.addPatient(new Patient(txtId.getText(), txtName.getText(), Integer.parseInt(txtAge.getText()), txtDiag.getText(), Double.parseDouble(txtFee.getText())));
            refreshPatientTable(); updateStats();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Check inputs!"); }
    }

    private void handleAddDoctor() {
        try {
            manager.addDoctor(new Doctor(txtDocId.getText(), txtDocName.getText(), Integer.parseInt(txtDocAge.getText()), txtSpec.getText()));
            refreshDoctorTable();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Check inputs!"); }
    }

    private void refreshPatientTable() {
        patientModel.setRowCount(0);
        for (Patient p : manager.getPatientList()) patientModel.addRow(new Object[]{p.getId(), p.getFullName(), p.getAge(), p.getDiagnosis(), p.getBaseFee()});
    }

    private void refreshDoctorTable() {
        doctorModel.setRowCount(0);
        for (Doctor d : manager.getDoctorList()) doctorModel.addRow(new Object[]{d.getId(), d.getFullName(), d.getAge()});
    }

    private void updateStats() {
        lblAverage.setText(String.format("Average Hospital Fee (Mean): $%.2f", manager.getAverageFee()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HospitalGUI().setVisible(true));
    }
}