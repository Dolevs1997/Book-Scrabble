package book_scrable;

import java.util.ArrayList;
import java.util.List;

public class LRU implements CacheReplacementPolicy {
    private List<String> lru;

    public LRU() {
        lru = new ArrayList<>();
    }

    public void add(String word) {
        // If word is already present, remove it from the list
        if (lru.contains(word)) {
            lru.remove(word);
        }

        // Add the word at the end of the list
        lru.add(word);

    }

    public String remove() {
        if (!lru.isEmpty()) {
            // Remove and return the least recently used word from the front of the list
            return lru.remove(0);
        }

        return null; // or throw an exception indicating the cache is empty
    }
}
