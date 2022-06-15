package org.example;

import org.example.enums.TipoConta;
import org.example.exceptions.UserException;
import org.example.models.Conta;
import org.example.models.ContaCorrente;
import org.example.models.ContaInvestimento;
import org.example.models.ContaPoupanca;
import org.example.models.Pessoa;
import org.example.models.PessoaFisica;
import org.example.models.PessoaJuridica;
import org.example.services.ContaService;
import org.example.services.PessoaService;
import org.example.strings.UserMessage;

import java.math.BigDecimal;

/**
 * Crie uma aplicação que simule uma app bancária. Os clientes podem ser pessoa
 * física ou jurídica, sendo
 * que para PJ existe a cobrança de uma taxa de 0.5% para cada saque ou
 * transferência. Além do produto conta-corrente,
 * os clientes PF podem abrir uma conta-poupança e conta-investimento. Clientes
 * PJ não abrem poupança,
 * mas seus rendimentos em conta-investimento rendem 2% a mais.
 *
 * Crie as funcionalidades: abrir conta, sacar, depositar, transferência,
 * investir e consultar saldo (poupança e corrente).
 *
 * Use a classe "Aplicacao" para criar seu método "main" e demonstrar o
 * funcionamento do seu código.
 */

public class AplicacaoTeste {
    static ContaService contaService = ContaService.getInstance();
    static PessoaService pessoaService = PessoaService.getInstance();

    public static void main(String[] args) throws UserException {
        testeIncrementoIDDao();
        testeAbrirConta();
        testeSaque();
        testeDeposito();
        testeLogin();
    }

    static void testeLogin() {
        assert pessoaService.login("User1") : "Login falhou";
    }

    static void testeIncrementoIDDao() throws UserException {
        Pessoa p1 = pessoaService.create(new PessoaFisica().setCpf("123").setNome("Teste"));
        assert p1.getId() == 6 : "GenericDAO não gerou o id corretamente";

        Pessoa p2 = pessoaService.create(new PessoaFisica().setCpf("123").setNome("Teste"));
        assert p2.getId() == 7 : "GenericDAO não gerou o id corretamente";

        Conta c1 = contaService.validarDadosEAbrirConta(new PessoaFisica(), new ContaCorrente(), TipoConta.CORRENTE);
        assert c1.getId() == 8 : "GenericDAO não gerou o id corretamente";

        Conta c2 = contaService.validarDadosEAbrirConta(new PessoaFisica(), new ContaCorrente(), TipoConta.CORRENTE);
        assert c2.getId() == 9 : "GenericDAO não gerou o id corretamente";
    }

    static void testeAbrirConta() {
        try {
            contaService.validarDadosEAbrirConta(new PessoaJuridica(), new ContaPoupanca(), TipoConta.POUPANCA);
            assert false : "Não deve criar conta poupança PJ";
        } catch (UserException e) {
            // System.out.println(e.getMessage());
            assert e.getMessage().compareTo(UserMessage.PJ_NAO_CRIA_POUPANCA.getMessage()) == 0
                    : "Não deve criar conta poupança PJ, mensagem incorreta";
        }
    }

    static void testeSaque() {
        ContaPoupanca contaPoupancaSaldo1000 = new ContaPoupanca().setSaldo(BigDecimal.valueOf(1000));
        ContaInvestimento contaInvestimentoPJSaldo1000 = new ContaInvestimento().setPessoa(new PessoaJuridica())
                .setSaldo(BigDecimal.valueOf(1000));

        try {
            contaService.sacar(contaPoupancaSaldo1000, BigDecimal.valueOf(4000));
            assert false : "Não deve sacar além do saldo da conta";
        } catch (UserException e) {
            assert true;
        }
        try {
            contaService.sacar(contaPoupancaSaldo1000, BigDecimal.valueOf(1000));
            assert false : "Não deve sacar além do saldo da conta";
        } catch (UserException e) {
            assert true;
        }

        try {
            contaService.sacar(contaPoupancaSaldo1000, BigDecimal.valueOf(900));
        } catch (UserException e) {
            System.out.println(e.getMessage());
            assert false : "Saque deve ser autorizado";
        }
        
        assert contaPoupancaSaldo1000.getSaldo().compareTo(BigDecimal.valueOf(100)) == 0
                : "Saldo poupança após saque sem tarifa";

        try {
            contaService.sacar(contaInvestimentoPJSaldo1000, BigDecimal.valueOf(4000));
            assert false : "Não deve sacar além do saldo da conta";
        } catch (UserException e) {
            assert true;
        }
        try {
            contaService.sacar(contaInvestimentoPJSaldo1000, BigDecimal.valueOf(1000));
            assert false : "Não deve sacar além do saldo da conta";
        } catch (UserException e) {
            assert true;
        }

        try {
            contaService.sacar(contaInvestimentoPJSaldo1000, BigDecimal.valueOf(900));
        } catch (UserException e) {
            System.out.println(e.getMessage());
            assert false : "Saque deve ser autorizado";
        }

        assert contaInvestimentoPJSaldo1000.getSaldo().compareTo(BigDecimal.valueOf(95.5)) == 0
                : "Saldo conta investimento após saque com tarifa";
    }

    static void testeDeposito() {
        ContaPoupanca contaPoupancaTesteDeposito = new ContaPoupanca().setSaldo(BigDecimal.ZERO);

        try {
            contaService.depositar(contaPoupancaTesteDeposito, BigDecimal.valueOf(100));
            assert contaPoupancaTesteDeposito.getSaldo().compareTo(BigDecimal.valueOf(100)) == 0
                    : "Depósito deve ser efetuado";
        } catch (UserException e) {
            System.out.println(e.getMessage());
            assert contaPoupancaTesteDeposito.getSaldo().compareTo(BigDecimal.valueOf(100)) == 0
                    : "Depósito deve ser efetuado";
        }

        try {
            contaService.depositar(contaPoupancaTesteDeposito, BigDecimal.valueOf(-100));
            assert contaPoupancaTesteDeposito.getSaldo().compareTo(BigDecimal.valueOf(100)) == 0
                    : "Valores de depósito negativos não devem ser aceitos";
        } catch (UserException e) {
            // System.out.println(e.getMessage());
            assert contaPoupancaTesteDeposito.getSaldo().compareTo(BigDecimal.valueOf(100)) == 0
                    : "Valores de depósito negativos não devem ser aceitos";
        }
    }
}