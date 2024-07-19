package com.railway.reservation_system.utils.date;

import com.railway.reservation_system.utils.exception.InvalidDateException;

/**
 * Adapts date string in Java
 * 
 * @author amanfoundongithub 
 */
public class DateAdapter {
    
    /**
     * Converts String date to Java Object
     * 
     * @param String date
     * @throws InvalidDateException if date is invalid
     */
    public DateAdapter(){

    }

    public Date convertToDate(String date) throws InvalidDateException {
        try{
            String[] tokens = date.split("-");
            
            int[] quantities = new int[3];
            for (int i = 0; i < tokens.length; i++) {
                quantities[i] = Integer.valueOf(tokens[i].strip());
            }

            Date result = new Date();
            
            result.setDate(quantities[0]);
            result.setMonth(quantities[1]);
            result.setYear(quantities[2]); 

            return result;
        }
        catch(Exception e){
            throw new InvalidDateException();
        }
    }
    
    
}
