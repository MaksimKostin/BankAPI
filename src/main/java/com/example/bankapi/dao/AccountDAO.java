package com.example.bankapi.dao;

import com.example.bankapi.handler.exceptions.ThereIsNoSuchAccountException;
import com.example.bankapi.models.Account;

public interface AccountDAO {

    /**
     * Getting account object by id
     *
     * @param accountId account id
     * @return account object from DB
     * @throws ThereIsNoSuchAccountException - account with such id not found
     */
    Account getAccountById(long accountId) throws ThereIsNoSuchAccountException;
}
