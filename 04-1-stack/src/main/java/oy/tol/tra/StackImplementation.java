package oy.tol.tra;

/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 * 
 * Note that you need to implement construtor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with size.
 *  -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
public class StackImplementation<E> implements StackInterface<E> {

   // TODO: Add member variables needed.
   // Do not use constant values in code, e.g. 10. Instead, define a constant for that. For example:
   // private static final int MY_CONSTANT_VARIABLE = 10;
   
   private E[] array;
   private int currentIndex;
   private int capacity;
   private static final int MY_CONSTANT_VARIABLE = 10;

   /**
    * Allocates a stack with a default capacity.
    * @throws StackAllocationException
    */
   public StackImplementation() throws StackAllocationException {
      // TODO: call the constructor with size parameter with default size (see member variable!).
      this(MY_CONSTANT_VARIABLE);
   }

   /** TODO: Implement so that
    * - if the size is less than 2, throw StackAllocationException
    * - if the allocation of the array throws with Java exception,
    *   throw StackAllocationException.
    * @param capacity The capacity of the stack.
    * @throws StackAllocationException If cannot allocate room for the internal array.
    */
   public StackImplementation(int capacity) throws StackAllocationException {
      E[] array = (E[]) new Object[capacity]; 
      this.capacity = capacity;
      currentIndex = -1;
   }

   @Override
   public int capacity() {
      // TODO: Implement this
      return 0;
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      // TODO: Implement this
      if(currentIndex >= capacity - 1){
         reallocateArray();
      }
      currentIndex++;
      array[currentIndex] = element;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {
      // TODO: Implement this
      if(isEmpty()){
         throw new StackIsEmptyException("Cannot pop from empty Stack!");
      }
      
      E element = array[currentIndex];
      array[currentIndex] = null;

      return element;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      // TODO: Implement this
      return null;
   }

   @Override
   public int size() {
      // TODO: Implement this
      return 0;
   }

   @Override
   public void clear() {
      // TODO: Implement this
   }

   @Override
   public boolean isEmpty() {
      // TODO: Implement this
      if(currentIndex < 0){
         return true;
      }else{
         return false;
      }
   }

   @Override
   public String toString() {
      // TODO: Implement this
      return "";
   }

   private void reallocateArray(){
      int newCapacity = capacity * 2; 
      E[] newArray = (E[]) new Object[newCapacity];

      for(int index = 0; index <= currentIndex; index++){
         newArray[index] = array[index];
      }
   }

}
