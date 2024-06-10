package com.bellintegrator.bankaccount.model.generators;

import java.security.SecureRandom;

public class NumberGenerator {
    private static final SecureRandom random = new SecureRandom();
    private static final int CARD_NUMBER_LENGTH = 20;

    public static String generate() {
        StringBuilder cardNumber = new StringBuilder(CARD_NUMBER_LENGTH);
        for (int i = 0; i < CARD_NUMBER_LENGTH; i++) {
            int randomDigit;
            do {
                randomDigit = random.nextInt(10);
            } while (i == 0 && randomDigit == 0); // пропускаем 0 для первой цифры
            cardNumber.append(randomDigit);
        }
        return cardNumber.toString();
    }
}

