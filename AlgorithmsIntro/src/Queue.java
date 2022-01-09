/**
 * A queue implementation, ie a FIFO queue
 * implemented as a linked list, with 
 * constant time operations for all methods
 * supported operation
 * push
 * pop
 * isEmpty
 * 
 * @author erikkarlsson
 *
 */

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item>
{
   private Node first = null;
   private Node last = null;
   private int N;
   private class Node
   {
      Item item;
      Node next;
   }
   
   public boolean isEmpty()
   {
      return first == null;
   }
   
   public void enqueue(Item item)
   //Add item to end of the list, ie last
   {
      Node oldlast = last;
      last = new Node();
      last.next = null;
      last.item = item;
      if (isEmpty()) first = last;
      else oldlast.next = last;
      N++;
   }
   
   public Item dequeue()
   //Remove item from front of list, ie first
   {
      if (N==0) {throw new IllegalAccessError("There are no items in the queue");}
      Item item = first.item;
      first = first.next;
      if (isEmpty()) last = null;
      N--;
      return item;
   }
   public Iterator<Item> iterator()
   {return new ListIterator();}
   private class ListIterator implements Iterator<Item>
   {
      private Node current = first;
      public boolean hasNext() {return current != null;}
      public void remove() {}
      public Item next()
      {
         Item item = current.item;
         current = current.next;
         return item;
      }
   }
   
   public static void main(String[] args)
   {
      Queue<String> q= new Queue<String>();
      q.enqueue("Kalle");
      q.enqueue("Nalle");
      q.enqueue("Tjalle");
      //System.out.println(q.dequeue());
      //System.out.println(q.dequeue());
      //System.out.println(q.dequeue());
      //System.out.println(q.dequeue());
      for (String fItem: q)
      {
         System.out.println(fItem);
         
      }
   }

}
