package com.example.bankapi.dao;

import com.example.bankapi.models.Card;

public interface CardDAO {

    /**
     * Adding new card to DB
     *
     * @param card credit card with details
     */
    void addNewCard(Card card);
}
