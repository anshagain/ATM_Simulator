package com.atm.machine;

import java.util.Objects;

public class CardReader {

    private Machine machine;

    public CardReader(Machine machine) {

        this.machine = machine;
    }

    public boolean canReadCardFromMachine() {

        if (Objects.nonNull(machine.getCard()))
            return true;
        return false;
    }

    public void ejectCard() {

        System.out.println("Card is Ejected");
    }

}
