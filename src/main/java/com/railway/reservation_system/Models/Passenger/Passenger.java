package com.railway.reservation_system.Models.Passenger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Passenger details
 * 
 * @author amanfoundongithub 
 * 
 */
@Document("passenger")
public class Passenger {
    
    @Id
    private String id;

    /**
     * Login credentials 
     * 
     */
    @Indexed(unique = true)
    private String email;

    private String password;
    
    /**
     * Passenger details 
     * 
     */
    private String firstName;
    private String lastName; 

    private String gender;
    private String dateOfBirth;

    /*
     * Constructor of Passenger class 
     */
    public Passenger(){

    }
    
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }

    
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }

    /**
     * First Name Parameters
     * 
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return this.firstName;
    }

    /**
     * Last Name
     * 
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return this.lastName;
    }

    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return this.gender;
    }

    public void setDOB(String DOB){
        this.dateOfBirth = DOB;
    }
    public String getDOB(){
        return this.dateOfBirth;
    }

    public String getId(){
        return this.id;
    }
    
}
