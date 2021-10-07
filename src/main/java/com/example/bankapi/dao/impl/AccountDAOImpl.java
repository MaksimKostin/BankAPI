package com.example.bankapi.dao.impl;

import com.example.bankapi.dao.AccountDAO;
import com.example.bankapi.handler.exceptions.EmptyAccountException;
import com.example.bankapi.handler.exceptions.ThereIsNoSuchAccountException;
import com.example.bankapi.models.Account;
import com.example.bankapi.models.Card;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Account getAccountById(long accountId){
        Account account = entityManager.find(Account.class, accountId);
        if(account == null){
            throw new ThereIsNoSuchAccountException();
        }

        return account;
    }

    @Transactional(readOnly = true)
    public List<Card> getCards(long accountId){
        List<Card> cards = entityManager
                .createQuery("from Card as c join fetch c.account as ca where ca.id=:accountId", Card.class)
                .setParameter("accountId", accountId).getResultList();

        if (cards.isEmpty()) {
            throw new EmptyAccountException();
        }

        return cards;
    }

    @Transactional(readOnly = true)
    public float getAccountBalance(long accountId){
        try {

            return (float) entityManager
                    .createQuery("SELECT balance from Account where id=:accountId")
                    .setParameter("accountId", accountId).getSingleResult();

        } catch (NoResultException e) {
            throw new ThereIsNoSuchAccountException();
        }
    }

    @Transactional
    public void depositMoney(long accountId, float amount){
        try {

            entityManager.createQuery("UPDATE Account set balance = balance + :amount where id=:accountId")
                    .setParameter("amount", amount)
                    .setParameter("accountId", accountId)
                    .executeUpdate();

        } catch (NoResultException e) {
            throw new ThereIsNoSuchAccountException();
        }
    }

}
