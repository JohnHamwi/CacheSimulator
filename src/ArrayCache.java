import java.util.LinkedHashMap;
import java.util.Map;

public class ArrayCache {

    private final Map<String, CacheEntry> cache;  // A map to store cache entries
    private final int capacity;  // The maximum number of entries in the cache
    private int numHits;  // Counter for the number of cache hits
    private int numMisses;  // Counter for the number of cache misses

    /**
     * Constructs an ArrayCache with a specified size.
     * 
     * @param size The maximum number of entries the cache can hold.
     */
    public ArrayCache(int size) {
        this.capacity = size;
        this.numHits = 0;
        this.numMisses = 0;
        this.cache = new LinkedHashMap<String, CacheEntry>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, CacheEntry> eldest) {
                // Automatically removes the oldest entry when capacity is exceeded
                return size() > capacity;
            }
        };
    }

    /**
     * Adds or updates an entry in the cache.
     * 
     * @param name  The key for the cache entry.
     * @param value The value to be associated with the key.
     */
    public synchronized void put(String name, String value) {
        cache.put(name, new CacheEntry(name, value));
    }

    /**
     * Retrieves an entry from the cache.
     * 
     * @param name The key of the cache entry to retrieve.
     * @return The CacheEntry associated with the key, or null if not found.
     */
    public synchronized CacheEntry get(String name) {
        CacheEntry entry = cache.get(name);
        if (entry == null) {
            numMisses++;
            return null;
        } else {
            numHits++;
            // Re-insert the entry to maintain the access order for LRU
            cache.remove(name);
            cache.put(name, entry);
            return entry;
        }
    }

    /**
     * @return The number of cache hits.
     */
    public int getHits() {
        return numHits;
    }

    /**
     * @return The number of cache misses.
     */
    public int getMisses() {
        return numMisses;
    }

    /**
     * Clears all entries from the cache and resets the hit/miss counters.
     */
    public synchronized void clear() {
        cache.clear();
        numHits = 0;
        numMisses = 0;
    }

    /**
     * @return True if the cache is empty, false otherwise.
     */
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Entries: " + cache.size() + "\nHits: " + numHits + "\nMisses: " + numMisses + "\n");
        for (CacheEntry entry : cache.values()) {
            s.append(entry.toString()).append("\n");
        }
        return s.toString();
    }
}
