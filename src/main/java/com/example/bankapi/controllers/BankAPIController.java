package com.example.bankapi.controllers;

import com.example.bankapi.dto.AccReqDTO;
import com.example.bankapi.dto.AccRespDTO;
import com.example.bankapi.dto.CardReqDTO;
import com.example.bankapi.dto.CardRespDTO;
import com.example.bankapi.handler.exceptions.EmptyAccountException;
import com.example.bankapi.handler.exceptions.NotEqualAccountIdException;
import com.example.bankapi.handler.exceptions.ThereIsNoSuchAccountException;
import com.example.bankapi.services.ClientService;
import com.example.bankapi.services.impl.ClientServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/bankapi/client/account")
public class BankAPIController {

    private final ClientService clientService;

    public BankAPIController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/{accountId}/card")
    public ResponseEntity<List<CardRespDTO>> indexCard(@PathVariable long accountId) {
        List<CardRespDTO> cards = clientService.getClientCards(accountId);
        return ResponseEntity.ok().body(cards);
    }

    @PostMapping(value = "/{accountId}/card")
    public void newCard(@PathVariable long accountId, @RequestBody CardReqDTO cardReqDTO) {
        clientService.addNewCardByAccountId(accountId, cardReqDTO);
    }

    @GetMapping(value = "/{accountId}/balance")
    public ResponseEntity<AccRespDTO> showBalance(@PathVariable long accountId) {
        AccRespDTO dto = clientService.getClientAccountBalance(accountId);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{accountId}/balance")
    public void depositMoney(@PathVariable long accountId, @RequestBody AccReqDTO accReqDTO) {
        clientService.depositClientMoney(accountId, accReqDTO);
    }

}
