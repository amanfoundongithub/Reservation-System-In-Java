package com.railway.reservation_system.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.railway.reservation_system.exception.PriceHikeException;
import com.railway.reservation_system.exception.TicketException;
import com.railway.reservation_system.exception.TrainBookingException;
import com.railway.reservation_system.exception.TrainSearchException;
import com.railway.reservation_system.factory.DurontoFactory;
import com.railway.reservation_system.factory.RajdhaniFactory;
import com.railway.reservation_system.factory.ShatabdiFactory;
import com.railway.reservation_system.model.Passenger;
import com.railway.reservation_system.model.Ticket;
import com.railway.reservation_system.model.Train;
import com.railway.reservation_system.payment.CreditCardPayment;
import com.railway.reservation_system.payment.DebitCardPayment;
import com.railway.reservation_system.payment.PaymentGateway;
import com.railway.reservation_system.payment.UpiPayment;
import com.railway.reservation_system.repository.TicketCRUDInterface;
import com.railway.reservation_system.repository.TrainCRUDInterface;
import com.railway.reservation_system.utils.date.DateConvertor;
import com.railway.reservation_system.utils.station.StationName;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TrainApplication extends BaseApplication {

    @Autowired
    private TrainCRUDInterface trainCRUDInterface;

    @Autowired
    private TicketCRUDInterface ticketCRUDInterface;

    private PaymentGateway paymentGateway = new PaymentGateway();

    private CreditCardPayment creditCardPayment = new CreditCardPayment();
    private DebitCardPayment debitCardPayment = new DebitCardPayment();
    private UpiPayment upiPayment = new UpiPayment();

    public TrainApplication() {

    }

    public void populate() {
        /********************************************************
         * Populate with trains
         * 
         */

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
                    if (trainCRUDInterface.findByTrainNumber(trainNum).isEmpty()) {
                        trainCRUDInterface.save(rajdhaniTrain);
                        trainNum += 1;
                    }

                }
            }

        }

        trainNum = 121550;
        for (StationName openingStation : StationName.values()) {
            for (StationName closingStation : StationName.values()) {
                if (openingStation != closingStation) {
                    Train durontoTrain = durontoFactory.createTrain(trainNum, openingStation, closingStation);

                    if (trainCRUDInterface.findByTrainNumber(trainNum).isEmpty()) {
                        trainCRUDInterface.save(durontoTrain);
                        trainNum += 1;
                    }

                }
            }

        }

        trainNum = 108001;
        for (StationName openingStation : StationName.values()) {
            for (StationName closingStation : StationName.values()) {
                if (openingStation != closingStation) {
                    Train shatabdiTrain = shatabdiFactory.createTrain(trainNum, openingStation, closingStation);

                    if (trainCRUDInterface.findByTrainNumber(trainNum).isEmpty()) {
                        trainCRUDInterface.save(shatabdiTrain);
                        trainNum += 1;
                    }

                }
            }

        }
    }

    public void searchTrains() throws TrainSearchException {
        try {
            String openingStation = input("Enter the opening station in CAPS:");
            String closingStation = input("Enter the closing station in CAPS:");

            StationName open = StationName.valueOf(openingStation);
            StationName closed = StationName.valueOf(closingStation);

            List<Train> listOfTrains = trainCRUDInterface.findAllopeningStationAndclosingStation(open, closed);

            System.out.println("LIST OF AVAILABLE TRAINS");
            for (Train train : listOfTrains) {
                if (train.getAvailableSeats() > 0) {
                    String description = "Available :\n";
                    description += "Type : " + train.getTrainType() + "\n";
                    description += "Number : " + train.getTrainNumber() + "\n";
                    description += "Seats Left : " + train.getAvailableSeats() + "\n";
                    description += "Ticket Price : " + train.getTicketPrice() + "\n";

                    print(description);
                }

            }
        } catch (Exception e) {
            throw new TrainSearchException(e);
        }
    }

    public void bookTrain(Optional<Passenger> traveller) throws TrainBookingException {
        try {
            if (traveller.isEmpty()) {
                print("Please login before booking tickets");
                return;
            }

            int trainNumber = inputInt("Enter train Number: ");

            Optional<Train> train = trainCRUDInterface.findByTrainNumber(trainNumber);

            if (train.isEmpty()) {
                print("Invalid train number");
                return;
            }

            // Get the values
            Passenger passenger = traveller.get();
            Train vehicle = train.get();

            if (vehicle.getAvailableSeats() <= 0) {
                print("Train is full, please select another train");
                return;
            }

            int noOfTickets = inputInt("Enter number of tickets: ");

            DateConvertor dateConvertor = new DateConvertor();

            String date = input("Enter the date of journey (DD-MM-YYYY): ");

            print("Payment : CC, DC, UPI \nRequest amount: " + (vehicle.getTicketPrice() * noOfTickets));
            String method = scanner.next();

            if (method.equals("CC")) {
                paymentGateway.setPaymentMethod(creditCardPayment);
            } else if (method.equals("DC")) {
                paymentGateway.setPaymentMethod(debitCardPayment);
            } else if (method.equals("UPI")) {
                paymentGateway.setPaymentMethod(upiPayment);
            }

            if (paymentGateway.pay(vehicle.getTicketPrice() * noOfTickets) == true) {
                Ticket ticket = new Ticket(passenger.getEmail(),
                        noOfTickets, dateConvertor.convertToDate(date),
                        trainNumber);
                ticket = ticketCRUDInterface.save(ticket);
                vehicle.add(ticket);

                // Update this
                trainCRUDInterface.save(vehicle);

                print("Saved passenger " + passenger.getFirstName());
            } else {
                print("Failed");
            }

        } catch (Exception e) {
            throw new TrainBookingException(e);
        }
    }

    public void getAllTickets(Optional<Passenger> traveller) throws TicketException {
        try {
            // Fetch all tickets
            if (traveller.isEmpty()) {
                print("Please login before using this");
                return;
            }

            Passenger passenger = traveller.get();

            List<Ticket> listOfTickets = ticketCRUDInterface.findAllByEmail(passenger.getEmail());

            print(listOfTickets.size() + " Tickets found for " + passenger.getFirstName() + " "
                    + passenger.getLastName());
            for (Ticket ticket : listOfTickets) {
                Train train = trainCRUDInterface.findByTrainNumber(ticket.getTrainNumber()).get();
                print("------");
                print("Date Of Journey:" + ticket.getDOJ().toString());
                print("Opening Station:" + train.getOpeningStation());
                print("Closing Station:" + train.getClosingStation());
                print("Train Number:" + ticket.getTrainNumber());
                print("Number of Traveller(s):" + ticket.getNoOfTickets());
                print("-----");
            }

        } catch (Exception e) {
            throw new TicketException(e);
        }
    }

    public void priceHike() throws PriceHikeException {
        try {
            float percentage = inputFloat("Enter percentage increase in prices:");
            print("PRICE HIKE STARTED...");

            for (Train train : trainCRUDInterface.findAll()) {
                float original_price = train.getTicketPrice();
                original_price = original_price * (1 + percentage / 100);

                train.setTicketPrice(original_price);
                trainCRUDInterface.save(train);
            }
            print("...PRICE HIKE DONE");
        } catch (Exception e) {
            throw new PriceHikeException(e);
        }

    }

    public void resetTrains() {

        for (Train train : trainCRUDInterface.findAll()) {
         
            ticketCRUDInterface.deleteAllById(train.getListOfTickets());
            
            train.reset();
            
            trainCRUDInterface.save(train);
        }

    }
}
