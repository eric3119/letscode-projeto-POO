package org.example.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.example.dao.ContaDao;
import org.example.enums.TipoConta;
import org.example.exceptions.UserException;
import org.example.models.Conta;
import org.example.models.Pessoa;
import org.example.models.PessoaJuridica;
import org.example.strings.UserMessage;

public class ContaService {
    private static ContaService classInstance;

    private ContaService() {
    }

    public static ContaService getInstance() {
        if (classInstance == null)
            classInstance = new ContaService();

        return classInstance;
    }

    private ContaDao contaDao = ContaDao.getInstance();

    public List<Conta> getAll(Pessoa pessoa) {
        return this.contaDao.getAll().stream()
                .filter(conta -> conta
                        .getPessoa().getId() == pessoa.getId())
                .collect(Collectors.toList());
    }

    public void validaCriarConta(Pessoa pessoa, TipoConta tipoConta) throws UserException {
        if (pessoa instanceof PessoaJuridica && tipoConta == TipoConta.POUPANCA)
            throw new UserException(UserMessage.PJ_NAO_CRIA_POUPANCA.getMessage());
    }

    public Conta validarDadosEAbrirConta(Pessoa pessoa, Conta conta, TipoConta tipoConta) throws UserException {
        validaCriarConta(pessoa, tipoConta);
        return contaDao.create(conta);
    }

    public void sacar(Conta conta, BigDecimal valor) throws UserException {

        if (conta.getPessoa() instanceof PessoaJuridica) {
            sacar(conta, valor, BigDecimal.valueOf(0.005));
        } else {
            sacar(conta, valor, BigDecimal.valueOf(0));
        }
    }

    public void depositar(Conta conta, BigDecimal valor) throws UserException {
        if (valor.compareTo(BigDecimal.ZERO) < 0)
            throw new UserException(UserMessage.DEPOSITO_NEGATIVO.getMessage());

        BigDecimal novoSaldo = conta.getSaldo().add(valor);
        conta.setSaldo(novoSaldo);
    }

    public boolean transferir() {
        return false;
    }

    public void investir() {

    }

    public BigDecimal consultarSaldo() {
        return BigDecimal.ZERO;
    }

    private void sacar(Conta conta, BigDecimal valor, BigDecimal taxa) throws UserException {
        BigDecimal novoSaldo = conta.getSaldo().subtract((valor.multiply((BigDecimal.valueOf(1).add(taxa)))));

        if (novoSaldo.compareTo(BigDecimal.valueOf(0)) > 0) {
            conta.setSaldo(novoSaldo);
            contaDao.update(conta);
        } else {
            throw new UserException("Saldo insuficiente");
        }
    }
}
