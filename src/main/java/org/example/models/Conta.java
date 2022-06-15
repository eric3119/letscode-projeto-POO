package org.example.models;

import java.math.BigDecimal;

public abstract class Conta extends Model {
    private Pessoa pessoa;

    private BigDecimal saldo;

    public BigDecimal getSaldo() {
        return saldo;
    }

    public <T extends Conta> T setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
        
        @SuppressWarnings("unchecked")
        T ret = (T) this;
        
        return ret;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public <T extends Conta> T setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        
        @SuppressWarnings("unchecked")
        T ret = (T) this;

        return ret;
    }

    @Override
    public String toString() {
        return String.format("Conta {id = %d; saldo = %s}", this.getId(), this.getSaldo().toString());
    }
}
