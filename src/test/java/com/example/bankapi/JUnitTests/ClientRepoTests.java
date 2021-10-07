package com.example.bankapi.JUnitTests;

import com.example.bankapi.handler.exceptions.EmptyAccountException;
import com.example.bankapi.handler.exceptions.ThereIsNoSuchAccountException;
import com.example.bankapi.repositories.ClientRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientRepoTests {

    @Autowired
    private ClientRepo clientRepo;

    @Test
    public void getCardsExceptionTest(){
        assertThrows(EmptyAccountException.class, () -> {
            clientRepo.getCards(10);
        });
    }

    @Test
    public void getAccountBalanceExceptionTest(){
        assertThrows(ThereIsNoSuchAccountException.class, () -> {
            clientRepo.getAccountBalance(200);
        });
    }

}
