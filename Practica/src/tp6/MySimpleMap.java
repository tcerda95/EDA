package tp6;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import tp2.ListSet;

public class MySimpleMap<K, V> implements SimpleMap<K,V> {
	private static class Node<K,V> {
		private K key;
		private V value;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue(){
			return value;
		}

		public void setValue(V v) {
			value = v;
		}
	}

	private final static int INITIAL_MOD = 5;
	private final static int INITIAL_ARRAYS_AMOUNT = INITIAL_MOD * 2;
	private final static int ALPHA = 3;

	private int p;
	private int mod;
	private int size;
	private List<Node<K,V>>[] array;

	@SuppressWarnings("unchecked")
	public MySimpleMap() {
		size = 0;
		p = 0;
		mod =  INITIAL_MOD;
		array = (List<Node<K, V>>[])Array.newInstance(List.class, INITIAL_ARRAYS_AMOUNT);
		for (int i = 0; i < mod; i++)
			array[i] = newBucket();
	}

	@Override
	public void put(K key, V value) {
		int i = getBucketIndex(key);

		List<Node<K,V>> bucket = array[i];
		Node<K,V> node = findElem(bucket, key);

		if (node != null) { 	// elemento encontrado
			node.setValue(value);
		}
		else {
			putNewElem(new Node<K,V> (key, value), bucket);
		}
	}


	@Override
	public V get(K key) {
		int i = getBucketIndex(key);
		Node<K,V> node = findElem(array[i], key);
		return node == null ? null : node.getValue();
	}

	@Override
	public void remove(K key) {
		int i = getBucketIndex(key);
		List<Node<K,V>> bucket = array[i];
		Iterator<Node<K,V>> iter = bucket.iterator();

		while (iter.hasNext()) {
			K k = iter.next().getKey();
			if (k.equals(key)) {
				size -= 1;
				iter.remove();
				return;
			}
		}
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new ListSet<K>();
		for (int i = 0; i < numberOfBuckets(); i++) {
			for (Node<K,V> n : array[i]) {
				set.add(n.getKey());
			}
		}
		return set;
	}

	@Override
	public Collection<V> values() {
		Collection<V> list = new ArrayList<>(size());
		for (int i = 0; i < numberOfBuckets(); i++)
			for (Node<K,V> n : array[i])
				list.add(n.getValue());
		return list;
	}

	@Override
	public int size() {
		return size;
	}

	private List<Node<K, V>> newBucket() {
		return new LinkedList<Node<K,V>>();
	}

	private int getBucketIndex(K key) {
		int hash = Math.abs(key.hashCode());
		int i = hash % mod;
		if (i < p)
			i = hash % (mod*2);
		return i;
	}

	private Node<K,V> findElem(List<Node<K,V>> bucket, K key) {
		for (Node<K,V> n : bucket)
			if (n.getKey().equals(key))
				return n;
		return null;
	}

	private int numberOfBuckets() {
		return p + mod;
	}

	private void putNewElem(Node<K, V> node, List<Node<K, V>> bucket) {
		size += 1;
		bucket.add(node);
		if (mustExpand())
			expand();
	}

	private boolean mustExpand() {
		return (size / numberOfBuckets()) > ALPHA;
	}

	private void expand() {
		array[numberOfBuckets()] = newBucket();
		reHash(); // rehashea lo que se encuentra en p
		p += 1;
		if (p == mod) {
			mod *= 2;
			p = 0;
			array = Arrays.copyOf(array, mod*2);
		}
	}

	private void reHash() {
		List<Node<K,V>> bucket = array[p];
		List<Node<K,V>> newBucket = array[numberOfBuckets()];
		Iterator<Node<K,V>> iter = bucket.iterator();

		while (iter.hasNext()) {
			Node<K,V> n = iter.next();
			int i = Math.abs(n.getKey().hashCode()) % (mod*2);
			if (i != p) {
				iter.remove();
				newBucket.add(n);
			}
		}
	}
}
