package org.example.strings;

public enum UserMessage {
    DEPOSITO_NEGATIVO("Valores de depósito devem ser maiores que R$ 0,00"),
    PJ_NAO_CRIA_POUPANCA("PJ não é permitido");

    private String message;
    
    UserMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
