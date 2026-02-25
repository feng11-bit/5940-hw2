public class MyHashTable<T extends Comparable<T>> {
    private MyTree[] body; // an array of buckets, where each bucket contains either MyTree object or null if it is empty
    private int capacity;//The total number of buckets in your hash table (the size of the array)
    private static final int DEFAULT_CAPACITY = 701;//should be initialized to 701 by default
    private int size; //number of items currently stored in your hash table

    //constructor
    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    //alternative constructor
    public MyHashTable(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity");
        }
        this.capacity = capacity;
        body = new MyTree[capacity];
        size = 0;
    }

    //adding method
    public MyNode add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Illegal: item is null");
        }
        int index = Math.floorMod(item.hashCode(), capacity);//handel negative #
        if (body[index] == null) {
            body[index] = new MyTree();
        }

        //do not use contains to
        int initial = body[index].size();
        MyNode node = body[index].add(item); //insert item if not yet in present
        int current = body[index].size();
        if (initial < current) {
            size++; //total item change
        }
        return node;
    }

    //contains method
    public MyNode contains(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Illegal: item is null");
        }
        int index = Math.floorMod(item.hashCode(), capacity);
        if (body[index] == null) {
            return null;
        }
        MyNode node = body[index].contains(item);
        return node;
    }

    //remove method
    public boolean remove(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Illegal: item is null");
        }
        int index = Math.floorMod(item.hashCode(), capacity);
        if (body[index] == null) { //if null, nothing to remove
            return false;
        }
        boolean result = body[index].remove(item);
        if (result) {
            size--; //if removed, the size changed
        }
        return result;
    }

    public boolean isEmpty() {
        return size == 0; //only true if not item in hashTable
    }

    public int size() {
        return size;
    }

    public void clear() {
        body = new MyTree[capacity];
        size = 0;
    }

    public static void main(String[] args) {
        //create new hashtable
        MyHashTable<String> myHashTable = new MyHashTable<>();
        //print &test method
        System.out.println(myHashTable.add("I"));
        System.out.println(myHashTable.add("LOVE"));
        System.out.println(myHashTable.add("CIT"));
        System.out.println(myHashTable.add("5940"));
        System.out.println(myHashTable.add("!"));
        System.out.println("size:" + myHashTable.size());
        System.out.println("contains LOVE:" + myHashTable.contains("LOVE"));
        System.out.println("not contains HATE:" + myHashTable.contains("HATE"));
        System.out.println("insert exist LOVE:" + myHashTable.add("LOVE"));
        System.out.println("remove exist LOVE:" + myHashTable.remove("LOVE"));
        System.out.println("remove LOVE again:" + myHashTable.remove("LOVE"));
    }
}

