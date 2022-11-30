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

    @Override
    public E dequeue() throws QueueIsEmptyException {
        //TODO:
    }

    public void clear(){

        capacity = 10;
        head = 0;
        tail = 0;
        count = 0;
        itemArray = new Object[capacity];
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
