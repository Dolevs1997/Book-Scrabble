package book_scrable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Dictionary {
  private CacheManager cacheManagerLRU = null;
  private CacheManager cacheManagerLFU = null;
  private BloomFilter bf = null;
  private Scanner scanner = null;
  private String fileName1 = null;
  private String fileName2 = null;

  public Dictionary(String file1, String file2) {
    fileName1 = file1;
    fileName2 = file2;
    cacheManagerLRU = new CacheManager(400, new LRU());
    cacheManagerLFU = new CacheManager(100, new LFU());
    bf = new BloomFilter(256, "MD5", "SHA1");
    try {
      scanner = new Scanner(new BufferedReader(new FileReader(file1)));
    } catch (FileNotFoundException e) {
      // System.out.println("File 1 not found");
      e.printStackTrace();
    }

    while (scanner.hasNext()) {
      bf.add(scanner.next());
    }
    scanner.close();
    try {
      scanner = new Scanner(new BufferedReader(new FileReader(file2)));
    } catch (FileNotFoundException e) {
      // System.out.println("File 2 not found");
      e.printStackTrace();
    }
    while (scanner.hasNext()) {
      bf.add(scanner.next());
    }
    scanner.close();

  }

  public Dictionary() {
  }

  public boolean query(String word) {
    if (cacheManagerLRU.query(word))
      return true;
    else if (cacheManagerLFU.query(word))
      return false;
    if (bf.contains(word)) {
      cacheManagerLRU.add(word);
      return true;
    }
    return false;
  }

  public boolean challenge(String word) {
    try {
      scanner = new Scanner(new BufferedReader(new FileReader(fileName1)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return false;
    }
    try {
      scanner = new Scanner(new BufferedReader(new FileReader(fileName2)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return false;
    }
    cacheManagerLFU.add(word);
    return IOSearcher.search(word, fileName1, fileName2);

  }

}
