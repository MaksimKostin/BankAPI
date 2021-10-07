package com.example.bankapi.repositories;

import com.example.bankapi.handler.exceptions.EmptyAccountException;
import com.example.bankapi.handler.exceptions.ThereIsNoSuchAccountException;
import com.example.bankapi.models.Card;

import java.util.List;

public interface ClientRepo {

    /**
     * Getting card list by account id
     *
     * @param accountId account id
     * @return list of cards
     * @throws EmptyAccountException account has no cards
     */
    List<Card> getCards(long accountId) throws EmptyAccountException;

    /**
     * Getting account balance by id
     *
     * @param accountId account id
     * @return float balance value
     * @throws ThereIsNoSuchAccountException account with such id not found
     */
    float getAccountBalance(long accountId) throws ThereIsNoSuchAccountException;

    /**
     * Top up an account
     *
     * @param accountId account id
     * @param amount amount of funds deposited
     * @throws ThereIsNoSuchAccountException account with such id not found
     */
    void depositMoney(long accountId, float amount) throws ThereIsNoSuchAccountException;

    /**
     * Adding new card to DB
     *
     * @param accountId account id
     * @param card credit card with details
     */
    void addNewCard(long accountId, Card card);

}
