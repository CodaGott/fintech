package com.fintech.bannkingapp.service;

import com.fintech.bannkingapp.dto.EmailDetails;

public interface EmailService {

    void sendEmail(EmailDetails emailDetails);
}
