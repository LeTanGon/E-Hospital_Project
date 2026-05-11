package model;

import java.util.ArrayList;
import java.io.*;

public class HospitalManager {
    private static HospitalManager instance;
    private ArrayList<Patient> patientList = new ArrayList<>();
    private ArrayList<Doctor> doctorList = new ArrayList<>();
    private final String DATA_FILE = "hospital_records.txt";

    private HospitalManager() {
        loadFromFile();
    }

    // Singleton Pattern to keep data consistent
    public static HospitalManager getInstance() {
        if (instance == null) {
            instance = new HospitalManager();
        }
        return instance;
    }
    // Patient 
    public void addPatient(Patient p) {
        patientList.add(p);
        saveToFile(); // Auto-save every time a patient is added
    }
    //Doctor
    public void addDoctor(Doctor d) {
        doctorList.add(d);
        saveToFile();
    }

    public ArrayList<Patient> getPatientList() { return patientList; }
    public ArrayList<Doctor> getDoctorList() { return doctorList; }
    

    // Applied Math: Calculate Mean Fee
    public double getAverageFee() {
        if (patientList.isEmpty()) return 0.0;
        double total = 0;
        for (Patient p : patientList) {
            total += p.getBaseFee();
        }
        return total / patientList.size();
    }

    // Save data to local text file
    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Patient p : patientList) {
                pw.println(p.getId() + "," + p.getFullName() + "," + p.getAge() + "," + p.getDiagnosis() + "," + p.getBaseFee());
            }
            for (Doctor d : doctorList) {
                pw.println("D," + d.getId() + "," + d.getFullName() + "," + d.getAge() );
            }
        } catch (IOException e) {
            System.err.println("Critical: Could not save data.");
        }
    }

    // Load data when app starts
    public void loadFromFile() {
        File f = new File(DATA_FILE);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts[0].equals("P")) {
                    patientList.add(new Patient(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4], Double.parseDouble(parts[5])));
                } else if (parts[0].equals("D")) {
                    doctorList.add(new Doctor(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4]));
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading existing records.");
        }
    }
}