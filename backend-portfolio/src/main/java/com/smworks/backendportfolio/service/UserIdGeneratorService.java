package com.smworks.backendportfolio.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class UserIdGeneratorService {

    public String generateId() {
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
        int sequence = random.nextInt(upperLimit - lowerLimit) + lowerLimit;

        return sequence;
    }
}
