package oy.tol.tra;

import java.util.concurrent.atomic.AtomicInteger;

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


    int insert(K key, V value, int keyToSearch){
        int added = 0;
        if(keyToSearch < this.hash){
            if(null == left){
                left = new TreeNode<K, V>(key, value);
                TreeNode.addDepth += 1;
                added = 1;
            }else{
                TreeNode.addDepth += 1;
                added = left.insert(key, value, keyToSearch);
            }
        }else if(keyToSearch > this.hash){
            if(null == right){
                right = new TreeNode<K, V>(key, value);
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
