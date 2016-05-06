package tp6;

import java.util.Collection;
import java.util.Set;

public interface SimpleMap<K, V> {
	public void put(K key, V value);
	public V get(K key);
	public void remove(K key);
	public Set<K> keySet();
	public Collection<V> values();
	public int size();
}
