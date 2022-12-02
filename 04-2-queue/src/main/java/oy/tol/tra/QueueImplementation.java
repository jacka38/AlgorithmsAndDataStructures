package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {
    
    private Object [] itemArray;
    private int head;
    private int tail;
    private int capacity;
    private int count;

    public QueueImplementation() throws QueueAllocationException {
        
        this(10);
    }

    public QueueImplementation(int capacity) throws QueueAllocationException {

        if(capacity < 2){
           throw new QueueAllocationException("Queue size should be more than 1");
        }
        try{
           itemArray = new Object[capacity]; 
           this.capacity = capacity;
           head = 0;
           tail = 0;
           count = 0;
  
        }catch(Exception e){
           throw new QueueAllocationException(e.getMessage());
        }
    }

    @Override
    public int capacity() {
        
       return capacity;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {

        if(count >= capacity){
            reallocateArray();
        }
        if(tail >= capacity){
            tail = 0;
        }
        itemArray[tail] = element;
        tail++;
        count++;
    }


    @SuppressWarnings("unchecked")
    @Override
    public E dequeue() throws QueueIsEmptyException {
        
        if(isEmpty()){
            throw new QueueIsEmptyException("Cannot dequeue from empty queue");
        }

        E element = (E) itemArray[head];
        itemArray[head] = null;
        count--;
        head++;
        if(head >= capacity){
            head = 0;
        }
        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E element() throws QueueIsEmptyException{

        if(isEmpty()){
            throw new QueueIsEmptyException("Cannot return head from empty queue");
        }

        E element = (E) itemArray[head];
        return element; 
    }

    @Override
    public int size(){

        int size = count + 1;
        return size;
    }

    @Override
    public boolean isEmpty(){

        return count <= 0;
    }

    @Override
    public void clear(){

        capacity = 10;
        head = 0;
        tail = 0;
        count = 0;
        itemArray = new Object[capacity];
    }

    @Override
    public String toString(){
        
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        if(count > 0){
            int index = head;
            int counter = count;

            while(counter > 0){
                builder.append(itemArray[index]);
                index++;
                counter--;

                if(counter > 0){
                    builder.append(", ");
                }
                if(index >= capacity){
                    index = 0;
                }
            }
        }

        builder.append("]");
        return builder.toString();
    }

    private void reallocateArray(){

        int newCapacity = capacity * 2; 
        Object [] newArray;    

        try{

            newArray = new Object[newCapacity]; 
            
        }catch(Exception e){
               throw new QueueAllocationException("Reallocation for queue array failed.");
        }
        
        int index = head;
        int counter = count;
        int newItemArrayIndex = 0;

        while(counter > 0){
            newArray[newItemArrayIndex] = itemArray[index];
            newItemArrayIndex++;
            index++;
            counter--;
            
            if(index >= capacity){
                index = 0;

            }
        }
        head = 0;
        tail = count;
        capacity = newCapacity;
        itemArray = newArray;
    }

}
