
Student Login Authentication Application

This Java application provides a straightforward authentication mechanism for student logins. The primary component is the Authentication class, responsible for validating user credentials. It utilizes a properties file named "credentials" to store and retrieve hardcoded username and password information.

Features:
Authentication Logic:

The Authentication class includes a logIn method to validate user login attempts against stored credentials.
Username and password information is retrieved from the "credentials" properties file.
JUnit Testing:

The project incorporates JUnit test cases (AuthenticationTest.java) to ensure the correctness of the authentication logic.
Tests cover scenarios of both successful and unsuccessful login attempts.
Maven Integration:

The project is structured using Maven, with dependencies specified in the pom.xml file.
Maven facilitates the building, testing, and managing of project dependencies.
Project Structure:
markdown
Copy code
- src
  - main
    - java
      - accounts
        - student
          - login
            - Authentication.java
  - test
    - java
      - accounts
        - student
          - login
            - AuthenticationTest.java
- pom.xml
How to Use:
Authentication Class (Authentication.java):

Customize the logic or integrate this class into your project for student login authentication.
JUnit Test Cases (AuthenticationTest.java):

Use as a reference for creating additional test cases or ensuring the correctness of the authentication logic.
Running Tests:
Execute the following Maven command to run the JUnit tests:

bash
Copy code
mvn test
How to Build:
Use the following Maven command to clean, compile, and install the project:

bash
Copy code
mvn clean install
Notes:
Ensure that the "credentials" properties file contains keys "username" and "password" with appropriate values.
Feel free to adapt and extend this authentication mechanism according to your project requirements. If you have any questions or need further assistance, please don't hesitate to ask!