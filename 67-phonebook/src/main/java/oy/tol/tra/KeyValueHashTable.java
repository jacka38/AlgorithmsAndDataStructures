package oy.tol.tra;


public class KeyValueHashTable<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private static final int DEFAULT_CAPACITY = 20;
    private static final double LOAD_FACTOR = 0.65; 
    private int size;
    private int count;  
    private int capacity;
    private Pair<K,V>[] array; 
    private long collisionCount; 
    private long maxProbingCount; 

    public KeyValueHashTable(int capacity) throws OutOfMemoryError {
        ensureCapacity(capacity);
    }

    public KeyValueHashTable() throws OutOfMemoryError {
        ensureCapacity(20);
    }

    @Override
    public Type getType() {
        return Type.NONE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        // TODO: Implement this!
        if(count == 0){
            this.size = size;
            array = (Pair<K,V>[])new Pair[size];
        }else{
            reallocate(size);
        }
    }

    @Override
    public int size() {
        // TODO: Implement this!
        return count;
    }

    /**
     * Prints out the statistics of the hash table.
     * Here you should print out member variable information which tell something
     * about your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member
     * variables of the class (int counters) in add() whenever a collision
     * happen. Then print this counter value here.
     * You will then see if you have too many collisions. It will tell you that your
     * hash function is not good.
     */
    @Override
    public String getStatus() {
        // TODO: Implement this!
        StringBuilder builder = new StringBuilder();

        builder.append("Load factor: " + LOAD_FACTOR + "\n");
        builder.append("Capacity: " + capacity + "\n");
        builder.append("Count: " + count + "\n");
        builder.append("Filled to: " + (count / capacity * 100.0) + "%" + "\n");
        builder.append("Collisions: " + collisionCount + "\n");
        builder.append("Max probings: " + maxProbingCount + "\n");

        return builder.toString();
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        // TODO: Implement this!
        int index = 0;
        int hashModifier = 0;
        int currentProbingCount = 0;
        boolean added = false;

        do{
            index = indexFor(key, hashModifier);   
            if(array[index] == null){
                array[index] = new Pair<>(key, value);
                added = true;
                this.count++;
            }else if(!array[index].equals(key)){
                hashModifier++;
                collisionCount++;
                currentProbingCount++;
            }else{
                array[index].equals(value);
                added = true;
            }
        }while(!added);
        maxProbingCount = Math.max(maxProbingCount ,currentProbingCount);

        return added;
    }

    public int indexFor(K key, int hashModifier){
        return (key.hashCode() & 0x7fffffff + hashModifier) % size;
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        // TODO: Implement this!
        return null;
    }

    @Override
    @java.lang.SuppressWarnings({"unchecked"})
    public Pair<K,V> [] toSortedArray() {
        // TODO: Implement this!

        Pair<K,V>[] toReturn = (Pair<K,V>[])new Pair[count];
        int addIndex = 0;
        for(int index = 0; index < size; index++){
            if(array[index] != null){
                toReturn[addIndex++] = (Pair<K, V>)new Pair(array[index], collisionCount);
            }
        }
        Algorithms.fastSort(toReturn);
        return toReturn;
      }

    @Override
    public void compress() throws OutOfMemoryError {
        // TODO: Implement this!
        int indexOfFirstNull = Algorithms.partitionByRule(array, count, element -> element == null);
        reallocate(indexOfFirstNull);
    }

    private void reallocate(int newSize) throws OutOfMemoryError {
        Pair<K, V> [] newArray = (Pair<K,V>[])new Pair[newSize];
        
        for (int index = 0; index < count; index++) {
           newArray[index] = array[index];
        }
        array = newArray;
    }
}
