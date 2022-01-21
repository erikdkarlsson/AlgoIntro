import java.time.LocalTime;
import java.util.Scanner;
import java.util.regex.*;

//import jdk.vm.ci.meta.Local;

public class Test12
{
   private static Scanner userInput = new Scanner(System.in);
   public static void main(String[] args)
   {
      final int HISTORY_SIZE = 1000;

   

      // Historik i fyra vektorer, sorterade så att respektive position avser samma
      // parkering
      String[] historyReg = new String[HISTORY_SIZE];
      LocalTime[] historyIn = new LocalTime[HISTORY_SIZE];
      LocalTime[] historyOut = new LocalTime[HISTORY_SIZE];
      int[] historyCost = new int[HISTORY_SIZE];
      
      historyCost[0] = 30;
      historyReg[0] ="ACC123";
      historyIn[0] = LocalTime.parse("11:00");
      historyOut[0] = LocalTime.parse("13:00");
      
      historyCost[1] = 30;
      historyReg[1] ="ABC121";
      historyIn[1] = LocalTime.parse("10:00");
      historyOut[1] = LocalTime.parse("13:00");
      historyCost[2] = 30;
      historyReg[2] ="ABC125";
      historyIn[2] = LocalTime.parse("12:00");
      historyOut[2] = LocalTime.parse("13:00");
      historyCost[3] = 30;
      historyReg[3] ="ACC112";
      historyIn[3] = LocalTime.parse("14:00");
      historyOut[3] = LocalTime.parse("13:00");
      sortIn(historyReg, historyIn, historyOut, historyCost);
      int realSize =0;
      for (int i = 0; i < historyCost.length; i++)
      {
         if (historyReg[i] == null)
         {
            realSize = i;
            break;
         }
      }
      for (int i = 0; i < realSize; i++)
      {
         //System.out.printf("%d",i);
         System.out.printf("%s %s %s %d kr%n", historyReg[i], historyIn[i].toString(),
               historyOut[i].toString(), historyCost[i]);
      }
      String reg;
      //Pattern pattern = Pattern.compile("[a-zA-Z]{3}\\d{3}");
      boolean matchFound = false;
      System.out.printf("Bilens regnummer:");
      while (!matchFound)
      {
         
         
         reg = inputStr();
         matchFound = Pattern.matches("[a-zA-Z]{3}\\d{3}",reg);
      }
   }
   public static String inputStr()
   {

      String inputString;
      inputString = userInput.nextLine();

      return inputString;
   }
   public static void sortIn(String[] historyReg, LocalTime[] historyIn, LocalTime[] historyOut, int[] historyCost)
   {

      // Deklarera variabler
      int len;
      String temp;
      LocalTime temp2;
      LocalTime temp3;
      int temp4;
      int minOrMax;
      
      

      // Initiera variabler
      len = historyReg.length;
      temp = historyReg[0];
      minOrMax = 0;

      for (int i = 0; i < historyReg.length; i++)
      {
         if (historyReg[i] == null) 
         {
            len = i-1;
            break;
         }
      }
      if (len <=1)
      {
         return;
      }
      // Börja från vänster i arrayen
      for (int i = 0; i < (len - 1); i++)
      {
         minOrMax = i;
         // För varje position, kolla kvarvarande postioner, om någon är mindre el.
         // större
         for (int k = (i + 1); k < len; k++)
         {
            
           
               if (historyIn[k].compareTo(historyIn[minOrMax]) < 0)
               {
                  minOrMax = k;
               }
            
         }

         // Flytta minsta talet som hittas till nuvarande pos i array(inget händer om det
         // redan var minsta el. största beroende på typ av sortering)
         temp = historyReg[i];
         temp2 = historyIn[i];
         temp3 = historyOut[i];
         temp4 = historyCost[i];

         historyReg[i] = historyReg[minOrMax];
         historyIn[i] = historyIn[minOrMax];
         historyOut[i] = historyOut[minOrMax];
         historyCost[i] = historyCost[minOrMax];

         historyReg[minOrMax] = temp;
         historyIn[minOrMax] = temp2;
         historyOut[minOrMax] = temp3;
         historyCost[minOrMax] = temp4;

      }

   }

   public static void sortReg(String[] historyReg, LocalTime[] historyIn, LocalTime[] historyOut, int[] historyCost)
   {

      // Deklarera variabler
      int len;
      String temp;
      LocalTime temp2;
      LocalTime temp3;
      int temp4;
      int minOrMax;

      // Initiera variabler
      len = historyReg.length;
      temp = historyReg[0];
      minOrMax = 0;

      for (int i = 0; i < historyCost.length; i++)
      {
         if (historyReg[i] == null)
         {
            len = i - 1;
            break;
         }
      }
      if (len <= 1)
      {
         return;
      }
      // Börja från vänster i arrayen
      for (int i = 0; i < (len - 1); i++)
      {
         minOrMax = i;
         // För varje position, kolla kvarvarande postioner, om någon är mindre el.
         // större
         for (int k = (i + 1); k < len; k++)
         {
            // if (a[k][0] > a[minOrMax][0])

            if (historyReg[k].compareTo(historyReg[minOrMax]) > 0)
            {
               minOrMax = k;
            }

         }

         // Flytta minsta talet som hittas till nuvarande pos i array(inget händer om det
         // redan var minsta el. största beroende på typ av sortering)
         temp = historyReg[i];
         temp2 = historyIn[i];
         temp3 = historyOut[i];
         temp4 = historyCost[i];

         historyReg[i] = historyReg[minOrMax];
         historyIn[i] = historyIn[minOrMax];
         historyOut[i] = historyOut[minOrMax];
         historyCost[i] = historyCost[minOrMax];

         historyReg[minOrMax] = temp;
         historyIn[minOrMax] = temp2;
         historyOut[minOrMax] = temp3;
         historyCost[minOrMax] = temp4;

      }

   }
}
