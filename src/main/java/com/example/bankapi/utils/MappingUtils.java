package com.example.bankapi.utils;

import com.example.bankapi.dto.CardReqDTO;
import com.example.bankapi.dto.CardRespDTO;
import com.example.bankapi.models.Account;
import com.example.bankapi.models.Card;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {

    /**
     * From simple card to response card DTO mapping
     *
     * @param card card with details from DB
     * @return response card DTO with id and number
     */
    public CardRespDTO mapToCardDto(Card card){
        CardRespDTO dto = new CardRespDTO();
        dto.setId(card.getId());
        dto.setNumber(card.getNumber());
        return dto;
    }

    /**
     * From request card DTO to simple card with details
     * @param account account id
     * @param cardReqDTO request card DTO with details
     * @return simple card with details
     */
    public Card mapToCard(Account account, CardReqDTO cardReqDTO){
        Card card = new Card();
        card.setNumber(cardReqDTO.getNumber());
        card.setPin(cardReqDTO.getPin());
        card.setCvv(cardReqDTO.getCvv());
        card.setAccount(account);
        return card;
    }
}
