package com.railway.reservation_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.railway.reservation_system.Models.Passenger.Passenger;
import com.railway.reservation_system.Models.Passenger.PassengerBuilder;
import com.railway.reservation_system.utils.station.StationName;
import com.railway.reservation_system.Models.Train.Train;
import com.railway.reservation_system.Models.Train.Implementation.Factory.DurontoFactory;
import com.railway.reservation_system.Models.Train.Implementation.Factory.RajdhaniFactory;
import com.railway.reservation_system.Models.Train.Implementation.Factory.ShatabdiFactory;
import com.railway.reservation_system.Models.payment.CreditCardPayment;
import com.railway.reservation_system.Models.payment.DebitCardPayment;
import com.railway.reservation_system.Models.payment.PaymentGateway;
import com.railway.reservation_system.Models.payment.UpiPayment;
import com.railway.reservation_system.repository.PassengerCRUDInterface;
import com.railway.reservation_system.repository.TrainCRUDInterface;
import com.railway.reservation_system.utils.date.DateAdapter;
import com.railway.reservation_system.utils.exception.LogOutException;
import com.railway.reservation_system.utils.exception.LoginException;
import com.railway.reservation_system.utils.exception.SignUpException;
import com.railway.reservation_system.utils.exception.TrainBookingException;
import com.railway.reservation_system.utils.exception.TrainSearchException;
import com.railway.reservation_system.utils.validator.LoginValidator;

