package test2;

import java.util.HashSet;

public class CacheManager {
  private HashSet<String> cacheManager;
  CacheReplacementPolicy least_recently_used;
  int maxSizeCache;

  public CacheManager(int size, CacheReplacementPolicy cache) {
    cacheManager = new HashSet<>();
    least_recently_used = cache;
    maxSizeCache = size;
  }

  public boolean query(String string) {

    return cacheManager.contains(string);
  }

  public void add(String string) {
    least_recently_used.add(string);
    cacheManager.add(string);
    if (cacheManager.size() > maxSizeCache) {
      cacheManager.remove(least_recently_used.remove());
    }

  }

}
