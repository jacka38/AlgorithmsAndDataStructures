package oy.tol.tra;


public class LinkedListImplementation<E> implements LinkedListInterface<E> {

   private class Node<T> {
      Node(T data) {
         element = data;
         next = null;
      }
      T element;
      Node<T> next;
      @Override
      public String toString() {
         return element.toString();
      }
   }

   private Node<E> head = null;
   private int count = 0;

   @Override
   public void add(E element) throws NullPointerException, LinkedListAllocationException {

      if(null == head){
         head = new Node<E>(element);
         count++;
      }else{
         Node<E> current = head;
         while(current.next != null){
            current = current.next;
         }
         current.next = new Node<E>(element);
         count++;
      }
   }

   @Override
   public void add(int index, E element) throws NullPointerException, LinkedListAllocationException, IndexOutOfBoundsException {

      if(index < 0 || index > count){
         throw new IndexOutOfBoundsException("Index is wrong for this linked list");
      }

      if(index == 0){
         Node<E> newNode = new Node<E>(element);
         newNode.next = head;
         head = newNode;
         count++;
      }else{
         int counter = 0;
         Node<E> current = head;
         Node<E> previous = null;

         while(counter < index){
            previous = current;
            current = current.next;
            counter++;
         }
         Node<E> newNode = new Node<E>(element);
         previous.next = newNode;
         newNode.next = current;
         count++;
      }
   }

   @Override
   public boolean remove(E element) throws NullPointerException {
      // TODO: Implement this.
      return false;
   }

   @Override
   public E remove(int index) throws IndexOutOfBoundsException {

      if(index < 0 || index >= count){
         throw new IndexOutOfBoundsException("Index is wrong for this linked list");
      }

      E removed = null;
      if(index == 0){
         removed = head.element;
         head = head.next;
         count--;
      }else{
         int counter = 1;
         Node<E> current = head.next;
         Node<E> previous = head;

         while(current != null){
            if(counter == index){
               removed = current.element;
               previous.next = current.next;
               count--;
               break;
            }
            counter++;
            previous = current;
            current = current.next;
         }
      }
      return removed;
   }

   @Override
   public E get(int index) throws IndexOutOfBoundsException {
      // TODO: Implement this.
      return null;
   }

   @Override
   public int indexOf(E element) throws NullPointerException {
      // TODO: Implement this.
      return -1;
   }

   @Override
   public int size() {

      return count + 1;
   }

   @Override
   public void clear() {
      // TODO: Implement this.

      Node<E> head = null;
   }

   @Override
   public void reverse() {
      // TODO: implement this only when doing the task explained the TASK-2.md.
      // This method is not needed in doing the task in the README.md.
   }

   @Override
   public String toString() {
      // TODO: Implement this.

      StringBuilder builder = new StringBuilder();
        builder.append("[");

        

        builder.append("]");
        return builder.toString();
   }
   
}
