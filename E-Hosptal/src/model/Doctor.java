package model;

public class Doctor extends Person {
    private String specialty;

    public Doctor(String id, String fullName, int age, String specialty) {
        super(id, fullName, age);
        this.specialty = specialty;
    }

    @Override
    public void showInfo() {
        System.out.println("Doctor: " + fullName + " | Specialty: " + specialty);
    }    
}
