package com.railway.reservation_system.utils.date;

/**
 * Date object to handle dates
 * 
 * @author amanfoundongithub
 * 
 */
public class Date {
    
    private int date;
    private int month;
    private int year;

    public Date(){
        
    }

    public Date(int date, int month, int year){
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public int getDate(){
        return date;
    }

    public void setDate(int date){
        this.date = date;
    }

    public int getMonth(){
        return month;
    }

    public void setMonth(int month){
        this.month = month;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    @Override 
    public String toString(){
        return date + "-" + month + "-" + year;
    }
}
