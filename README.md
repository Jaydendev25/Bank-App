# Bank-App
## Project Description 
This Banking Application is a simple Java program that allows users to perform various banking operations such as account registration, login, deposit, withdraw, transfer funds, and view transaction history. The graphical user interface (GUI) is built using Java Swing, providing an intuitive and user-friendly experience.
### Features
**Account Registration:** Allows the user to create an account in the database, requiring a username and password.  
**Login:** Registered users can securely login to their account by providing a username and password, matching the currently registered accounts.  
**Deposit:** Users can deposit funds into their wallet by specifying the amount.  
**Withdraw:** Users can withdraw required funds by specifying a valid amount determined by the current balance in their wallet.  
**Transfer:** Users can transfer funds from the current account to another account by specifying the recipient's account name and the amount.  
**Transaction History:** Users can view their four most recent transactions, which include transaction type, date and amount.
### Technologies Used:
**Java Swing:** For building the graphical user interface.  
**MySQL:** For database management, storing user account information and transaction history.  
**Java Database Connectivity (JDBC):** To establish a connection between the Java application and the MySQL database for executing SQL queries.  
### Getting Started:
1. Clone the repository to your local machine.
2. Set up the MySQL database by creating a new schema named javabase and executing the provided SQL script to create the necessary tables.
3. Configure the database connection parameters in the Java application server class to match your MySQL server settings (username and password).
4. Compile and run the Java program.
