package org.example.models;

import java.math.BigDecimal;

public class ContaInvestimento extends Conta {
    private BigDecimal rendimentos;

    public BigDecimal getRendimentos() {
        return rendimentos;
    }

    public ContaInvestimento setRendimentos(BigDecimal rendimentos) {
        this.rendimentos = rendimentos;
        return this;
    }

}
