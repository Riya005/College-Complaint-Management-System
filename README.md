# College-Complaint-Management-System

# Overview
The Complaint Management System is a Java-based application designed to streamline the process of registering, tracking, and resolving complaints within a college environment. The system provides a user-friendly interface for students, faculty, and staff to communicate their concerns efficiently. The project aims to enhance communication, transparency, and efficiency in addressing various issues within the college.

# Complaint Registration:
Students, faculty, and staff can register complaints, providing relevant details about the nature of the concern.

# Complaint Tracking:
Complainants can track the status and progress of their registered complaints.

# Complaint Resolution:
Administrators have the ability to resolve and close complaints, with notifications sent to the complainants.

# Project Structure

CompClient.java:
Main class to initiate the Complaint Management System.

ComplaintManagementSystem.java:
Central class managing the interaction between the GUI and data handling components.

CompGUI.java:
Manages the graphical user interface, including the main menu, complaint registration, status checking, and reporting.

CompFile.java:
Handles the storage and retrieval of complaint data.

Complaint.java:
Represents a complaint with attributes such as complaint number, department, description, solution, priority, and type.

CompRegister.java:
Provides a GUI for registering new complaints.

CompStatus.java:
Displays the status and details of a specific complaint.

CompReport.java:
Generates a report displaying all filed complaints.
