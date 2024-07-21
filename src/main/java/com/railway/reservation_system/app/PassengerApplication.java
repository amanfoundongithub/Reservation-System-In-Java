package com.railway.reservation_system.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.railway.reservation_system.builder.PassengerBuilder;
import com.railway.reservation_system.exception.LogOutException;
import com.railway.reservation_system.exception.LoginException;
import com.railway.reservation_system.exception.SignUpException;
import com.railway.reservation_system.model.Passenger;
import com.railway.reservation_system.repository.PassengerCRUDInterface;
import com.railway.reservation_system.utils.validator.LoginValidator;

import java.util.Optional;

@SpringBootApplication
public class PassengerApplication extends BaseApplication {

    @Autowired
    private PassengerCRUDInterface passengerCRUDInterface;

    private PassengerBuilder passengerBuilder = new PassengerBuilder();
    private LoginValidator   loginValidator   = new LoginValidator();

    private String emailID = "";

    public PassengerApplication() {

    }

    public String getEmail() {
        return emailID;
    }

    public Optional<Passenger> getPassenger() {
        return passengerCRUDInterface.findByEmail(emailID);
    }

    // Log In
    public void logIn() throws LoginException {
        try {
            String email = input("Enter the email: ");
            String pass = input("Enter password: ");
            if (passengerCRUDInterface.findByEmailAndPassword(email, pass).isPresent()) {
                emailID = email;
            }
        } catch (Exception e) {
            throw new LoginException(e);
        }
    }

    // Sign Up
    public void signUp() throws SignUpException {
        try {
            String email = input("Enter the email: ");
            if (passengerCRUDInterface.findByEmail(email).isPresent()) {
                print("Email already exists, please Log In");
                return;
            }

            passengerBuilder.reset();
            String password = input("Set a password: ");

            if (loginValidator.validate(email, password) == false) {
                print("Email/Password invalid");
            }

            passengerBuilder.addUniqueConstraints(email, password);

            print("--- OTHER DETAILS ---");
            // Other details
            String firstName = input("First Name:");
            String lastName = input("Last Name:");
            String gender = input("Gender:");
            String DOB = input("DOB : ");

            passengerBuilder.addDetails(firstName, lastName, gender, DOB);

            passengerCRUDInterface.save(passengerBuilder.build());

            print("--- SAVED ---");

        } catch (Exception e) {
            throw new SignUpException(e);
        }
    }

    // Log Out
    public void logOut() throws LogOutException {
        try {
            emailID = "";
            print("LOGOUT SUCCESSFUL!");
        } catch (Exception e) {
            throw new LogOutException();
        }
    }

}
