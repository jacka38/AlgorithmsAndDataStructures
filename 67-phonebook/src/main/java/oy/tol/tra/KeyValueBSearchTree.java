package oy.tol.tra;

import java.util.concurrent.atomic.AtomicInteger;

public class KeyValueBSearchTree<K extends Comparable<K>,V> implements Dictionary<K, V> {

    // This is the BST implementation, KeyValueHashTable has the hash table implementation
    
    private TreeNode<K, V> root;
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

    @Override
    public String getStatus() {
        StringBuilder builder = new StringBuilder();

        builder.append("Count of tree nodes in BST " + count + "\n");
        builder.append("Max depth in BST is " + maxDepth + "\n");
        builder.append("Max collision in BST is " + maxCollisionChainLength + "\n");

        return builder.toString();
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {

        if(key == null || value == null){
            throw new IllegalArgumentException("paramaters cannot be null");
        }

        if(null == root){
            root = new TreeNode<>(key, value);
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
    public V find(K key) throws IllegalArgumentException {

        if(key == null){
            throw new IllegalArgumentException("paramaters cannot be null");
        }

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
}

