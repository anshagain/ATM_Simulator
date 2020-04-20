package com.atm.machine;

import java.util.Objects;

import com.atm.machine.banking.Card;
import com.atm.machine.exception.AtmException;
import com.atm.machine.txn.Transaction;

public class Session {

    private static final int CARD_READING_SESSION_STATE = 1;
    private static final int READING_PIN_SESSION_STATE = 2;
    private static final int PERFORM_TRANSACTION_STATE = 3;
    private static final int CARD_EJECT_SESSION_STATE = 4;
    private static final int FINAL_SESSION_STATE = 5;

    private Machine machine;
    private int sessionState; // Session State
    private Integer pin;

    public Session(Machine machine) {

        this.machine = machine;
        sessionState = CARD_READING_SESSION_STATE;
    }

    public void doSession() throws AtmException {

        CardReader cardReader = new CardReader(machine);
        Card card = null;
        while (sessionState != FINAL_SESSION_STATE) {
            switch (sessionState) {
                case CARD_READING_SESSION_STATE:
                    if (cardReader.canReadCardFromMachine())
                        sessionState = READING_PIN_SESSION_STATE;
                    else {
                        cardReader.ejectCard();
                        sessionState = CARD_EJECT_SESSION_STATE;
                        throw new AtmException("Card is Damaged");
                    }
                    break;
                case READING_PIN_SESSION_STATE:
                    pin = machine.getCustomerScreen().getPin();
                    if (Objects.nonNull(pin)) {
                        sessionState = PERFORM_TRANSACTION_STATE;
                    } else {
                        sessionState = CARD_EJECT_SESSION_STATE;
                        throw new AtmException("Pin is not entered");
                    }
                    break;
                case PERFORM_TRANSACTION_STATE:
                    Transaction.performTransaction(machine, this, card, pin);
                    sessionState = CARD_EJECT_SESSION_STATE;
                    break;
                case CARD_EJECT_SESSION_STATE:
                    sessionState = FINAL_SESSION_STATE;
                    break;

            }
        }
    }
}
