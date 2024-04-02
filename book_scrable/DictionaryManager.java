package book_scrable;

import java.util.Map;
import java.util.HashMap;

public class DictionaryManager {
  private static DictionaryManager instanceOfDm;
  private Map<String, Dictionary> dm = new HashMap<>();// holds each book with it's dictionary

  public static DictionaryManager get() {
    if (instanceOfDm == null)
      instanceOfDm = new DictionaryManager();
    return instanceOfDm;
  }

  public boolean query(String... args) {
    boolean wordFound = false;
    String searchQuery = args[args.length - 1];
    String[] books = new String[args.length - 1];

    System.arraycopy(args, 0, books, 0, args.length - 1);

    for (String book : books) {
      if (dm.containsKey(book)) {
        Dictionary dictionary = dm.get(book);

        if (dictionary.query(searchQuery) && dictionary.challenge(searchQuery)) {
          wordFound = true;
        }
      } else {
        dm.put(book, new Dictionary(books[0], books[1])); // Assuming the Dictionary constructor accepts an array of
                                                          // book names
        Dictionary dictionary = dm.get(book);
        if (dictionary.query(searchQuery) && dictionary.challenge(searchQuery)) {
          wordFound = true;
        }
      }
    }

    return wordFound;
  }

  public boolean challenge(String... args) {
    String search_query = args[args.length - 1];
    for (int i = 0; i < args.length - 1; i++) {
      String books = args[i];
      Dictionary dictionary = dm.get(books);
      if (dictionary.challenge(search_query)) {
        return true;
      }
    }
    return false;
  }

  public int getSize() {
    return dm.size();
  }

}
