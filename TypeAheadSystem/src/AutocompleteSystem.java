import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AutocompleteSystem {
    private final TrieNode root;
    private final int MAX_SUGGESTIONS = 5;
    private final LRUCache cache;


    public AutocompleteSystem(int cacheSize){
        this.root = new TrieNode();
        this.cache = new LRUCache(cacheSize);
    }

    public void insert(String query, int frequency){
        TrieNode node = root;
        for(char c : query.toLowerCase().toCharArray()){
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
            node.searchCounts.put(query, node.searchCounts.getOrDefault(query, 0) + frequency);
        }
        node.isEndOfWord = true;
    }

    public List<String> getSuggestions(String prefix){
        prefix = prefix.toLowerCase();
        if(cache.contains(prefix)){
            return cache.get(prefix);
        }

        TrieNode node = root;
        for(char c : prefix.toCharArray()){
            if(!node.children.containsKey(c)) return new ArrayList<>();
            node = node.children.get(c);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue().compareTo(a.getValue()));
        pq.addAll(node.searchCounts.entrySet());

        List<String> results = new ArrayList<>();
        while(!pq.isEmpty() && results.size() < MAX_SUGGESTIONS){
            results.add(pq.poll().getKey());
        }

        cache.put(prefix, results);
        return results;
    }
}
