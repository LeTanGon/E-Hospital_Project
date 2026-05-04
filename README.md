                                      E-Hospital Management System
A Java-based desktop application designed to streamline hospital record management. This project demonstrates the practical application of Object-Oriented Programming (OOP) and Applied Mathematics in a software environment.

1.Overview
 
-The E-Hospital System allows administrators to manage patient and doctor information through an intuitive graphical interface. It focuses on efficient data handling, persistent storage, and real-time statistical analysis.

2.Key Features:

-OOP Implementation: Utilizes Abstraction, Inheritance, and Polymorphism for a robust system architecture.

-Singleton Pattern: Ensures a single source of truth for data management throughout the application.

-Persistent Storage: Automatically saves and loads records from a local text file (hospital_records.txt), ensuring data is never lost.

-Real-time Statistics: Automatically calculates the average hospital fee across all registered patients.

-User-Friendly GUI: Built with Java Swing, featuring a structured layout for easy data entry and viewing.

3.Technologies Used:

-Language: Java

-Library: Java Swing (GUI), AWT

-Storage: Local File I/O (.txt)

-Version Control: Git & GitHub

4.Project Structure:

-Person.java: Abstract base class defining common attributes.

-Doctor.java & Patient.java: Specialized classes inheriting from Person.

-HospitalManager.java: The core logic handler (Singleton) for data processing and File I/O.

-HospitalGUI.java: The main entry point and user interface.
