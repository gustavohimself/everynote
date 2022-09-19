package com.everynote.db.bean;

public enum Prioridade {
    BAIXA(1), NORMAL(2), ALTA(3);

    private final int value;

    Prioridade(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
