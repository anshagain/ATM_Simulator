package com.atm.machine;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.atm.machine.banking.Card;
import com.atm.machine.exception.AtmException;

/**
 * Test case for ATM Simulator.
 */
@TestMethodOrder(OrderAnnotation.class)
public class AtmSimulatorTest {

    CustomerScreen customerScreen = new CustomerScreen();
    Card card = null;

    @Test
    @Order(1)
    public void testIncorrectCardSession() throws AtmException {

        Session session = new Session(new Machine(card, customerScreen));
        assertThrows(AtmException.class, () -> session.doSession());
    }

    @Test
    @Order(2)
    public void testIncorrectPinSession() throws AtmException {

        card = new Card(1234);
        Session session = new Session(new Machine(card, customerScreen));
        assertThrows(AtmException.class, () -> session.doSession());
    }

    @Test
    @Order(3)
    public void testPerformTxnSession() throws AtmException {

        card = new Card(1234);
        customerScreen.setChoice(0);
        customerScreen.setPin(1234);
        Session session = new Session(new Machine(card, customerScreen));
        session.doSession();
    }
}
