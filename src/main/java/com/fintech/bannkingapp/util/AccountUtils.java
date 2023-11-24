package com.fintech.bannkingapp.util;


import java.time.Year;

public class AccountUtils {

    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_CREATED_CODE = "200";
    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already exists";
    public static final String ACCOUNT_CREATED_MESSAGE = "Account Created successfully";

    public static String generateAccountNumber(){
        Year currentYear = Year.now();
        int min = 100_000;
        int max = 999_999;

        int randomNumber = (int) (Math.random() * (max - min + 1) + min);

        String year = String.valueOf(currentYear);
        String randomNum =String.valueOf(randomNumber);

        StringBuilder accountNum = new StringBuilder();
        return accountNum.append(year + randomNum).toString();
    }

}
