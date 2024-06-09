package com.bellintegrator.bankaccount.models.dto.mapper;

import com.bellintegrator.bankaccount.models.dto.CardDTO;
import com.bellintegrator.bankaccount.models.Card;
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

