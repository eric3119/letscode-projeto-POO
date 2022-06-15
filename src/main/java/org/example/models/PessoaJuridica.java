package org.example.models;

import java.math.BigDecimal;

import org.example.interfaces.TaxaSaque;

public class PessoaJuridica extends Pessoa implements TaxaSaque {
    private String cnpj;
    private final BigDecimal taxaSaque = BigDecimal.valueOf(0.005);

    public String getCnpj() {
        return cnpj;
    }

    public PessoaJuridica setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public BigDecimal getTaxaSaque() {
        return this.taxaSaque;
    }
}
