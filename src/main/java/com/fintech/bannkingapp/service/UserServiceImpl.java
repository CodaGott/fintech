package com.fintech.bannkingapp.service;

import com.fintech.bannkingapp.dto.BankResponse;
import com.fintech.bannkingapp.dto.UserDto;
import com.fintech.bannkingapp.entity.User;
import com.fintech.bannkingapp.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.fintech.bannkingapp.util.AccountUtils.generateAccountNumber;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<BankResponse> createAccount(UserDto userDto) {

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

        return null;
    }
}
