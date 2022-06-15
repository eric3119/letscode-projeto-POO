package org.example;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.example.enums.OperacaoSistema;
import org.example.enums.TipoConta;
import org.example.exceptions.NegotialException;
import org.example.exceptions.UserException;
import org.example.helpers.ContaHelper;
import org.example.models.Conta;
import org.example.models.Pessoa;
import org.example.services.ContaService;
import org.example.services.PessoaService;

public class Aplicacao {
    private static ContaService contaService = ContaService.getInstance();
    private static PessoaService pessoaService = PessoaService.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        OperacaoSistema operacao = null;

        do {
            do {
                System.out.println("\nDigite o comando\n");
                Arrays.stream(OperacaoSistema.values()).map(opr -> opr.getFormatted()).forEach(System.out::println);

                try {
                    operacao = OperacaoSistema.fromInt(scanner.nextInt());
                } catch (InputMismatchException e) {
                    System.out.println("Digite um número inteiro");
                }

                scanner.nextLine();
            } while (operacao == null);

            run(operacao);

        } while (operacao != OperacaoSistema.ENCERRAR);
    }

    private static void run(OperacaoSistema op) {
        try {

            switch (op) {
                case ABRIR_CONTA:
                    abrirConta(pessoaService.getById(1));
                    break;
                case SACAR:
                    sacar(pessoaService.getById(1));
                    break;
                case DEPOSITAR:
                    depositar(pessoaService.getById(1));
                    break;
                case TRANSFERIR:
                    break;
                case INVESTIR:
                    break;
                case SALDO:
                    break;
                case ENCERRAR:
                    break;
            }
        } catch (UserException | NegotialException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void abrirConta(Pessoa pessoa) throws NegotialException, UserException {
        TipoConta tipoConta;
        do {
            System.out.println("Selecione o tipo de conta\n");
            Arrays.stream(TipoConta.values()).map(opr -> opr.getFormatted()).forEach(System.out::println);

            tipoConta = TipoConta.fromInt(scanner.nextInt());
            scanner.nextLine();
        } while (tipoConta == null);

        Conta contaASerEfetivada = ContaHelper.getDadosAberturaConta(pessoa, tipoConta);
        Conta contaCriada = contaService.validarDadosEAbrirConta(pessoa, contaASerEfetivada, tipoConta);
        if (contaCriada == null)
            throw new NegotialException("Erro ao criar conta");

        System.out.printf("\n------------------\nConta criada com id: %d\n------------------\n", contaCriada.getId());
    }

    private static void sacar(Pessoa pessoa) throws NegotialException, UserException {
        List<Conta> contasUsuario = contaService.getAll(pessoa);
        Conta contaSelecionada;
        do {

            System.out.println("Selecione o id da conta\n");
            contasUsuario.forEach(System.out::println);

            int idConta = scanner.nextInt();
            scanner.nextLine();
            contaSelecionada = contasUsuario.stream()
                    .filter(conta -> conta.getId() == idConta).findFirst().orElse(null);
        } while (contaSelecionada == null);

        System.out.println("Digite o valor");
        BigDecimal valor = BigDecimal.valueOf(scanner.nextDouble());
        scanner.nextLine();

        contaService.sacar(contaSelecionada, valor);
    }

    private static void depositar(Pessoa pessoa) throws NegotialException, UserException {
        List<Conta> contasUsuario = contaService.getAll(pessoa);
        Conta contaSelecionada;
        do {

            System.out.println("Selecione o id da conta\n");
            contasUsuario.forEach(System.out::println);

            int idConta = scanner.nextInt();
            scanner.nextLine();
            contaSelecionada = contasUsuario.stream()
                    .filter(conta -> conta.getId() == idConta).findFirst().orElse(null);
        } while (contaSelecionada == null);

        System.out.println("Digite o valor");
        BigDecimal valor = BigDecimal.valueOf(scanner.nextDouble());
        scanner.nextLine();

        contaService.depositar(contaSelecionada, valor);
    }

}