import java.util.Scanner;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ReservationSystemApplication implements CommandLineRunner {

	@Autowired
	PassengerCRUDInterface passengerCRUDInterface;

	@Autowired
	TrainCRUDInterface trainCRUDInterface;

	private PassengerBuilder passengerBuilder = new PassengerBuilder();

	private LoginValidator loginValidator = new LoginValidator();

	private Scanner scanner = new Scanner(System.in);

	private String emailID = "";
	private String password = "";

	private PaymentGateway paymentGateway = new PaymentGateway();

	private CreditCardPayment creditCardPayment = new CreditCardPayment();
	private DebitCardPayment debitCardPayment = new DebitCardPayment();
	private UpiPayment upiPayment = new UpiPayment();

	public static void main(String[] args) {
		SpringApplication.run(ReservationSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Initialize the client
		System.out.println("Initializing Command Line Runner...");

		/********************************************************
		 * Populate with trains
		 * 
		 */
		trainCRUDInterface.deleteAll();

		RajdhaniFactory rajdhaniFactory = new RajdhaniFactory();

		DurontoFactory durontoFactory = new DurontoFactory();

		ShatabdiFactory shatabdiFactory = new ShatabdiFactory();

		/**
		 * Define a regular Rajdhani train for connecting all cities
		 */

		int trainNum = 123450;
		for (StationName openingStation : StationName.values()) {
			for (StationName closingStation : StationName.values()) {
				if (openingStation != closingStation) {
					Train rajdhaniTrain = rajdhaniFactory.createTrain(trainNum, openingStation, closingStation);

					trainCRUDInterface.save(rajdhaniTrain);

					trainNum += 1;
				}
			}

		}

		trainNum = 121550;
		for (StationName openingStation : StationName.values()) {
			for (StationName closingStation : StationName.values()) {
				if (openingStation != closingStation) {
					Train durontoTrain = durontoFactory.createTrain(trainNum, openingStation, closingStation);

					trainCRUDInterface.save(durontoTrain);

					trainNum += 1;
				}
			}

		}

		trainNum = 108001;
		for (StationName openingStation : StationName.values()) {
			for (StationName closingStation : StationName.values()) {
				if (openingStation != closingStation) {
					Train shatabdiTrain = shatabdiFactory.createTrain(trainNum, openingStation, closingStation);

					trainCRUDInterface.save(shatabdiTrain);

					trainNum += 1;
				}
			}

		}

		/**
		 * Database populated with all the trains
		 * 
		 * **********************************
		 */

		int input = 1;

		while (input != -1) {
			System.out.print("Enter number: ");

			input = scanner.nextInt();

			if (input == 1) {
				System.out.println("--------- LOGIN -------------------");
				logIn();
				System.out.println("-----------------------------------");
			} else if (input == 2) {
				System.out.println("--------- SIGN UP -----------------");
				signUp();
				System.out.println("-----------------------------------");
			} else if (input == 3) {
				System.out.println("--------------- LOGOUT ------------");
				logOut();
				System.out.println("-----------------------------------");
			} else if (input == 4) {
				System.out.println("----------- TRAIN SEARCH ----------");
				searchTrain();
				System.out.println("-----------------------------------");
			} else if (input == 5) {
				System.out.println("--------- TRAIN BOOKING -----------");
				bookTrain();
				System.out.println("-----------------------------------");
			}

		}

		System.out.println("Thank you for using application");

		scanner.close();
		System.exit(0);
	}

	// Log In
	private void logIn() throws LoginException {
		try {
			String email = scanner.next();
			String pass = scanner.next();

			if (passengerCRUDInterface.findByEmailAndPassword(email, pass).isPresent()) {
				emailID = email;
				password = pass;
			}

		} catch (Exception e) {
			throw new LoginException();
		}
	}

	// Sign Up
	private void signUp() throws SignUpException {
		try {
			passengerBuilder.reset();

			if (emailID.length() > 0 && password.length() > 0) {
				return;
			}

			String email = scanner.next();
			String pass = scanner.next();

			if (loginValidator.validate(email, pass) == false) {
				return;
			}

			if (passengerCRUDInterface.findByEmailAndPassword(email, pass).isPresent()) {
				return;
			}

			passengerBuilder.addUniqueConstraints(email, pass);

			// Other details
			String firstName = scanner.next();
			String lastName = scanner.next();
			String gender = scanner.next();
			String DOB = scanner.next();

			passengerBuilder.addDetails(firstName, lastName, gender, DOB);

			Passenger createdPassenger = passengerBuilder.build();
			passengerCRUDInterface.save(createdPassenger);
		} catch (Exception e) {
			throw new SignUpException();
		}
	}

	// Log Out
	private void logOut() throws LogOutException {
		try {
			emailID = "";
			password = "";
		} catch (Exception e) {
			throw new LogOutException();
		}
	}

	// Train search using opening and closing stations
	private void searchTrain() throws TrainSearchException {
		try {
			String openingStation = scanner.next();
			String closingStation = scanner.next();

			StationName open = StationName.valueOf(openingStation);
			StationName closed = StationName.valueOf(closingStation);

			List<Train> listOfTrains = trainCRUDInterface.findAllopeningStationAndclosingStation(open, closed);

			System.out.println("LIST OF AVAILABLE TRAINS");
			for (Train train : listOfTrains) {
				System.out.println("Available :\n Model : " + train.getTrainModel() + "\n Number: "
						+ train.getTrainNumber() + "\n Available Seats: " + train.getAvailableSeats());
			}
		} catch (Exception e) {
			throw new TrainSearchException();
		}
	}

	// Booking train
	private void bookTrain() throws TrainBookingException {
		try {
			Optional<Passenger> passenger = passengerCRUDInterface.findByEmail(emailID);

			if (passenger.isEmpty()) {
				return;
			}

			int trainNumber = scanner.nextInt();

			Optional<Train> train = trainCRUDInterface.findByTrainNumber(trainNumber);

			if (train.isEmpty()) {
				return;
			}

			// Get the values
			Passenger traveller = passenger.get();
			Train vehicle = train.get();

			if (vehicle.getAvailableSeats() <= 0) {
				System.out.println("Train is full, please select another train");
				return;
			}

			int noOfTickets = scanner.nextInt();

			DateAdapter dateAdapter = new DateAdapter();

			System.out.println("Payment : CC, DC, UPI Request amount: " + (vehicle.getTicketPrice() * noOfTickets));
			String method = scanner.next();

			if (method.equals("CC")) {
				
				paymentGateway.setPaymentMethod(creditCardPayment);
			} else if (method.equals("DC")) {
				paymentGateway.setPaymentMethod(debitCardPayment);
			} else if (method.equals("UPI")) {
				paymentGateway.setPaymentMethod(upiPayment);
			}

			
			if (paymentGateway.pay() == true) {
				vehicle.add(traveller.getEmail(), noOfTickets, dateAdapter.convertToDate("17-12-2024"));

				// Update this
				trainCRUDInterface.save(vehicle);

				System.out.println("Saved passenger " + traveller.getFirstName());
			} else {
				System.out.println("Failed");
			}

		} catch (Exception e) {
			throw new TrainBookingException();
		}
	}
}
