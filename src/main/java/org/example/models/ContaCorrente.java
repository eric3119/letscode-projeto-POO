package org.example.models;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {
    private BigDecimal limite;

    public BigDecimal getLimite() {
        return limite;
    }

    public ContaCorrente setLimite(BigDecimal limite) {
        this.limite = limite;
        return this;
    }
}
