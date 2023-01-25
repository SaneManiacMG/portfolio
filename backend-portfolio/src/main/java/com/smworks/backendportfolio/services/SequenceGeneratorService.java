package com.smworks.backendportfolio.services;

public interface SequenceGeneratorService {
    public String generateUserId();
    public String generateDateTime();
    public int generateNumber();
}
