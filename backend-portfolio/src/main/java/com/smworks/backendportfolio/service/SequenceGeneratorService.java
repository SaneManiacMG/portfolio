package com.smworks.backendportfolio.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class SequenceGeneratorService {
    public String generateId() {
        return generateNumber() + generateDateTime();
    }

    private String generateDateTime() {
        DateTimeFormatter userIdDateTimeFormat = DateTimeFormatter.ofPattern("ssmmHHddMM");
        LocalDateTime now = LocalDateTime.now();
        return userIdDateTimeFormat.format(now);
    }

    private int generateNumber() {
        Random random = new Random();
        int upperLimit = 99;
        int lowerLimit = 10;
        return random.nextInt(upperLimit - lowerLimit) + lowerLimit;
    }
}
