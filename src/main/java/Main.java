import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by shriti.bharani on 3/25/17.
 */
public class Main {

    public static void main(String args[]) {
        if(args.length < 2) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            Parking parking = null;
            while (true) {
                try {
                    String input = bufferedReader.readLine();
                    String inputCommand[] = input.trim().split(" ");

                    if (inputCommand[0].equalsIgnoreCase("create_parking_lot")) {
                        if (inputCommand.length != 2) {
                            System.out.println("Not found");
                        } else {
                            //Create Parking slot
                            parking = new Parking();
                            parking.createParkingLot(Integer.valueOf(inputCommand[1]));
                        }
                    } else if (inputCommand[0].equalsIgnoreCase("park")) {
                        if (inputCommand.length != 3) {
                            System.out.println("Not found");
                        } else {
                            // Park car
                            if(parking != null) {
                                Car car = new Car();
                                car.setRegistrationNumber(inputCommand[1]);
                                car.setColour(inputCommand[2]);
                                parking.parkCar(car);
                            } else {
                                System.out.println("Not found");
                            }
                        }
                    } else if (inputCommand[0].equalsIgnoreCase("leave")) {
                        if (inputCommand.length != 2) {
                            System.out.println("Not found");
                        } else {
                            //Empty parking slot
                            if (parking != null)
                                parking.leaveCar(Integer.valueOf(inputCommand[1]));
                            else
                                System.out.println("Not found");
                        }
                    } else if (inputCommand[0].equalsIgnoreCase("status")) {
                        if (inputCommand.length != 1) {
                            System.out.println("Not found");
                        } else {
                            //Status of slot
                            if (parking != null) {
                                parking.showStatus();
                            } else
                                System.out.println("Not found");
                        }
                    } else if (inputCommand[0].equalsIgnoreCase("registration_numbers_for_cars_with_colour")) {
                        if (inputCommand.length != 2) {
                            System.out.println("Not found");
                        } else {
                            //regNoColourMap
                            if (parking != null) {
                                parking.getCarsByColour(inputCommand[1]);
                            } else
                                System.out.println("Not found");
                        }
                    } else if (inputCommand[0].equalsIgnoreCase("slot_numbers_for_cars_with_colour")) {
                        if (inputCommand.length != 2) {
                            System.out.println("Not found");
                        } else {
                            //colourSlotMap
                            if (parking != null) {
                                parking.getSlotByColour(inputCommand[1]);
                            } else
                                System.out.println("Not found");
                        }
                    } else if (inputCommand[0].equalsIgnoreCase("slot_number_for_registration_number")) {
                        if (inputCommand.length != 2) {
                            System.out.println("Not found");
                        } else {
                            //regNoSlotMap
                            if (parking != null) {
                                parking.getSlotByRegistrationNo(inputCommand[1]);
                            } else
                                System.out.println("Not found");
                        }
                    } else {
                        // Do nothing
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
