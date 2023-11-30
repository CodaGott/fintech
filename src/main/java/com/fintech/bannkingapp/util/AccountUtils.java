package com.fintech.bannkingapp.util;


import java.time.Year;

public class AccountUtils {

    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_CREATED_CODE = "200";
    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already exists";
    public static final String ACCOUNT_CREATED_MESSAGE = "Account Created successfully";
    public static final String ACCOUNT_DOES_NOT_EXIST_MESSAGE = "Account Number Does Not Exist";
    public static final String ACCOUNT_DOES_NOT_EXIST_CODE = "404";
    public static final String ACCOUNT_DOES_EXIST_MESSAGE = "Found Account with Number ";
    public static final String NULL_ACCOUNT_MESSAGE = "Please Pass Account Number To Proceed";
    public static final String NULL_ACCOUNT_CODE = "800";

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
