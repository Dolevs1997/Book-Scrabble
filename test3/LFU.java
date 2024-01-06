package test3;

import java.util.HashMap;
import java.util.Map;

public class LFU implements CacheReplacementPolicy {
  private Map<String, Integer> keyCounter = new HashMap<>();
  private Map<String, Integer> insertionOrder = new HashMap<>();
  private int counter = 0;

  public void add(String word) {
    if (keyCounter.containsKey(word)) {
      // If word is already present, update its access count
      keyCounter.put(word, keyCounter.get(word) + 1);
    } else {
      // Add word to the access count map and set its initial count to 1
      keyCounter.put(word, 1);
      // Add word to the insertion order map with its current counter value
      insertionOrder.put(word, counter++);
    }
  }

  public String remove() {

    String leastFrequentWord = null;
    int counterOfKey = Integer.MAX_VALUE;
    int leastInsertionOrder = Integer.MAX_VALUE;

    // Find the least frequently used word based on access count and insertion order
    for (Map.Entry<String, Integer> entry : keyCounter.entrySet()) {
      String word = entry.getKey();
      int count = entry.getValue();
      int order = insertionOrder.get(word);

      if (count < counterOfKey || (count == counterOfKey && order < leastInsertionOrder)) {
        counterOfKey = count;
        leastInsertionOrder = order;
        leastFrequentWord = word;
      }
    }

    // Remove the least frequently used word from the access count and insertion
    // order maps
    keyCounter.remove(leastFrequentWord);
    insertionOrder.remove(leastFrequentWord);

    return leastFrequentWord;

  }

}
