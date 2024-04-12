package se.lexicon.data.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.ParkingSpot;

import static org.junit.jupiter.api.Assertions.*;


public class ParkingSpotDaoImplTest {
    private ParkingSpotDaoImpl testObject;

    @BeforeEach
    public void setup() { testObject = new ParkingSpotDaoImpl();}

    @Test
    public void testCreateParkingSpot() {
        ParkingSpot testParkingSpot = new ParkingSpot(1001,1);
        ParkingSpot actualValue = testObject.create(testParkingSpot);

        assertEquals(testParkingSpot,actualValue);
    }
    @Test
    public void testFindBySpotNumber() {
        ParkingSpot testParkingSpot = new ParkingSpot(1001,1);
        testObject.create(testParkingSpot);

        assertTrue(testObject.find(1001).isPresent());

    }
    @Test
    public void testNonExistingSpotNumber() {
        ParkingSpot testParkingSpot = new ParkingSpot(1001,1);
        testObject.create(testParkingSpot);

        assertFalse(testObject.find(2002).isPresent());

    }
    @Test
    public void testRemoveParkingSpot() {
        ParkingSpot testParkingSpot = new ParkingSpot(1001,1);
        testObject.create(testParkingSpot);

        assertTrue(testObject.remove(1001));
        assertFalse(testObject.find(1001).isPresent());

    }
    @Test
    public void testRemoveNonExistingParkingSpot() {
        boolean removed = testObject.remove(2002);

        assertFalse(removed);
    }
    @Test
    public void testFindAllParkingSpots() {
        ParkingSpot testParkingSpot = new ParkingSpot(1001, 1);
        testObject.create(testParkingSpot);

        assertFalse(testObject.findAll().isEmpty());

    }
    @Test
    public void testFindAllParkingSpotsEmptyList() {assertTrue(testObject.findAll().isEmpty());}

    @Test
    public void testFindByAreaCode() {
        ParkingSpot testParkingSpot = new ParkingSpot(1001,1);
        testObject.create(testParkingSpot);

        assertTrue(testObject.findByAreaCode(1).contains(testParkingSpot));

    }

    @Test
    public void testFindByNonExistingAreaCode() {
        ParkingSpot testParkingSpot = new ParkingSpot(1001,1);
        testObject.create(testParkingSpot);

        assertFalse(testObject.findByAreaCode(2).contains(testParkingSpot));
    }

    @Test
    public void testOccupyParkingSpot() {
        ParkingSpot testParkingSpot = new ParkingSpot(1001,1);
        testObject.create(testParkingSpot);

        testObject.occupyParkingSpot(1001);

        assertTrue(testParkingSpot.isOccupied());

    }

    @Test
    public void testOccupyOccupiedParkingSpot() {
        ParkingSpot testParkingSpot = new ParkingSpot(1001,1);
        testObject.create(testParkingSpot);
        testObject.occupyParkingSpot(1001);

        assertThrows(IllegalArgumentException.class, ()->testObject.occupyParkingSpot(1001));
    }

    @Test
    public void testOccupyNonExistingParkingSpot() {
        ParkingSpot testParkingSpot = new ParkingSpot(1001,1);
        testObject.create(testParkingSpot);

        assertThrows(IllegalArgumentException.class, ()->testObject.occupyParkingSpot(2002));
    }

    @Test
    public void testVacateParkingSpot() {
        ParkingSpot testParkingSpot = new ParkingSpot(1001,1);
        testObject.create(testParkingSpot);
        testObject.occupyParkingSpot(1001);
        testObject.vacateParkingSpot(1001);

        assertFalse(testParkingSpot.isOccupied());
    }

    @Test
    public void testVacateNonOccupiedParkingSpot() {
        ParkingSpot testParkingSpot = new ParkingSpot(1001,1);
        testObject.create(testParkingSpot);

        assertThrows(IllegalArgumentException.class, ()->testObject.vacateParkingSpot(1001));


    }

    @Test
    public void testVacateNonExistingParkingSpot() {
        ParkingSpot testParkingSpot = new ParkingSpot(1001,1);
        testObject.create(testParkingSpot);

        assertThrows(IllegalArgumentException.class, ()->testObject.vacateParkingSpot(2002));


    }


}
