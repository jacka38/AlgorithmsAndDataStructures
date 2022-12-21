package oy.tol.tra;


public class KeyValueBSearchTree<K extends Comparable<K>,V> implements Dictionary<K, V> {

    // This is the BST implementation, KeyValueHashTable has the hash table implementation
    
    private TreeNode root;
    private int count;
    private int maxDepth = 0;
    private int maxCollisionChaninLength = 0;

    public KeyValueBSearchTree(){
        this.root = null;
        this.count = count;
        this.maxDepth = maxDepth;
        this.maxCollisionChaninLength = maxCollisionChaninLength;
    }

    @Override
    public Type getType() {
       return Type.NONE;
    }
 
    @Override
    public int size() {
        // TODO: Implement this!
        return count;
    }

    /**
     * Prints out the statistics of the tree structure usage.
     * Here you should print out member variable information which tell something about
     * your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member variables of the class
     * (int counters) in add(K) whenever a collision happen. Then print this counter value here. 
     * You will then see if you have too many collisions. It will tell you that your hash function
     * is good or bad (too much collisions against data size).
     */
    @Override
    public String getStatus() {
        StringBuilder builder = new StringBuilder();

        builder.append("Count of tree nodes in BST " + count + "\n");
        builder.append("BST max value " + Integer.MAX_VALUE + "\n");
        builder.append("Max depth in BST is " + maxDepth + "\n");
        builder.append("Max collision in BST is " + maxCollisionChaninLength + "\n");

        return builder.toString();
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        // TODO: Implement this!

        if(null == root){
            root = new TreeNode(key, value);
            count++;
            maxDepth = 1;
            maxCollisionChaninLength = 0;
            return true;
        }else{
            int added = root.insert(key, value, key.hashCode());
            if(added > 0){
                count++;
                return true;
            }
        }
        count++;
        return false; 
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        // TODO: Implement this!

        if(null == root){
            return null;
        }else{
            return (V) root.find(key);
        }
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        // Nope, not needed
    }
    
    @Override
    public void compress() throws OutOfMemoryError {
        // Nope, not needed
    }

    
    @Override
    public Pair<K,V> [] toSortedArray() {
        // TODO: Implement this!

        if(null == root){
            return null;
        }
        
        Pair<K,V>[] returnArray = (Pair<K,V>[])new Pair[count];
        int toAddIndex = 0;
        root.toSortedArray(returnArray);
        Algorithms.fastSort(returnArray);
        return returnArray;
    }
    

    public class TreeNode<K extends Comparable<K>,V>{

        private int hash;
        private Pair<K, V> keyValue;
        private TreeNode<K, V> left;
        private TreeNode<K, V> right;

        TreeNode(K key, V value){
            this.keyValue = new Pair<K, V>(key, value);
            this.hash = key.hashCode();
            this.left = null;
            this.right = null;
        }

        int insert(K key, V value, int keyToSearch){
            int added = 0;
            if(keyToSearch < this.hash){
                if(null == left){
                    left = new TreeNode(key, value);
                    added = 1;
                }else{
                    added = left.insert(key, value, keyToSearch);
                }
            }else if(keyToSearch > this.hash){
                if(null == right){
                    right = new TreeNode(key, value);
                    added = 1;
                }else{
                    added = right.insert(key, value, keyToSearch);
                }
            }else{
                if(keyValue.getKey().equals(key)){
                    keyValue.setvalue(value);
                }else{
                    //TODO:
                }
            }
        return added;
        }

        V find(K key){
            V result = null;
            int toFindHash = key.hashCode();

            if(toFindHash < this.hash){
                if(null != left){
                    result = left.find(key);
                }
            }else if(toFindHash > this.hash){
                if(null != right){
                    result = right.find(key);
                }
            }else{
                if(keyValue.getKey().equals(key)){
                    return keyValue.getValue();
                }else{
                    //TODO:
                }
            }
        return result;
        }

    }
}

