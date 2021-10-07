package com.example.bankapi.dao;

import com.example.bankapi.handler.exceptions.EmptyAccountException;
import com.example.bankapi.handler.exceptions.ThereIsNoSuchAccountException;
import com.example.bankapi.models.Account;
import com.example.bankapi.models.Card;

import java.util.List;

public interface AccountDAO {

    /**
     * Getting account object by id
     *
     * @param accountId account id
     * @return account object from DB
     * @throws ThereIsNoSuchAccountException - account with such id not found
     */
    Account getAccountById(long accountId);

    /**
     * Getting card list by account id
     *
     * @param accountId account id
     * @return list of cards
     * @throws EmptyAccountException account has no cards
     */
    List<Card> getCards(long accountId);

    /**
     * Getting account balance by id
     *
     * @param accountId account id
     * @return float balance value
     * @throws ThereIsNoSuchAccountException account with such id not found
     */
    float getAccountBalance(long accountId);

    /**
     * Top up an account
     *
     * @param accountId account id
     * @param amount amount of funds deposited
     * @throws ThereIsNoSuchAccountException account with such id not found
     */
    void depositMoney(long accountId, float amount);
}
