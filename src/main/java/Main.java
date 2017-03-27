import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by shriti.bharani on 3/25/17.
 */
public class Main {

    public static void main(String args[]) {
        BufferedReader bufferedReader = null;
        try {
            if (args.length < 1) {
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            } else if (args.length == 1) {
                bufferedReader = new BufferedReader(new FileReader(args[0].trim()));
            }
            Parking parking = null;
            while (true) {
                String input = bufferedReader.readLine();
                if (input != null) {
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
                            if (parking != null) {
                                Car car = new Car();
                                car.setRegistrationNumber(inputCommand[1]);
                                car.setColour(inputCommand[2]);
                                Integer allocatedSlot = parking.parkCar(car);
                                if (allocatedSlot != null)
                                    System.out.println("Allocated slot number: " + allocatedSlot.toString());
                                else
                                    System.out.println("Sorry, parking lot is full");
                            } else {
                                System.out.println("Not found");
                            }
                        }
                    } else if (inputCommand[0].equalsIgnoreCase("leave")) {
                        if (inputCommand.length != 2) {
                            System.out.println("Not found");
                        } else {
                            //Empty parking slot
                            if (parking != null){
                                Integer availableSlot = parking.leaveCar(Integer.valueOf(inputCommand[1]));
                                if (availableSlot != null)
                                    System.out.println("Slot number " + availableSlot.toString() + " is free");
                                else
                                    System.out.println("Not found");
                            }
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
                                ArrayList<Integer> mappedSlots = parking.getSlotByColour(inputCommand[1]);
                                if (mappedSlots != null) {
                                    int i;
                                    for (i=0; i<mappedSlots.size() - 1; i++)  {
                                        System.out.print(mappedSlots.get(i) + ", ");
                                    }
                                    System.out.print(mappedSlots.get(i)+"\n");
                                }
                            } else
                                System.out.println("Not found");
                        }
                    } else if (inputCommand[0].equalsIgnoreCase("slot_number_for_registration_number")) {
                        if (inputCommand.length != 2) {
                            System.out.println("Not found");
                        } else {
                            //regNoSlotMap
                            if (parking != null) {
                                Integer slot = parking.getSlotByRegistrationNo(inputCommand[1]);
                                if(slot != null) {
                                    System.out.println(slot.toString());
                                } else {
                                    System.out.println("Not found");
                                }
                            } else
                                System.out.println("Not found");
                        }
                    } else {
                        // Do nothing
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

    }
}
