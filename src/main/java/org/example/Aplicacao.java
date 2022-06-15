package org.example;

import java.util.Arrays;
import java.util.Scanner;

import org.example.enums.OperacaoSistema;
import org.example.enums.TipoConta;
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
        OperacaoSistema operacao;

        do {
            do {
                System.out.println("Digite o comando\n");
                Arrays.stream(OperacaoSistema.values()).map(opr -> opr.getFormatted()).forEach(System.out::println);

                operacao = OperacaoSistema.fromInt(scanner.nextInt());
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
                    break;
                case DEPOSITAR:
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
        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void abrirConta(Pessoa pessoa) throws UserException {
        TipoConta tipoConta;
        do {
            System.out.println("Selecione o tipo de conta\n");
            Arrays.stream(TipoConta.values()).map(opr -> opr.getFormatted()).forEach(System.out::println);

            tipoConta = TipoConta.fromInt(scanner.nextInt());
            scanner.nextLine();
        } while (tipoConta == null);

        contaService.validaCriarConta(pessoa, tipoConta);
        Conta conta = ContaHelper.getDadosAberturaConta(pessoa, tipoConta);

        contaService.abrirConta(pessoa, conta, tipoConta);
    }

    
}
