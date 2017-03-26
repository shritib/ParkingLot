import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shriti.bharani on 3/25/17.
 */
public class Parking {

    private ArrayList<Integer> availableSlots;
    private HashMap<Integer, Car> slotMap;
    private HashMap<String, Integer> registrationSlotMap;
    private HashMap<String, ArrayList<Integer>> colorSlotMap;

    public Parking() {
        availableSlots = new ArrayList<Integer>();
        slotMap = new HashMap<Integer, Car>();
        registrationSlotMap = new HashMap<String, Integer>();
        colorSlotMap = new HashMap<String, ArrayList<Integer>>();
    }

    public void createParkingLot(int slots) {
        for (int i=1; i<=slots; i++) {
            availableSlots.add(i);
        }
        System.out.println("Created a parking lot with " + slots + " slots");
    }

    public void parkCar(Car car) {
        if (availableSlots.size() > 0) {
            Collections.sort(availableSlots);
            Integer availableSlotNumber = availableSlots.get(0);
            slotMap.put(availableSlotNumber, car);
            registrationSlotMap.put(car.getRegistrationNumber(), availableSlotNumber);
            if (colorSlotMap.get(car.getColour()) != null) {
                ArrayList<Integer> mappedSlots = colorSlotMap.get(car.getColour());
                mappedSlots.add(availableSlotNumber);
                colorSlotMap.put(car.getColour(), mappedSlots);
            } else {
                ArrayList<Integer> mappedSlots = new ArrayList<Integer>();
                mappedSlots.add(availableSlotNumber);
                colorSlotMap.put(car.getColour(), mappedSlots);
            }
            availableSlots.remove(0);
            System.out.println("Allocated slot number: " + availableSlotNumber.toString());
        } else {
            System.out.println("Sorry, parking lot is full");
        }
    }

    public void leaveCar(Integer slot) {
        Car car = slotMap.get(slot);
        if (car != null) {
            registrationSlotMap.remove(car.getRegistrationNumber());
            ArrayList<Integer> mappedSlots = colorSlotMap.get(car.getColour());
            for(int i=0; i<mappedSlots.size(); i++) {
                if(mappedSlots.get(i) == slot) {
                    mappedSlots.remove(i);
                }
            }
            slotMap.remove(slot);
            availableSlots.add(slot);
            System.out.println("Slot number " + slot.toString() + " is free");
        } else {
            System.out.println("Not found");
        }
    }

    public void showStatus() {
        System.out.println("Slot No.\tRegistration No\tColour" );
        if (slotMap != null) {
            for(Map.Entry<Integer, Car> entry : slotMap.entrySet()) {
                System.out.println(entry.getKey().toString() + "\t" + entry.getValue().getRegistrationNumber() + "\t"
                                           + entry.getValue().getColour());
            }
        }
    }

    public void getSlotByColour(String colour) {
        ArrayList<Integer> mappedSlots = colorSlotMap.get(colour);
        if (mappedSlots != null) {
            int i;
            for (i=0; i<mappedSlots.size() - 1; i++)  {
                System.out.print(mappedSlots.get(i) + ", ");
            }
            System.out.print(mappedSlots.get(i)+"\n");
        }
    }

    public void getSlotByRegistrationNo(String registrationNumber) {
        Integer slot = registrationSlotMap.get(registrationNumber);
        if(slot != null) {
            System.out.println(slot.toString());
        } else {
            System.out.println("Not found");
        }
    }

    public void getCarsByColour(String colour) {
        ArrayList<Integer> mappedSlots = colorSlotMap.get(colour);
        if (mappedSlots != null) {
            int i;
            for (i=0; i<mappedSlots.size() - 1; i++)  {
                System.out.print(slotMap.get(mappedSlots.get(i)).getRegistrationNumber() + ", ");
            }
            System.out.print(slotMap.get(mappedSlots.get(i)).getRegistrationNumber()+"\n");
        }
    }
}
