import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LRUCache {
    private final int capacity;
    private final LinkedHashMap<String, List<String>> cache;

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.cache  = new LinkedHashMap<>(capacity, 0.75f, true){
            protected boolean removeEldestEntry(Map.Entry<String, List<String>> eldest){
                return size() > capacity;
            }
        };
    }

    public boolean contains(String key){
        return cache.containsKey(key);
    }

    public List<String> get(String key){
        return cache.getOrDefault(key, new ArrayList<>());
    }

    public void put(String key, List<String> value) {
        cache.put(key, value);
    }
}
