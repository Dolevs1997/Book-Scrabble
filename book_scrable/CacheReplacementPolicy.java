package book_scrable;

public interface CacheReplacementPolicy {
	void add(String word);

	String remove();
}
