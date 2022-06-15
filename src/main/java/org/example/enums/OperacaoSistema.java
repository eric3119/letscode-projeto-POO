package org.example.enums;

public enum OperacaoSistema {
    ABRIR_CONTA(1), SACAR(2), DEPOSITAR(3), TRANSFERIR(4), INVESTIR(5), SALDO(6), ENCERRAR(99);

    private int representation;

    OperacaoSistema(int op) {
        this.representation = op;
    }

    public String getFormatted() {
        return this.representation + " - " + fromInt(this.representation).toString();
    }

    public static OperacaoSistema fromInt(int op) {
        switch (op) {
            case 1:
                return OperacaoSistema.ABRIR_CONTA;
            case 2:
                return OperacaoSistema.SACAR;
            case 3:
                return OperacaoSistema.DEPOSITAR;
            case 4:
                return OperacaoSistema.TRANSFERIR;
            case 5:
                return OperacaoSistema.INVESTIR;
            case 6:
                return OperacaoSistema.SALDO;
            case 99:
                return OperacaoSistema.ENCERRAR;
        }
        return null;
    }
}