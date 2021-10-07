package com.example.bankapi.dao.impl;

import com.example.bankapi.dao.AccountDAO;
import com.example.bankapi.handler.exceptions.ThereIsNoSuchAccountException;
import com.example.bankapi.models.Account;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class AccountDAOImpl implements AccountDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public Account getAccountById(long accountId) throws ThereIsNoSuchAccountException {
        Account account = entityManager.find(Account.class, accountId);
        if(account == null){
            throw new ThereIsNoSuchAccountException();
        }

        return account;
    }
}
