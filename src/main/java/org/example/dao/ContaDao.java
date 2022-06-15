package org.example.dao;

import org.example.models.Conta;

public class ContaDao extends GenericDao<Conta> {

    private static ContaDao classInstance;

    private ContaDao() {
    }

    public static ContaDao getInstance() {
        if (classInstance == null)
            classInstance = new ContaDao();

        return classInstance;
    }
}
