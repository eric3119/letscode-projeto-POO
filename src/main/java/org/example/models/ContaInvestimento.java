package org.example.models;

import java.math.BigDecimal;

import org.example.interfaces.Rentabilidade;

public class ContaInvestimento extends Conta implements Rentabilidade {
    private BigDecimal rendimentos;

    public BigDecimal getRendimentos() {
        return rendimentos;
    }

    public ContaInvestimento setRendimentos(BigDecimal rendimentos) {
        this.rendimentos = rendimentos;
        return this;
    }

}
