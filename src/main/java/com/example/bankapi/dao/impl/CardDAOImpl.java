package com.example.bankapi.dao.impl;

import com.example.bankapi.dao.CardDAO;
import com.example.bankapi.models.Card;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CardDAOImpl implements CardDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addNewCard(Card card) {
        entityManager.persist(card);
    }
}
