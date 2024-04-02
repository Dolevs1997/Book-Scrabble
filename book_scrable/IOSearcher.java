package book_scrable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class IOSearcher {
  static Scanner scanner = null;

  public static boolean search(String word, String fileName1, String fileName2) {
    try {
      scanner = new Scanner(new BufferedReader(new FileReader(fileName1)));
    } catch (FileNotFoundException e) {

      e.printStackTrace();
    }

    while (scanner.hasNext()) {
      if (scanner.hasNext(word)) {
        scanner.close();
        return true;
      }
      scanner.next();
    }
    try {
      scanner = new Scanner(new BufferedReader(new FileReader(fileName2)));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    while (scanner.hasNext()) {
      if (scanner.hasNext(word)) {
        scanner.close();
        return true;
      }
      scanner.next();
    }
    scanner.close();

    return false;
  }
}
