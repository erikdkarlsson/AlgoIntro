/**
 * Comment below outdated, now updated to handle all numbers and also trim of all whitespace,
 * also ignores all empty strings that may be parsed,
 * the parsing could probably be improved but the algorithm 
 * is probably satisfactory for most simple impl.
 * 
 * 
 * 
 * Implementation of Dijkstras algorithm, currently can only handle number 0-9,
 * will maybe be improved later implementation in Algorithms uses Standard in
 * stream and takes each part as a string, maybe use a string.split here to
 * achieve the same effect(split on *,+,-,/,(,) and whitespace
 * 
 * @author erikkarlsson
 *
 */

public class DijkstraArithmetcEval
{
   public static void main(String[] args)
   {
      Stack<Character> opStack = new Stack<Character>();
      Stack<Integer> valStack = new Stack<Integer>();

      String test = "( 10 + ( ( 2+3)*(4*5)))"; // Should eval to 10
      
      String[] splits = test.split("((?=[+]|[*]|[-]|[/]|[(]|[)])|(?<=[+]|[*]|[-]|[/]|[(]|[)]))");
      for (int i = 0; i< splits.length;i++)
      {
         splits[i] = splits[i].trim();
         //System.out.println(splits[i]);
      }
      for (int i = 0; i < splits.length; i++)
      {
         if (splits[i].equals("+"))
            opStack.push('+');
         else if (splits[i].equals("*"))
            opStack.push('*');
         else if (splits[i].equals("/"))
            opStack.push('/');
         else if (splits[i].equals("-"))
            opStack.push('-');
         // Evaluate two vals and the operator
         else if (splits[i].equals(")"))
         {
            char op = opStack.pop();
            int val1 = valStack.pop();
            int val2 = valStack.pop();
            // System.out.printf("val1: %d, val2: %d, op: %c%n", val1,val2, op);
            switch (op)
            {
            case '+':
               valStack.push(val1 + val2);
               break;
            case '*':
               valStack.push(val1 * val2);
               break;
            case '/':
               valStack.push(val2 / val1);
               break;
            case '-':
               valStack.push(val2 - val1);
               break;

            }
         }
         // Ignore whitespace and left paren
         else if (!splits[i].equals("(") && !splits[i].equals(""))
         {
            valStack.push(Integer.parseInt(splits[i]));
            // System.out.println(valStack.size());
         }

      }
      /*
       * for (int i =0;i<test.length();i++) { if (test.charAt(i) == '+')
       * opStack.push('+'); else if (test.charAt(i) == '*') opStack.push('*'); else if
       * (test.charAt(i) == '/') opStack.push('/'); else if (test.charAt(i) == '-')
       * opStack.push('-');
       * 
       * //Evaluate two vals and the operator else if (test.charAt(i) == ')') { char
       * op =opStack.pop(); int val1 = valStack.pop(); int val2 = valStack.pop();
       * //System.out.printf("val1: %d, val2: %d, op: %c%n", val1,val2, op); switch
       * (op) { case '+': valStack.push(val1+val2); break; case '*':
       * valStack.push(val1*val2); break; case '/': valStack.push(val2/val1); break;
       * case '-': valStack.push(val2-val1); break;
       * 
       * } } //Ignore whitespace and left paren else if (test.charAt(i) != ' ' &&
       * test.charAt(i) != '(') {
       * valStack.push(Character.getNumericValue(test.charAt(i)));
       * //System.out.println(valStack.size()); } }
       */
      System.out.println(valStack.pop());
   }

}
