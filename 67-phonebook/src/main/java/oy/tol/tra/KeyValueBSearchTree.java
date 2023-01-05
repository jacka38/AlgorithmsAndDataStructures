package oy.tol.tra;

import java.util.concurrent.atomic.AtomicInteger;

public class KeyValueBSearchTree<K extends Comparable<K>,V> implements Dictionary<K, V> {

    // This is the BST implementation, KeyValueHashTable has the hash table implementation
    
    private TreeNode root;
    private int count = 0;
    private int maxDepth = 0;
    private int maxCollisionChainLength = 0;

    public KeyValueBSearchTree(){
        this.root = null;

    }

    @Override
    public Type getType() {
       return Type.BST;
    }
 
    @Override
    public int size() {

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
        builder.append("Max depth in BST is " + maxDepth + "\n");
        builder.append("Max collision in BST is " + maxCollisionChainLength + "\n");

        return builder.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {

        if(key == null){
            throw new IllegalArgumentException("paramaters cannot be null");
        }

        if(null == root){
            root = new TreeNode(key, value);
            count++;
            maxDepth = 1;
            maxCollisionChainLength = 0;
            return true;
        }else{
            TreeNode.addDepth = 1;
            maxCollisionChainLength = 0;
            int added = root.insert(key, value, key.hashCode());
            if(added > 0){
                count++;
                maxDepth = Math.max(TreeNode.addDepth, maxDepth);
                maxCollisionChainLength = Math.max(TreeNode.collisionChainLength, maxCollisionChainLength);
                return true;
            }
        }
        count++;
        return false; 
    }

    @Override
    @SuppressWarnings("unchecked")
    public V find(K key) throws IllegalArgumentException {

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
    @SuppressWarnings("unchecked")
    public Pair<K,V> [] toSortedArray() {

        if(null == root){
            return null;
        }
        
        Pair<K,V>[] returnArray = (Pair<K,V>[])new Pair[count];
        AtomicInteger toAddIndex = new AtomicInteger();
        root.toSortedArray(returnArray, toAddIndex);
        Algorithms.fastSort(returnArray);
        return returnArray;
    }
    

    public class TreeNode<K extends Comparable<K>,V>{

        private int hash;
        private Pair<K, V> keyValue;
        private TreeNode<K, V> left;
        private TreeNode<K, V> right;
        private LinkedListImplementation<Pair<K,V>> collisionChain;

        public static int addDepth = 0;
        public static int collisionChainLength = 0;
        

        TreeNode(K key, V value){
            this.keyValue = new Pair<K, V>(key, value);
            this.hash = key.hashCode();
            this.left = null;
            this.right = null;
            this.collisionChain = null;
        }

        void toSortedArray(Pair<K,V>[] array, AtomicInteger toAddIndex) {

            if (left != null) {
                left.toSortedArray(array, toAddIndex);
            }
            array[toAddIndex.getAndIncrement()] = new Pair<>(keyValue.getKey(), keyValue.getValue());

            if (collisionChain != null) {
                for (int index = 0; index < collisionChain.size(); index++) {
                    Pair<K,V> found = collisionChain.get(index);
                    if (null != found) {
                        array[toAddIndex.getAndIncrement()] = new Pair<>(found.getKey(), found.getValue());
                    }
                }
            }
            
            if (right != null) {
                right.toSortedArray(array, toAddIndex);
            }
        }

        @SuppressWarnings("unchecked")
        int insert(K key, V value, int keyToSearch){
            int added = 0;
            if(keyToSearch < this.hash){
                if(null == left){
                    left = new TreeNode(key, value);
                    TreeNode.addDepth += 1;
                    added = 1;
                }else{
                    TreeNode.addDepth += 1;
                    added = left.insert(key, value, keyToSearch);
                }
            }else if(keyToSearch > this.hash){
                if(null == right){
                    right = new TreeNode(key, value);
                    TreeNode.addDepth += 1;
                    added = 1;
                }else{
                    TreeNode.addDepth += 1;
                    added = right.insert(key, value, keyToSearch);
                }
            }else{
                if(keyValue.getKey().equals(key)){
                    keyValue.setvalue(value);
                }else{
                    //TODO:
                    if(null == collisionChain){
                        collisionChain = new LinkedListImplementation<Pair<K,V>>();
                        collisionChain.add(new Pair<>(key, value));
                        added = 1;
                        collisionChainLength = 1;
                    }else if(collisionChain.size() > 0){
                        
                        int index = collisionChain.indexOf(keyValue);
                        if(index < 0){
                            collisionChain.add(new Pair<K, V>(key, value));
                            added = 1;
                        }else{
                            collisionChain.remove(index);
                            collisionChain.add(new Pair<K, V>(key, value));
                        }
                        TreeNode.collisionChainLength = collisionChain.size();
                    }
                    
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
                    if(null != collisionChain){
                        
                        int index = collisionChain.indexOf(keyValue);
                        if(index >= 0){
                            return collisionChain.get(index).getValue();
                        }
                    }
                }
            }
        return result;
        }

    }
}

