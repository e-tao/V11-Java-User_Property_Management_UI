# V11 Java Final Project

## Introduction
A database-driven application written in Java with JavaFX UI and MariaDB.

The application is required to be able to:
- list, add, modify and delete records through user interface;
- work with database table that contains relationship with another table (e.g. a join table/view);
- hava 3rd database table for another feature (e.g. lookup table);
- use a single MariaDB database;
- have a user focused application (e.g. design, layout, etc)

## Features 
The application is designed for the company's employee and contains the following functions: 
- Employee can query the database for booking, current properties and log without a employee number (no log record);
- Employee can manipulate customer(user) data ONLY when employee number entered;
- Employee can UPDATE and DELETE customer(user) data and the operations will create a log record in the database;
- Employee can purge invalid account in the system (account never activated)
- Log file keeps records of employee number, actual operation, operation data, and operation time.

## Tech
The project uses the following language, toolkit, IDE etc...

- [JAVA] - The sole language for the application
- [JAVA FX] - A Java GUI toolkit
- [MariaDB] - RDBMS
- [Eclipse IDE] - IDE for Java development
- [git] - version control

## Screenshot
![Application Screenshot](https://raw.githubusercontent.com/ethantao-repo/V11-PRJ-User_Property_Management_UI/master/screenshot/V11_Project_ScreenShot.png)
