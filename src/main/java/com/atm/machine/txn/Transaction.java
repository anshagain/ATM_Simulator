package com.atm.machine.txn;

import com.atm.machine.Machine;
import com.atm.machine.Session;
import com.atm.machine.banking.Card;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Transaction {

    private Machine machine;
    private Session session;
    private Card card;
    private Integer pin;

    protected Transaction(Machine machine, Session session, Card card, int pin) {

        this.machine = machine;
        this.session = session;
        this.card = card;
        this.pin = pin;
    }

    public static Transaction performTransaction(Machine machine, Session session, Card card,
            int pin) {

        switch (machine.getCustomerScreen().getChoice()) {
            case 0:
                return new Balance(machine, session, card, pin);
            default:
                return null;
        }
    }
}
