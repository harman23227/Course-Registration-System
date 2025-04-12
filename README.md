# University Course Management System (ERP)

![Java](https://img.shields.io/badge/Java-17-blue)
![OOP](https://img.shields.io/badge/OOP-Design-success)
![License](https://img.shields.io/badge/License-MIT-green)

A Java-based ERP system for universities to manage courses, student enrollments, professor assignments, and administrative workflows.

## ✨ Features
- **Role-Based Access Control**  
  - Students: Enroll/drop courses, submit feedback/complaints, view results.  
  - Professors: Manage grades, view complaints, analyze feedback.  
  - TAs: Update grades, access student menus.  
  - Admins: Add/remove courses, manage users.  

- **Core Functionalities**  
  - Course enrollment with deadline checks (`dropdeadlinepassedexception`).  
  - Capacity validation (`coursefullexception`).  
  - Generic feedback system (ratings/comments).  
  - Complaint handling with status tracking.  

- **Technical Highlights**  
  - OOP design with inheritance (`user` → `student`, `professor`, `TA`).  
  - Singleton `database` for centralized data management.  
  - Exception handling for robust workflows.  
  - Generic programming for flexible feedback (`<T>`).  
