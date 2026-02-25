public class MyHashTable<T extends Comparable<T>> {
    private MyTree[] body; // an array of buckets, where each bucket contains either MyTree object or null if it is empty
    private int capacity;//The total number of buckets in your hash table (the size of the array)
    private static final int DEFAULT_CAPACITY = 701;//should be initialized to 701 by default
    private int size; //number of items currently stored in your hash table
    public  MyHashTable() {
        this(DEFAULT_CAPACITY);
    }
    public MyHashTable(int capacity){
        if(capacity < 0){
            throw new IllegalArgumentException("Illegal Capacity");
        }
        this.capacity = capacity;
        body = new MyTree[capacity];
        size = 0;
    }

}

