package com.example.bankapi.IntegrationsTests;

import com.example.bankapi.dto.AccReqDTO;
import com.example.bankapi.dto.AccRespDTO;
import com.example.bankapi.dto.CardReqDTO;
import com.example.bankapi.dto.CardRespDTO;
import com.example.bankapi.handler.exceptions.EmptyAccountException;
import com.example.bankapi.handler.exceptions.ThereIsNoSuchAccountException;
import com.example.bankapi.services.ClientService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.is;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestControllerTests {


    private ClientService clientService;
    private RestTemplate restTemplate;

    @LocalServerPort
    private String port;

    @Autowired
    public RestControllerTests(ClientService clientService, RestTemplate restTemplate) {
        this.clientService = clientService;
        this.restTemplate = restTemplate;
    }

    @Test
    public void indexCardTest(){
        ResponseEntity<List<CardRespDTO>> response = restTemplate
                .exchange("http://localhost:" + port + "/bankapi/client/account/{accountId}/card", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<CardRespDTO>>() {}, 1);

        List<CardRespDTO> cards = response.getBody();
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(cards, hasSize(2));
        assertThat(cards.get(0).getId(), is(1L));
        assertThat(cards.get(0).getNumber(), is("3456 2352 6755 8234"));
        assertThat(cards.get(1).getId(), is(2L));
        assertThat(cards.get(1).getNumber(), is("5676 5572 8323 4238"));
    }

    @Test
    public void showBalanceTest() throws ThereIsNoSuchAccountException {
        ResponseEntity<AccRespDTO> response = restTemplate
                .exchange("http://localhost:" + port + "/bankapi/client/account/{accountId}/balance", HttpMethod.GET,
                null, AccRespDTO.class, 1);
        AccRespDTO accRespDTO = clientService.getClientAccountBalance(1);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getBalance(), is(accRespDTO.getBalance()));
    }

    @Test
    public void depositMoneyTest() throws ThereIsNoSuchAccountException {
        AccReqDTO accReqDTO = new AccReqDTO(10.33f);
        HttpEntity<AccReqDTO> httpEntity = new HttpEntity<>(accReqDTO);
        restTemplate.put("http://localhost:" + port + "/bankapi/client/account/{accountId}/balance", accReqDTO, 1);
        AccRespDTO accRespDTO = clientService.getClientAccountBalance(1);

        assertThat(accRespDTO.getBalance(), is(1010.33f));
    }

    @Test
    public void newCardTest() throws EmptyAccountException {
        CardReqDTO cardReqDTO = CardReqDTO.builder()
                .accountId(1)
                .number("3456 3465 7463 3456")
                .pin("2343")
                .cvv("234")
                .build();

        HttpEntity<CardReqDTO> httpEntity = new HttpEntity<>(cardReqDTO);
        restTemplate.postForLocation("http://localhost:" + port + "/bankapi/client/account/{accountId}/card", httpEntity, 1);
        List<CardRespDTO> cards = clientService.getClientCards(1);

        assertThat(cards, hasSize(3));
        assertThat(cards.get(2).getId(), is(9L));
        assertThat(cards.get(2).getNumber(), is("3456 3465 7463 3456"));
    }

















//    @Autowired
//    private MockMvc mockMvc; // предоставляет фейковое окружение для тестирования
//
//    @Autowired
//    private BankAPIController controller;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    public void controllerTest() throws Exception {
//        assertThat(controller).isNotNull();
//    }
//
//    @Test
//    public void showBalanceTest() throws Exception{
//        this.mockMvc.perform(get("/bankapi/client/account/1/balance"))
//                .andDo(print()) // выввод результата в консоль
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.balance", is(1000.0)));
//
//    }
}
