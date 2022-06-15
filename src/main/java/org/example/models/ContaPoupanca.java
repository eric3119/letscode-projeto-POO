package org.example.models;

import java.math.BigDecimal;

import org.example.interfaces.Rentabilidade;

public class ContaPoupanca extends Conta implements Rentabilidade {
    private BigDecimal rendimentos;

    public BigDecimal getRendimentos() {
        return rendimentos;
    }

    public ContaPoupanca setRendimentos(BigDecimal rendimentos) {
        this.rendimentos = rendimentos;
        return this;
    }
}
