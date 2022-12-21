package oy.tol.tra;


public class KeyValueHashTable<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private static final int DEFAULT_CAPACITY = 20;
    private static final double LOAD_FACTOR = 0.70; 
    private int size;
    private int count;  
    private Pair<K,V>[] array; 
    private long collisionCount; 
    private long maxProbingCount; 
    private int reallocateCount = 0;

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
        Double filled = count * 100.00 / size;
        String filledPercent = String.format("%.2f%%", filled);
        
        builder.append("Load factor for the hash table is " + LOAD_FACTOR + "\n");
        builder.append("Capacity for the hash table is " + size + "\n");
        builder.append("Count in hash table is " + count + "\n");
        builder.append("Current fill rate for hash table is " + filledPercent + "\n");
        builder.append("Hash table had " + collisionCount + " collisions when filling the hash table" + "\n");
        builder.append("Hash table had to reallocate " + reallocateCount + " times while filling the hash table." + "\n");
        builder.append("Hash table had to proble " + maxProbingCount + " times in the worst case" + "\n");

        return builder.toString();
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        // TODO: Implement this!
        int index = 0;
        int hashModifier = 0;
        int currentProbingCount = 0;
        boolean added = false;

        if(count > (size * LOAD_FACTOR)){
            reallocate(size * 2);
        }

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
        return ((key.hashCode() & 0x7fffffff) + hashModifier) % size;
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        // TODO: Implement this!
        boolean finished = false;
        V result = null;
        int hashModifier = 0;

        do{
            int index = indexFor(key, hashModifier);
            if(array[index] != null){
                if(array[index].equals(key)){
                    result = array[index].getValue();
                    finished = true;
                }else{
                    hashModifier++;
                }
            }else{
                finished = true;
            }

        }while(!finished);

        return result;
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
        int newSize = (int) (count * LOAD_FACTOR);

        if(newSize < size){
            reallocate(newSize);
        }
    }

    private void reallocate(int newSize) throws OutOfMemoryError {
        
        Pair<K, V> [] oldArray = this.array;
        array = (Pair<K,V>[])new Pair[newSize];
        int oldSize = size;
        count = 0;
        size = newSize;
        reallocateCount++;
        for(int index = 0; index < oldSize; index++){
            if(oldArray[index] != null){
                add(oldArray[index].getKey(), oldArray[index].getValue());
            }
        }
    }
}
