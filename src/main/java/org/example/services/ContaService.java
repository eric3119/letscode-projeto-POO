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

    public Conta getById(Pessoa pessoa, int idConta) {
        return this.contaDao.getAll().stream()
                .filter(conta -> conta
                        .getPessoa().getId() == pessoa.getId() && conta.getId() == idConta)
                .findFirst().orElse(null);
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

    public void transferir(Pessoa pessoa, int idContaOrigem, int idContaDestino, BigDecimal valor) throws UserException {
        Conta contaOrigem = getById(pessoa, idContaOrigem);
        Conta contaDestino = this.contaDao.getById(idContaDestino);

        if(contaOrigem == null) throw new UserException("Conta origem não encontrada");
        if(contaDestino == null) throw new UserException("Conta destino não encontrada");

        if(contaOrigem.getId() == contaDestino.getId()) throw new UserException("Contas são iguais");

        this.sacar(contaOrigem, valor, BigDecimal.ZERO); // saque sem tarifa
        this.depositar(contaDestino, valor);
    }

    public void investir() {

    }

    public BigDecimal consultarSaldo(Pessoa pessoa, int idConta) throws UserException {
        Conta contaBusca = getById(pessoa, idConta);
        if(contaBusca == null) throw new UserException("Conta não encontrada");
        else return contaBusca.getSaldo();
    }

    private void sacar(Conta conta, BigDecimal valor, BigDecimal taxa) throws UserException {
        BigDecimal novoSaldo = conta.getSaldo().subtract((valor.multiply((BigDecimal.valueOf(1).add(taxa)))));
        
        if (novoSaldo.compareTo(BigDecimal.ZERO) >= 0) {
            conta.setSaldo(novoSaldo);
            contaDao.update(conta);
        } else {
            throw new UserException("Saldo insuficiente");
        }
    }
}
