import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


/**
 * least-recently-used cache
 * hashmap + queue with a fixed size to implement LRU
 */
public class LRUCache<K, V> {
    private HashMap<K, V> lru;
    private Queue<K> queue;
    private int max;

    public LRUCache(int n) {
        lru = new HashMap<K, V>();
        queue = new LinkedList<K>();
        max = n;
    }

    public boolean add(K key, V value) {
        if (lru.size() < max) {
            lru.put(key, value);
            queue.add(key);
            return true;
        } else if (lru.size() == max) {

            lru.remove(queue.poll());
            queue.add(key);
            lru.put(key, value);
            return true;
        } else
            return false;
    }

    public boolean remove(K key) {
        if (queue.contains(key)) {
            queue.remove(key);
            lru.remove(key);
            return true;
        } else
            return false;
    }

    public int size() {
        return lru.size();
    }

    public V get(K key) {
        if (queue.contains(key)) {
            return lru.get(key);
        } else
            return null;
    }
    public V getFirst(){
        if(!queue.isEmpty()){
            return lru.get(queue.peek());
        }
        else
            return null;
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> lruCache = new LRUCache<String, Integer>(2);

        lruCache.add("A", 1);
        lruCache.add("B", 2);
        System.out.println("getfirst: "+lruCache.getFirst());
        lruCache.add("C", 3);

        System.out.println("size: " +lruCache.size());
        System.out.println("c: " + lruCache.get("C"));
        System.out.println("getfirst: "+lruCache.getFirst());

        try {
            System.out.println(lruCache.get("A"));
        } catch (NullPointerException e) {
            System.out.println("null");
        }


    }
}
