package com.fintech.bannkingapp.service;

import com.fintech.bannkingapp.dto.BankResponse;
import com.fintech.bannkingapp.dto.EmailDetails;
import com.fintech.bannkingapp.dto.UserDto;
import com.fintech.bannkingapp.entity.User;
import com.fintech.bannkingapp.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.fintech.bannkingapp.util.AccountUtils.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public ResponseEntity<BankResponse> createAccount(UserDto userDto) {

        if (userRepository.existsByEmail(userDto.getEmail())){
            return ResponseEntity.badRequest().body(new BankResponse(ACCOUNT_EXISTS_CODE, ACCOUNT_EXISTS_MESSAGE));
        }

        User newUser = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .address(userDto.getAddress())
                .otherName(userDto.getOtherName())
                .phoneNumber(userDto.getPhoneNumber())
                .altPhoneNumber(userDto.getAltPhoneNumber())
                .gender(userDto.getGender())
                .email(userDto.getEmail())
                .accountType(userDto.getAccountType())
                .stateOfOrigin(userDto.getStateOfOrigin())
                .accountBalance(BigDecimal.ZERO)
                .status("ACTIVE")
//                .password(userDto.getPassword())
                .accountNumber(generateAccountNumber())
                .build();
        User savedUser = userRepository.save(newUser);
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .subject("Account Creation Email")
                .messageBody("Dear " + savedUser.getFirstName() + " congratulations, your account was created Successfully. \n" +
                        "Here is your Account details: \n" + " Account Number: "+ savedUser.getAccountNumber()+ "\n" +
                        "Account Name: " + savedUser.getFirstName() +" " + savedUser.getLastName() + " " + savedUser.getOtherName())
                .build();

        emailService.sendEmail(emailDetails);
        return ResponseEntity.ok().body(new BankResponse(ACCOUNT_CREATED_CODE, ACCOUNT_CREATED_MESSAGE, savedUser));
    }
}
