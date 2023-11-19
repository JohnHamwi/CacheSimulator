public class CacheEntry {
    // Private fields to store the name and value of the cache entry
    private String name;
    private String value;

    /**
     * Constructor to create a new CacheEntry.
     * 
     * @param name  The name (key) of the cache entry.
     * @param value The value associated with the name.
     */
    public CacheEntry(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Retrieves the name of the cache entry.
     * 
     * @return The name (key) of the cache entry.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets or updates the name of the cache entry.
     * 
     * @param name The new name to be set for the cache entry.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the value of the cache entry.
     * 
     * @return The value associated with the cache entry.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets or updates the value of the cache entry.
     * 
     * @param value The new value to be set for the cache entry.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Provides a string representation of the cache entry.
     * 
     * @return A string that contains the name and value of the cache entry.
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Value: " + value;
    }
}
