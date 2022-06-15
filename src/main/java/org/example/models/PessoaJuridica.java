package org.example.models;

public class PessoaJuridica extends Pessoa {
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public PessoaJuridica setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }
}
