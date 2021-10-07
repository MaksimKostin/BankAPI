package com.example.bankapi.repositories.impl;

import com.example.bankapi.handler.exceptions.EmptyAccountException;
import com.example.bankapi.handler.exceptions.ThereIsNoSuchAccountException;
import com.example.bankapi.models.Account;
import com.example.bankapi.models.Card;
import com.example.bankapi.repositories.ClientRepo;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ClientRepoImpl implements ClientRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Card> getCards(long accountId) throws EmptyAccountException {
        ArrayList<Card> cards = new ArrayList<>(entityManager
                .createQuery("from Card as c join fetch c.account as ca where ca.id=:accountId", Card.class)
                .setParameter("accountId", accountId).getResultList());

        if (cards.isEmpty()) {
            throw new EmptyAccountException();
        }

        return cards;
    }

    public float getAccountBalance(long accountId) throws ThereIsNoSuchAccountException {
        try {

            return (float) entityManager
                    .createQuery("SELECT balance from Account where id=:accountId")
                    .setParameter("accountId", accountId).getSingleResult();

        } catch (NoResultException e) {
            throw new ThereIsNoSuchAccountException();
        }
    }

    public void depositMoney(long accountId, float amount) throws ThereIsNoSuchAccountException {
        try {

            entityManager.createQuery("UPDATE Account set balance = balance + :amount where id=:accountId")
                    .setParameter("amount", amount)
                    .setParameter("accountId", accountId)
                    .executeUpdate();

        } catch (NoResultException e) {
            throw new ThereIsNoSuchAccountException();
        }
    }

    public void addNewCard(long accountId, Card card) {
        Account account = entityManager.find(Account.class, accountId);
        account.getCards().add(card);
    }

}
