import java.util.Iterator;
import java.util.Scanner;

public class InOut
{

   public static void main(String[] args)
   {
      System.out.printf("Skriv in sökvägen:%n");
      Scanner userInput = new Scanner(System.in);
      //userInput.useDelimiter("[:/]+");
      
      String path;
      //int count = 0;
      
      path = userInput.nextLine();
      String[] paths = new String[20];
      paths = path.split("[:|/]+");
      /*while (userInput.hasNext())
      {
         path[count] = userInput.next();
         userInput.nextLine();
         count++;
      }*/
      
      for (String output : paths)
      {
         System.out.printf("%s%n", output);
      }
   }

}
