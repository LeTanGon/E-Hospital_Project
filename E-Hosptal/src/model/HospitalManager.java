package model;

import java.util.ArrayList;
import java.io.*;

public class HospitalManager {
    private static HospitalManager instance;
    private ArrayList<Patient> patientList;
    private final String FILE_NAME = "hospital_data.txt";

    private HospitalManager() {
        patientList = new ArrayList<>();
        loadFromFile();
    }

    public static HospitalManager getInstance() {
        if (instance == null) instance = new HospitalManager();
        return instance;
    }

    public void addPatient(Patient p) {
        patientList.add(p);
        saveToFile();
    }

    public ArrayList<Patient> getPatientList() { return patientList; }

    // --- Applied Math Statistics ---
    public double getAverageFee() {
        if (patientList.isEmpty()) return 0;
        double sum = 0;
        for (Patient p : patientList) sum += p.getBaseFee();
        return sum / patientList.size();
    }

    // --- File Operations ---
    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Patient p : patientList) {
                // Format: ID, Name, Age, Diagnosis, Fee
                pw.println(p.getId() + "," + p.getFullName() + "," + p.getAge() + "," + p.getDiagnosis() + "," + p.getBaseFee());
            }
        } catch (IOException e) {
            System.err.println("Save error: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        File f = new File(FILE_NAME);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length >= 5) {
                    patientList.add(new Patient(d[0], d[1], Integer.parseInt(d[2]), d[3], Double.parseDouble(d[4])));
                }
            }
        } catch (Exception e) {
            System.err.println("Load error.");
        }
    }
}
