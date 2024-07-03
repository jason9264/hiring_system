Certainly! Here's a combined README file for your Java program, focusing on setup and basic usage without detailed testing information:

---

# Wolf Hire Java Application

## Overview

The Wolf Hire Java application is designed to manage positions and applications within an educational setting. It allows users to track positions, manage applications, and perform various administrative tasks.

## Folder Structure

```
hiring_program
│   test
│   test-files
│   src
│   └── edu
│       └── ncsu
│           └── csc216
│               └── wolf_hire
│                   ├── view
│                   │   └── WolfHireGUI.java
│                   ├── model
│                   │   ├── manager
│                   │   │   └── WolfHire.java
│                   │   └── Position.java
│                   ├── io
│                   │   ├── PositionWriter.java
│                   │   └── PositionReader.java
│                   ├── command
│                   │   └── Command.java
│                   └── application
│                       └── Application.java
```

## Getting Started

To run the Wolf Hire application:

1. **Clone the repository**:
   ```bash
   git clone <repository_url>
   cd hiring_program
   ```

2. **Compile the Java files**:
   ```bash
   javac src/edu/ncsu/csc216/wolf_hire/*.java
   ```

3. **Run the application**:
   ```bash
   java -cp src edu.ncsu.csc216.wolf_hire.view.WolfHireGUI
   ```

## Usage

### Loading Positions and Applications

- Start the Wolf Hire application.
- Choose a position file (e.g., `position1.txt` or `position2.txt`) to load positions and associated applications.

### Managing Positions

- Add new positions through the application interface.
- Edit existing positions, including changing statuses (e.g., Submitted, Hired, Inactive).

### Managing Applications

- View applications associated with each position.
- Edit application details, such as applicant information and application status.

### Saving Changes

- Save the current state of positions and applications using the application's save feature.

## Contributors

- Jason Wang
