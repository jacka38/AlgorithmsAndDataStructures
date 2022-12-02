package oy.tol.tra;

import java.util.Currency;

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
   private int size = 0;
   private int count;

   @Override
   public void add(E element) throws NullPointerException, LinkedListAllocationException {
      // TODO: Implement this.
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
      // TODO: Implement this.

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
      // TODO: Implement this.
      return null;
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
      // TODO: Implement this.
      size = count;
      return size;
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
