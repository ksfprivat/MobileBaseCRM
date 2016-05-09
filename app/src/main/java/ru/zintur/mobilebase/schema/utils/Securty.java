package ru.zintur.mobilebase.schema.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Securty {


    public static String getMd5hash(String str) {

        byte[] bytes = str.getBytes();
        String hash = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] digest = md.digest(bytes);

            BigInteger bigInt = new BigInteger(1,digest);
            hash = bigInt.toString(16);

            while(hash.length() < 32 ){
                hash = "0"+hash;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hash;
    }

}
