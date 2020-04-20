package com.atm.machine.banking;

import lombok.Getter;

public class Card {

    @Getter
    private int number;

    public Card(int number) {

        this.number = number;
    }

}
