package se.lexicon.data.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Customer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDaoImplTest {

    private CustomerDaoImpl testObject;



    @BeforeEach
    public void setUp() {
        testObject = new CustomerDaoImpl();
    }

    @Test
    public void testCreateCustomer() {
         Customer testCustomer = new Customer("Jenny","8675309");

         Customer actualValue = testObject.create(testCustomer);


        //Assert
        assertEquals(testCustomer,actualValue);
    }

    @Test
    public void findById() {
        Customer testCustomer = new Customer(1001,"Jenny","8675309");
        testObject.create(testCustomer);


        //Assert
        assertTrue(testObject.find(1001).isPresent());


    }

    @Test
    public void testNonExistentCustomer() {
        Customer testCustomer = new Customer(1001,"Jenny","8675309");
        testObject.create(testCustomer);

        assertFalse(testObject.find(2002).isPresent());

    }

    @Test
    public void testRemoveCustomer() {
        Customer testCustomer = new Customer(1001,"Jenny","8675309");
        testObject.create(testCustomer);
        testObject.remove(1001);


        assertTrue(testObject.findAll().isEmpty());
    }

    @Test
    public void testRemoveNonExistentCustomer() {
        Customer testCustomer = new Customer(1001,"Jenny","8675309");
        testObject.create(testCustomer);

        assertFalse(testObject.remove(2002));
    }

    @Test
    public void testFindAllCustomers() {
        Customer testCustomer = new Customer(1001,"Jenny","8675309");
        testObject.create(testCustomer);

        assertFalse(testObject.findAll().isEmpty());


    }

    @Test
    public void testFindAllCustomersEmptyList() {
        assertTrue(testObject.findAll().isEmpty());


    }

}
