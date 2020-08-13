package datastructure.map;

public interface Map<K,V> {
    V put(K key,V value);
    V  get(K key);
    
    public interface Entry<K,V>{
        K getKey();
        V getValue();
    }
}
