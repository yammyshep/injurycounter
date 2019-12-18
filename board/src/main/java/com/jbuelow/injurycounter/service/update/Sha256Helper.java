package com.jbuelow.injurycounter.service.update;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Helper {

  public static byte[] getSHA(byte[] input) throws NoSuchAlgorithmException
  {
    // Static getInstance method is called with hashing SHA
    MessageDigest md = MessageDigest.getInstance("SHA-256");

    // digest() method called
    // to calculate message digest of an input
    // and return array of byte
    return md.digest(input);
  }

  public static String toHexString(byte[] hash)
  {
    // Convert byte array into signum representation
    BigInteger number = new BigInteger(1, hash);

    // Convert message digest into hex value
    StringBuilder hexString = new StringBuilder(number.toString(16));

    // Pad with leading zeros
    while (hexString.length() < 32)
    {
      hexString.insert(0, '0');
    }

    return hexString.toString();
  }

}
