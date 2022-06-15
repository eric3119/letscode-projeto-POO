package org.example.models;

import java.math.BigDecimal;

import org.example.interfaces.Limite;

public class ContaCorrente extends Conta implements Limite {
    private BigDecimal limite;

    public BigDecimal getLimite() {
        return limite;
    }

    public ContaCorrente setLimite(BigDecimal limite) {
        this.limite = limite;
        return this;
    }
}
