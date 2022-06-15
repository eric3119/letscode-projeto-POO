package org.example.helpers;

import java.math.BigDecimal;

import org.example.enums.TipoConta;
import org.example.models.Conta;
import org.example.models.ContaCorrente;
import org.example.models.ContaInvestimento;
import org.example.models.ContaPoupanca;
import org.example.models.Pessoa;

public class ContaHelper {
    public static Conta getDadosAberturaConta(Pessoa pessoa, TipoConta tipoConta) {

        switch (tipoConta) {
            case CORRENTE:
                return getDadosAberturaContaCorrente(pessoa);
            case POUPANCA:
                return getDadosAberturaContaPoupanca(pessoa);
            case INVESTIMENTO:
                return getDadosAberturaContaInvestimento(pessoa);
        }
        return null;
    }

    private static ContaCorrente getDadosAberturaContaCorrente(Pessoa pessoa) {
        ContaCorrente contaCorrente = new ContaCorrente();

        contaCorrente.setLimite(BigDecimal.ZERO);
        contaCorrente.setSaldo(BigDecimal.ZERO);
        contaCorrente.setPessoa(pessoa);

        return contaCorrente;
    }

    private static ContaPoupanca getDadosAberturaContaPoupanca(Pessoa pessoa) {
        ContaPoupanca contaPoupanca = new ContaPoupanca();

        contaPoupanca.setSaldo(BigDecimal.ZERO);
        contaPoupanca.setPessoa(pessoa);

        return contaPoupanca;
    }

    private static ContaInvestimento getDadosAberturaContaInvestimento(Pessoa pessoa) {
        ContaInvestimento contaInvestimento = new ContaInvestimento();

        contaInvestimento.setSaldo(BigDecimal.ZERO);
        contaInvestimento.setPessoa(pessoa);

        return contaInvestimento;
    }

}
