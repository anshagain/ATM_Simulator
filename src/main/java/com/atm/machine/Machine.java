package com.atm.machine;

import com.atm.machine.banking.Card;

import lombok.Getter;

public class Machine {

    @Getter
    private Card card;

    @Getter
    private CustomerScreen customerScreen;

    public Machine(Card card, CustomerScreen customerScreen) {

        this.card = card;
        this.customerScreen = customerScreen;
    }

}
