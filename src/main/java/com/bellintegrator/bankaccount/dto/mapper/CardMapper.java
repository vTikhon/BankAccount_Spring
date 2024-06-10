package com.bellintegrator.bankaccount.dto.mapper;

import com.bellintegrator.bankaccount.dto.CardDTO;
import com.bellintegrator.bankaccount.model.Card;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CardMapper {

    @Mapping(source = "account.id", target = "accountId")
    CardDTO cardToCardDTO(Card card);

    @Mapping(source = "accountId", target = "account.id")
    Card cardDTOToCard(CardDTO cardDTO);

    List<CardDTO> toCardDTOList(List<Card> cards);
    List<Card> toCardList(List<CardDTO> cardsDTO);
}

