package com.railway.reservation_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


import com.railway.reservation_system.app.PassengerApplication;
import com.railway.reservation_system.app.TrainApplication;


import java.util.Scanner;

@SpringBootApplication
@EnableMongoRepositories
public class ReservationSystemApplication implements CommandLineRunner {

	@Autowired 
	private PassengerApplication passengerApplication;

	@Autowired
	private TrainApplication trainApplication;


	private Scanner scanner = new Scanner(System.in);


	public static void main(String[] args) {
		SpringApplication.run(ReservationSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
		// Initialize the client
		System.out.println("Initializing Command Line Runner...");

		int input = 1;

		trainApplication.populate();

		while (input != -1) {
			
			System.out.print("Enter number: ");

			input = scanner.nextInt();

			if (input == 1) {
				System.out.println("--------- LOGIN -------------------");
				passengerApplication.logIn();
				System.out.println("-----------------------------------");
			} else if (input == 2) {
				System.out.println("--------- SIGN UP -----------------");
				passengerApplication.signUp();
				System.out.println("-----------------------------------");
			} else if (input == 3) {
				System.out.println("--------- LOGOUT ------------------");
				passengerApplication.logOut();
				System.out.println("-----------------------------------");
			} else if (input == 4) {
				System.out.println("----------- TRAIN SEARCH ----------");
				trainApplication.searchTrains();
				System.out.println("-----------------------------------");
			} else if (input == 5) {
				System.out.println("--------- TRAIN BOOKING -----------");
				trainApplication.bookTrain(passengerApplication.getPassenger());
				System.out.println("-----------------------------------");
			}

		}

		System.out.println("Thank you for using application");

		scanner.close();
		System.exit(0);
	}

}
