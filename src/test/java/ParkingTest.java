import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * Created by shriti.bharani on 3/26/17.
 */
public class ParkingTest {

    ArrayList<Integer> availableSlots;
    HashMap<Integer, Car> slotMap;
    HashMap<String, Integer> registrationSlotMap;
    HashMap<String, ArrayList<Integer>> colorSlotMap;
    Parking parking;
    Car car;

    @BeforeMethod
    public void setUpTests() {
        availableSlots = new ArrayList<Integer>();
        slotMap = new HashMap<Integer, Car>();
        registrationSlotMap = new HashMap<String, Integer>();
        colorSlotMap = new HashMap<String, ArrayList<Integer>>();
        parking = new Parking();
        parking.createParkingLot(6);
        car = new Car();
        car.setRegistrationNumber("KA-01-HH-1234");
        car.setColour("White");

    }

    @Test
    public void parkCarTest() {
        Integer actual = parking.parkCar(car);
        Integer expected = 1;
        assertEquals(expected,actual);
    }

    @Test
    public void parkCarTestWithFailure() {
        Integer actual = parking.parkCar(car);
        Integer expected = 2;
        assertNotEquals(expected, actual);
    }

    @Test
    public void leaveCarTest() {
        parking.parkCar(car);
        Integer actual = parking.leaveCar(1);
        Integer expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    public void leaveCarTestWithFailure() {
        Integer actual = parking.leaveCar(1);
        assertEquals(actual, null);
    }

    @Test
    public void getSlotByColourTest() {
        parking.parkCar(car);
        ArrayList<Integer> actualMappedSlots = parking.getSlotByColour("White");
        ArrayList<Integer> expectedMappedSlots = new ArrayList<Integer>();
        expectedMappedSlots.add(1);
        assertEquals(actualMappedSlots, expectedMappedSlots);
    }

    @Test
    public void getSlotByColourTestWithFailure() {
        parking.parkCar(car);
        ArrayList<Integer> actualMappedSlots = parking.getSlotByColour("White");
        ArrayList<Integer> expectedMappedSlots = new ArrayList<Integer>();
        expectedMappedSlots.add(2);
        assertNotEquals(actualMappedSlots, expectedMappedSlots);
    }

    @Test
    public void getSlotByRegistrationNoTest() {
        parking.parkCar(car);
        Integer actualSlot = parking.getSlotByRegistrationNo(car.getRegistrationNumber());
        Integer expectedSlot = 1;
        assertEquals(actualSlot, expectedSlot);
    }

    @Test
    public void getSlotByRegistrationNoTestWithFailure() {
        parking.parkCar(car);
        Integer actualSlot = parking.getSlotByRegistrationNo(car.getRegistrationNumber());
        Integer expectedSlot = 2;
        assertNotEquals(actualSlot, expectedSlot);
    }
}
