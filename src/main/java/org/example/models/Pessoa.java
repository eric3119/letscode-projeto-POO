package org.example.models;

public abstract class Pessoa extends Model {
    private String nome;

    public String getNome() {
        return nome;
    }

    public <T extends Pessoa> T setNome(String nome) {
        this.nome = nome;

        @SuppressWarnings("unchecked")
        T ret = (T) this;

        return ret;
    }

}
