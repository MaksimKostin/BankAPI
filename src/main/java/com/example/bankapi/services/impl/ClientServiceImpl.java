package com.example.bankapi.services.impl;

import com.example.bankapi.dao.AccountDAO;
import com.example.bankapi.dao.CardDAO;
import com.example.bankapi.dto.AccReqDTO;
import com.example.bankapi.dto.AccRespDTO;
import com.example.bankapi.dto.CardReqDTO;
import com.example.bankapi.dto.CardRespDTO;
import com.example.bankapi.handler.exceptions.EmptyAccountException;
import com.example.bankapi.handler.exceptions.NotEqualAccountIdException;
import com.example.bankapi.handler.exceptions.ThereIsNoSuchAccountException;
import com.example.bankapi.models.Account;
import com.example.bankapi.models.Card;

import com.example.bankapi.services.ClientService;
import com.example.bankapi.utils.MappingUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final CardDAO cardDAO;
    private final AccountDAO accountDAO;
    private final MappingUtils mappingUtils;

    public ClientServiceImpl(CardDAO cardDAO, AccountDAO accountDAO, MappingUtils mappingUtils) {
        this.cardDAO = cardDAO;
        this.accountDAO = accountDAO;
        this.mappingUtils = mappingUtils;
    }

    public List<CardRespDTO> getClientCards(long accountId) {
        return accountDAO.getCards(accountId).stream()
                .map(mappingUtils::mapToCardDto)
                .collect(Collectors.toList());
    }

    public AccRespDTO getClientAccountBalance(long accountId) {
        return new AccRespDTO(accountDAO.getAccountBalance(accountId));
    }

    public void depositClientMoney(long accountId, AccReqDTO accReqDTO) {
        accountDAO.depositMoney(accountId, accReqDTO.getAmount());
    }

    public void addNewCardByAccountId(long accountId, CardReqDTO cardReqDTO) {
        if(accountId != cardReqDTO.getAccountId()){
            throw new NotEqualAccountIdException();
        }
        Account account = accountDAO.getAccountById(accountId);
        Card card = mappingUtils.mapToCard(account, cardReqDTO);
        cardDAO.addNewCard(card);
    }
}
