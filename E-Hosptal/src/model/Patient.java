package model;

public class Patient extends Person {
    private String diagnosis;
    private double baseFee;

    public Patient(String id, String fullName, int age, String diagnosis, double baseFee) {
        super(id, fullName, age);
        this.diagnosis = diagnosis;
        this.baseFee = baseFee;
    }

    public String getDiagnosis() { return diagnosis; }
    public double getBaseFee() { return baseFee; }

    @Override
    public void showInfo() {
        System.out.println("Patient: " + fullName + " | Diagnosis: " + diagnosis);
    }
}