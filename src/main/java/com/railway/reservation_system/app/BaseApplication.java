package com.railway.reservation_system.app;

import java.util.Scanner;

import com.railway.reservation_system.utils.exception.InputException;
import com.railway.reservation_system.utils.exception.PrintException;

/**
 * Base class containing input operations and abstractions 
 * 
 * @author amanfoundongithub 
 * 
 */
public class BaseApplication {

    protected Scanner scanner = new Scanner(System.in);

    public BaseApplication() {

    }

    /**
     * Takes String as input
     * 
     * @param message to be shown to user
     * @return Input string
     * @throws InputException
     */
    protected String input(String message) throws InputException {
        try {
            System.out.print(message);
            return scanner.next();
        } catch (Exception e) {
            throw new InputException(e);
        }
    }

    /**
     * Takes an integer as input
     * 
     * @param message to be displayed to user
     * @return Integer
     * @throws InputException
     */
    protected int inputInt(String message) throws InputException {
        try {
            System.out.print(message);
            return scanner.nextInt();
        } catch (Exception e) {
            throw new InputException(e);
        }
    }

    /**
     * Prints a given line
     * 
     * @param message to be printed
     * @throws PrintException
     */
    protected void print(String message) throws PrintException {
        try {
            System.out.println(message);
        } catch (Exception e) {
            throw new PrintException(e);
        }
    }

}
