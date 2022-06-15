package org.example.enums;

public enum TipoPessoa {
    FISICA(1), JURIDICA(2);

    private int representation;

    TipoPessoa(int op) {
        this.representation = op;
    }

    public String getFormatted() {
        return this.representation + " - " + fromInt(this.representation).toString();
    }

    public static TipoPessoa fromInt(int op) {
        switch (op) {
            case 1:
                return TipoPessoa.FISICA;
            case 2:
                return TipoPessoa.JURIDICA;
        }
        return null;
    }
}
