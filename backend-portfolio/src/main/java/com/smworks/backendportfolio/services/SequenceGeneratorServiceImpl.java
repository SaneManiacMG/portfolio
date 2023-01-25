package com.smworks.backendportfolio.services;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService{
    @Override
    public String generateUserId() {
        String generatedSequence = generateDateTime() + generateNumber();
        return generatedSequence;
    }

    @Override
    public String generateDateTime() {
        DateTimeFormatter userIdDateTimeFormat = DateTimeFormatter.ofPattern("ssmmHHddMM");
        LocalDateTime now = LocalDateTime.now();
        return userIdDateTimeFormat.format(now);
    }

    @Override
    public int generateNumber() {
        Random random = new Random();
        int upperLimit = 99;
        int lowerLimit = 10;
        return random.nextInt(upperLimit - lowerLimit) + lowerLimit;
    }
}
