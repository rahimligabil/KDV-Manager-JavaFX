#  KDV Manager JavaFX

A feature-rich JavaFX desktop application for managing VAT (KDV) calculations, user accounts, roles, and system settings. Developed as a graduation project with multi-language support, dark/light themes, and data export capabilities.

---

## Project Goals

This application is designed to assist company personnel such as data entry staff, accountants, and administrators by providing:

-  A simple **notebook** system
-  A fast and exportable **VAT (KDV) calculator**
-  A secure **user management** and **authorization panel**
-  Multi-theme and multi-language UI
-  A modular structure with layered architecture (MVC)

---

##Technologies Used

| Layer              | Technology / Library                          |
|--------------------|-----------------------------------------------|
| UI                 | JavaFX, FXML, CSS, ControlsFX                 |
| Database           | SQLite + JDBC                                 |
| Password Security  | BCrypt (hashed login passwords)               |
| Export             | Apache POI (Excel), Apache PDFBox (PDF)       |
| Backup/Restore     | JSON / XML file operations                    |
| Mail (optional)    | JavaMail API                                   |
| Charts             | JavaFX PieChart, BarChart                     |
| Scheduler          | Timeline, ScheduledExecutorService            |
| Build Tool         | Maven                                          |

---

##  Features

### Login & Role-Based Access
- Roles: `ADMIN`, `OPERATOR`, `USER`
- Passwords hashed using BCrypt
- Interface changes dynamically by role

###  User Management
- Add, edit, delete, search, and filter users
- Reset password, assign roles
- TableView-based UI with dynamic filtering

###  VAT (KDV) Calculation
- Real-time calculation based on amount and tax rate
- Add invoice number, date, and description
- Export to PDF, Excel, or TXT
- Print and email report capabilities

### Theme & Language Support
- Light/Dark theme toggle (`Scene.getStylesheets()`)
- English and Turkish support (`ResourceBundle`)

###  Notification System
- Pop-up messages for success/error/info
- Notification history available

###  Notebook Module
- Personal notes for each user
- Optional countdown timer with reminder popups

###  Backup & Restore
- Save data as JSON/XML
- Restore via `FileChooser`
- Table updates automatically after restore

###  Date & Time Panel
- Live clock using Timeline

---

##  Project Structure
src/main/java/com/gabil/kdvapp/
├── config/
├── controller/
├── dao/
├── dto/
├── exceptions/
├── iofiles/
├── model/
├── util/
├── HelloApplication.java
├── HelloController.java
