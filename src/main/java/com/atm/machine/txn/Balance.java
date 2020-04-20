package com.atm.machine.txn;

import com.atm.machine.Machine;
import com.atm.machine.Session;
import com.atm.machine.banking.Card;

public class Balance extends Transaction {

    public Balance(Machine machine, Session session, Card card, int pin) {

        super(machine, session, card, pin);
    }

    public String getBalance() {

        return "$30";
    }
}
