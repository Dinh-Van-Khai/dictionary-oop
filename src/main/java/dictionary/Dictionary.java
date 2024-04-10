package dictionary;

import java.util.TreeMap;

public class Dictionary extends TreeMap<String, Word> {
//  In Java, TreeMap is a class in the java.util package used to store key-value pairs in sorted
//  order based on the natural ordering of keys or a custom comparator. Here are some key points
//  about TreeMap:
//
//  Sorting Order:
//  TreeMap sorts the keys in ascending order using a Red-Black tree data structure.
//  This means that when you iterate over a TreeMap, keys will be returned in ascending order.
//
//  Time Complexity:
//  The time complexity for accessing, inserting, or deleting an element in a TreeMap is O(log n),
//  where n is the number of elements in the TreeMap.
//  Since TreeMap uses a Red-Black tree to organize data, operations on it have better performance
//  compared to other data structures like HashMap when the number of elements is large.
//
//  Non-null Keys:
//  In TreeMap, keys cannot be null.
//  If you attempt to add a null key to a TreeMap, it will throw a NullPointerException.
//
//  Main Methods:
//  put(K key, V value): Adds a key-value pair to the TreeMap.
//      get(Object key): Returns the value associated with the given key from the TreeMap,
//      or null if the key does not exist.
//      remove(Object key): Removes the key-value pair with the given key from the TreeMap,
//      if it exists.
//  size(): Returns the number of key-value pairs in the TreeMap.
//  And many more methods like firstKey(), lastKey(), ceilingKey(), floorKey() allow operations
//  related to the order of keys.
//
//  Generics Usage:
//  TreeMap supports generics, allowing you to specify the data type of keys and values when
//  declaring a TreeMap.
//      Example: TreeMap<String, Integer> treeMap = new TreeMap<>();
//
//  TreeMap is a popular data structure in Java, used when you need to store key-value pairs
//  in sorted order and have efficient access and updates to the data.
}
