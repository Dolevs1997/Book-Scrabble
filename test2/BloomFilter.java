package test2;

import java.util.BitSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class BloomFilter {
  private StringBuilder sb;
  private BitSet bitSet;
  private String[] algorithms;
  private int bitSetSize;

  public BloomFilter(int size, String... algos) {
    bitSetSize = size;
    bitSet = new BitSet(bitSetSize);
    algorithms = algos;

  }

  public void add(String word) {

    int index = 0;
    for (String algorithm : algorithms) {
      try {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] bts = md.digest(word.getBytes());
        BigInteger integer = new BigInteger(1, bts);
        index = Math.abs(integer.intValue() % bitSetSize);
        bitSet.set(index);
      } catch (NoSuchAlgorithmException e) {
        System.out.println("algorithm not found");
        e.printStackTrace();
      }

      // System.out.println("index - " + index + bitSet.get(index));
    }
  }

  public boolean contains(String word) {
    for (String algorithm : algorithms) {
      try {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] bts = md.digest(word.getBytes());
        BigInteger integer = new BigInteger(1, bts);
        int index = Math.abs(integer.intValue() % bitSetSize);
        if (bitSet.get(index))
          return true;
      } catch (NoSuchAlgorithmException e) {
        System.out.println("algorithm not found");
        e.printStackTrace();
        return false;
      }

    }
    return false;
  }

  @Override
  public String toString() {

    sb = new StringBuilder();

    for (int i = 0; i < bitSet.length(); i++) {
      if (bitSet.get(i)) {
        sb.append("1");
      } else {
        sb.append("0");
      }
    }
    // printString();
    return sb.toString();
  }

  public void printBitSet() {
    for (int i = 0; i < bitSet.size(); i++) {
      System.out.print(bitSet.get(i) + ",");
    }
  }

  public void printString() {
    for (int i = 0; i < sb.length(); i++) {
      System.out.print(sb.charAt(i) + ",");
    }
  }
}
