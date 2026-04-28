package model;

public abstract class Person {
    protected String id;
    protected String fullName;
    protected int age;

    public Person(String id, String fullName, int age) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public int getAge() { return age; }

    public abstract void showInfo();
}
