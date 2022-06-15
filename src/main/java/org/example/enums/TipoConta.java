package org.example.enums;

public enum TipoConta {
    POUPANCA(1), CORRENTE(2), INVESTIMENTO(3);
    
    private int representation;

    TipoConta(int op) {
        this.representation = op;
    }

    public String getFormatted() {
        return this.representation + " - " + fromInt(this.representation).toString();
    }

    public static TipoConta fromInt(int op) {
        switch (op) {
            case 1:
                return TipoConta.POUPANCA;
            case 2:
                return TipoConta.CORRENTE;
            case 3:
                return TipoConta.INVESTIMENTO;
        }
        return null;
    }
}
