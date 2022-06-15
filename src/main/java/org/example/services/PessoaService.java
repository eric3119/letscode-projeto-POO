package org.example.services;

import java.util.List;

import org.example.dao.PessoaDao;
import org.example.models.Pessoa;
import org.example.models.PessoaFisica;
import org.example.models.PessoaJuridica;

public class PessoaService {
    private static PessoaService classInstance;

    private PessoaDao pessoaDao = PessoaDao.getInstance();

    private PessoaService() {
        pessoaDao.create(new PessoaJuridica().setCnpj("cnpj").setNome("User1"));
        pessoaDao.create(new PessoaJuridica().setCnpj("cnpj").setNome("User2"));
        pessoaDao.create(new PessoaFisica().setCpf("cnpj").setNome("User3"));
        pessoaDao.create(new PessoaFisica().setCpf("cnpj").setNome("User4"));
        pessoaDao.create(new PessoaFisica().setCpf("cnpj").setNome("User5"));
    }

    public static PessoaService getInstance() {
        if (classInstance == null)
            classInstance = new PessoaService();

        return classInstance;
    }


    public List<Pessoa> getAll() {
        return this.pessoaDao.getAll();
    }

    public Pessoa create(Pessoa entity) {
        return this.pessoaDao.create(entity);
    }

    public Pessoa getById(int id) {
        return this.pessoaDao.getById(id);
    }

    public Pessoa update(Pessoa entity) {
        return this.pessoaDao.update(entity);
    }

    public boolean login(String username){
        return this.pessoaDao.getAll().stream().filter(pessoa -> {
            return pessoa.getNome().compareTo(username) == 0;
        }).findFirst().isPresent();
    }
}
