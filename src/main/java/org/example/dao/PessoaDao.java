package org.example.dao;

import org.example.models.Pessoa;

public class PessoaDao extends GenericDao<Pessoa> {

    private static PessoaDao classInstance;

    private PessoaDao() {
    }

    public static PessoaDao getInstance() {
        if (classInstance == null)
            classInstance = new PessoaDao();

        return classInstance;
    }
    
}
