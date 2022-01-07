/**
 * A stack implementation,  ie a LIFO queue
 *  * implemented as a linked list, with 
 * constant time operations for all methods
 * push
 * pop
 * isempty
 * size
 * @author erikkarlsson
 */

public class Stack<Item>
{
   private Node first = null;
   private int N;
   
   private class Node //16 byte overhead + extra inner class overhead 8 bytes
   {
      Item item; //Pointer/reference to or the data itself, 8 byte plus object or primitive type 
      Node next; //Pointer/reference to next Node, 8 bytes
   }
   //Return true if 
   public boolean isEmpty()  { return first == null;}
   public int size() {return N;}
   
   public void push(Item item)
   {
      Node oldfirst = first;
      first = new Node();
      first.item = item;
      first.next = oldfirst;
      N++;
   }
   
   public Item pop()
   {
      if (first == null) throw new IllegalArgumentException("No more items in stack");
      Item item = first.item;
      first = first.next;
      N--;
      return item;
   }
   
   public static void main(String[] args)
   {
      Stack<String> s = new Stack<String>();
      s.push("Kalle");
      s.push("Nalle");
      s.push("Tjalle");
      System.out.println(s.pop());
      System.out.println(s.pop());
      System.out.println(s.isEmpty());
      System.out.println(s.pop());
      System.out.println(s.isEmpty());
      //This one tests the exception, should throw "No more items in stack" 
      System.out.println(s.pop());
   }

}
