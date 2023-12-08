package com.fintech.bannkingapp.service;

import com.fintech.bannkingapp.dto.*;
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

    @Override
    public ResponseEntity<BankResponse> makeBalanceEnquiry(EnquiryRequest request) {
        boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if (request.equals(null)){
            return ResponseEntity.badRequest().body(new BankResponse(NULL_ACCOUNT_CODE, NULL_ACCOUNT_MESSAGE));
        }
        if (!isAccountExist){
            return ResponseEntity.badRequest().body(new BankResponse(ACCOUNT_DOES_NOT_EXIST_CODE, ACCOUNT_DOES_NOT_EXIST_MESSAGE));
        }
        User foundUser = userRepository.findByAccountNumber(request.getAccountNumber());
        BalanceResponse balanceResponse = BalanceResponse.builder()
                .accountBalance(foundUser.getAccountBalance())
                .accountNumber(foundUser.getAccountNumber()).build();
        return ResponseEntity.ok().body(new BankResponse(ACCOUNT_EXISTS_CODE, ACCOUNT_EXISTS_MESSAGE, balanceResponse));
    }

    @Override
    public ResponseEntity<BankResponse> makeNameEnquiry(EnquiryRequest request) {
        boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if (request.equals(null)){
            return ResponseEntity.badRequest().body(new BankResponse(NULL_ACCOUNT_CODE, NULL_ACCOUNT_MESSAGE));
        }
        if (!isAccountExist){
            return ResponseEntity.badRequest().body(new BankResponse(ACCOUNT_DOES_NOT_EXIST_CODE, ACCOUNT_DOES_NOT_EXIST_MESSAGE));
        }
        User foundUser = userRepository.findByAccountNumber(request.getAccountNumber());
        NameEnquiryResponse response = NameEnquiryResponse.builder()
                .lastName(foundUser.getLastName())
                .firstName(foundUser.getFirstName()).accountBalance(foundUser.getAccountBalance())
                .accountNumber(foundUser.getAccountNumber())
                .accountType(foundUser.getAccountType())
                .address(foundUser.getAddress())
                .email(foundUser.getEmail())
                .phoneNumber(foundUser.getPhoneNumber())
                .stateOfOrigin(foundUser.getStateOfOrigin())
                .accountBalance(foundUser.getAccountBalance())
                .build();
        return ResponseEntity.ok().body(new BankResponse(ACCOUNT_EXISTS_CODE, ACCOUNT_EXISTS_MESSAGE, response));
    }

    @Override
    public ResponseEntity<BankResponse> creditAccount(CreditDebitRequest request) {
        Boolean accountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if (!accountExist){
            return ResponseEntity.badRequest().body(new BankResponse(ACCOUNT_DOES_NOT_EXIST_CODE, ACCOUNT_DOES_NOT_EXIST_MESSAGE));
        }
        User userToCredit = userRepository.findByAccountNumber(request.getAccountNumber());
        userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(request.getAmount()));
        userRepository.save(userToCredit);
        Object response = "Your account has been credited with " + request.getAmount();

        return ResponseEntity.ok().body(new BankResponse(ACCOUNT_EXISTS_CODE, ACCOUNT_EXISTS_MESSAGE, response));
    }

    @Override
    public ResponseEntity<BankResponse> debitAccount(CreditDebitRequest request) {
        Boolean accountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if (!accountExist){
            return ResponseEntity.badRequest().body(new BankResponse(ACCOUNT_DOES_NOT_EXIST_CODE, ACCOUNT_DOES_NOT_EXIST_MESSAGE));
        }
        User userToDebit = userRepository.findByAccountNumber(request.getAccountNumber());
        if (userToDebit.getAccountBalance().compareTo(request.getAmount()) >= 0.00) {
            userToDebit.setAccountBalance(userToDebit.getAccountBalance().subtract(request.getAmount()));
            userRepository.save(userToDebit);
        }else {
            return ResponseEntity.badRequest().body(new BankResponse(ACCOUNT_DOES_EXIST_MESSAGE, INSUFFICIENT_FUNDS));
        }
        Object response = "Your account has been debited " + request.getAmount();

        return ResponseEntity.ok().body(new BankResponse(ACCOUNT_DEBIT_CODE, ACCOUNT_DEBITED_MESSAGE, response));
    }

    @Override
    public ResponseEntity<BankResponse> transfer(TransferRequest request) {

        Boolean accountExist = userRepository.existsByAccountNumber(request.getAccountNumberToDebit());
        if (!accountExist){
            return ResponseEntity.badRequest().body(new BankResponse(ACCOUNT_DOES_NOT_EXIST_CODE, ACCOUNT_DOES_NOT_EXIST_MESSAGE));
        }
        Boolean creditAccountExist = userRepository.existsByAccountNumber(request.getAccountNumberToCredit());
        if (!creditAccountExist){
            return ResponseEntity.badRequest().body(new BankResponse(ACCOUNT_DOES_NOT_EXIST_CODE, ACCOUNT_DOES_NOT_EXIST_MESSAGE));
        }

        User userToDebit = userRepository.findByAccountNumber(request.getAccountNumberToDebit());
        if (userToDebit.getAccountBalance().compareTo(request.getAmount()) >= 0.00) {
            userToDebit.setAccountBalance(userToDebit.getAccountBalance().subtract(request.getAmount()));
            User userToCredit = userRepository.findByAccountNumber(request.getAccountNumberToCredit());
            userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(request.getAmount()));
            userRepository.save(userToDebit);
            userRepository.save(userToCredit);
            Object response = "Your account has been credited with " + request.getAmount();
            return ResponseEntity.ok().body(new BankResponse(ACCOUNT_EXISTS_CODE, ACCOUNT_EXISTS_MESSAGE, response));
        }else {
            return ResponseEntity.badRequest().body(new BankResponse(ACCOUNT_DOES_EXIST_MESSAGE, INSUFFICIENT_FUNDS));
        }
    }
}
