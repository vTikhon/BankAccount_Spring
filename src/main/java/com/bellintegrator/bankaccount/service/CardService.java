package com.bellintegrator.bankaccount.service;

import com.bellintegrator.bankaccount.model.Card;
import com.bellintegrator.bankaccount.dto.CardDTO;
import com.bellintegrator.bankaccount.dto.mapper.CardMapper;
import jakarta.persistence.EntityNotFoundException;
import com.bellintegrator.bankaccount.model.enums.CardStatus;
import com.bellintegrator.bankaccount.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

//    private static final Logger logger = LoggerFactory.getLogger(CardService.class);

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardService(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    @Transactional
    public void updateCardBalance(Long cardId, BigDecimal newBalance) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card with ID " + cardId + " not found"));

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("New balance cannot be negative");
        }

        card.setCardBalance(newBalance);
//        logger.info("Updating balance for card number: {}", card.getCardNumber());
        cardRepository.saveAndFlush(card);
    }

    @Transactional
    public void closeCard(String cardNumber) {
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new EntityNotFoundException("Card with number " + cardNumber + " not found"));
        card.setCardStatus(CardStatus.CLOSED);
//        logger.info("Closing card with number: {}", cardNumber);
        cardRepository.save(card);
    }

    @Transactional(readOnly = true)
    public Card getCardById(Long cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card with ID " + cardId + " not found"));
    }

    @Transactional(readOnly = true)
    public List<CardDTO> getAllCards() {
        List<Card> cards = cardRepository.findAll();
        List<CardDTO> cardsDTO = new ArrayList<>();
        for (Card card : cards) {
            CardDTO cardDTO = cardMapper.cardToCardDTO(card);
            cardsDTO.add(cardDTO);
        }
        return cardsDTO;
    }

    @Transactional
    public CardDTO createCard(CardDTO cardDTO) {
        validateCard(cardDTO);
        Card card = cardMapper.cardDTOToCard(cardDTO);
        cardRepository.save(card);
        return cardMapper.cardToCardDTO(card);
    }

    @Transactional(readOnly = true)
    public boolean existsByCardNumber(String cardNumber) {
        return cardRepository.existsByCardNumber(cardNumber);
    }

    @Transactional(readOnly = true)
    public CardDTO getCardByNumber(String cardNumber) {
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new EntityNotFoundException("Card with number " + cardNumber + " not found"));
        return cardMapper.cardToCardDTO(card);
    }

    public List<CardDTO> getAllCardsByAccountId(Long accountId) {
        return cardMapper.toCardDTOList(cardRepository.findAllByAccountId(accountId));
    }

    private void validateCard(CardDTO cardDTO) {
        if (cardDTO.getCardStatus() == null ) {
            throw new IllegalArgumentException("Card status cannot be null");
        }
    }

}
