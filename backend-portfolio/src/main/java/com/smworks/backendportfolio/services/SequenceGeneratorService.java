package com.smworks.backendportfolio.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class SequenceGeneratorService {
    public String generateUserId() {
        return generateDateTime() + generateNumber();
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
