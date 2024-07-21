package com.railway.reservation_system.utils.date;

import com.railway.reservation_system.exception.InvalidDateException;

/**
 * Custom date handler to handle dates in this application 
 * 
 * @author amanfoundongithub 
 */
public class DateConvertor {
    
    
    public DateConvertor(){

    }

    /**
     * Converts String date to Java Object
     * @param date in DD-MM-YYYY format
     * @throws InvalidDateException if date is invalid
     */
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
