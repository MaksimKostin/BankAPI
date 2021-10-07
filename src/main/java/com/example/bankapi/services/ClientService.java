package com.example.bankapi.services;

import com.example.bankapi.dto.AccReqDTO;
import com.example.bankapi.dto.AccRespDTO;
import com.example.bankapi.dto.CardReqDTO;
import com.example.bankapi.dto.CardRespDTO;
import com.example.bankapi.handler.exceptions.EmptyAccountException;
import com.example.bankapi.handler.exceptions.NotEqualAccountIdException;
import com.example.bankapi.handler.exceptions.ThereIsNoSuchAccountException;

import java.util.List;

public interface ClientService {

    /**
     * Getting card list by account id
     *
     * @param accountId account id
     * @return list of cards
     * @throws EmptyAccountException account has no cards
     */
    List<CardRespDTO> getClientCards(long accountId);

    /**
     * Getting account balance by id
     *
     * @param accountId account id
     * @return response object with account balance
     * @throws ThereIsNoSuchAccountException account with such id not found
     */
    AccRespDTO getClientAccountBalance(long accountId);

    /**
     * Top up an account
     *
     * @param accountId account id
     * @param accReqDTO request object with amount
     * @throws ThereIsNoSuchAccountException  account with such id not found
     */
    void depositClientMoney(long accountId, AccReqDTO accReqDTO);

    /**
     * Adding new card to DB
     *
     * @param accountId account id
     * @param cardReqDTO request credit card with details
     * @throws ThereIsNoSuchAccountException account with such id not found
     * @throws NotEqualAccountIdException URL account id doesn't match with json account id
     */
    void addNewCardByAccountId(long accountId, CardReqDTO cardReqDTO);
}
