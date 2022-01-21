import java.time.LocalTime;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Program som låter användaren skriva när bilar anländer, när de lämnar samt
 * historik sorterat på ankomsttid eller reg nr.
 * Man kan även söka upp platsen bilen står på.
 * 
 * @author erikkarlsson
 *
 */

public class Tentamen_HT21_erikau1
{
   private static Scanner userInput = new Scanner(System.in);

   public static void main(String[] args)
   {
      // Deklarera konstanter
      final int PLACES = 100;
      final int HISTORY_SIZE = 1000;

      // Deklarera variabler
      // Vektor som innehåller alla platser, null om tom, annars regnr
      String[] place = new String[PLACES];
      // Tid på formatet HH::MM för både när man kör in och när man kör ut
      LocalTime[] inTime = new LocalTime[PLACES];
      LocalTime[] outTime = new LocalTime[PLACES];

      // OBS jag tar in weekendrate men har inte sett i uppgiften att det ska hanteras
      // på särskilt sätt
      char[] weekendRate = new char[PLACES];
      int parkingPlace;

      // Historik i fyra vektorer, sorterade så att respektive position avser samma
      // parkering
      String[] historyReg = new String[HISTORY_SIZE];
      LocalTime[] historyIn = new LocalTime[HISTORY_SIZE];
      LocalTime[] historyOut = new LocalTime[HISTORY_SIZE];
      int[] historyCost = new int[HISTORY_SIZE];

      boolean quit;
      char choice;

      // Initiera variabler
      quit = false;

      while (!quit)
      {
         // Skriv ut menyn
         choice = menu();
         System.out.println(choice);
         // Switch för användarinmatningen
         switch (choice)
         {
         // Användaren har valt kör in
         case '1': {
            parkingPlace = driveIn(place, inTime, weekendRate);
            System.out.printf("%nParkera på våning %d, ruta %d", parkingPlace / 25 + 1, parkingPlace % 25 + 1);
            // System.out.printf("%nParkera på våning %d, ruta %d", parkingPlace / 4 + 1,
            // parkingPlace % 25 + 1);
            break;
         }
         // Användaren har valt kör ut
         case '2': {
            driveOut(place, inTime, weekendRate, historyReg, historyIn, historyOut, historyCost);
            break;
         }
         // Användaren har valt att söka på reg nummer
         case '3': {
            searchCar(place);
            break;
         }
         // Användaren har valt parkeringshistorik(ankomsttid)
         case '4': {
            printHistoryIn(historyReg, historyIn, historyOut, historyCost);
            break;
         }
         // Användaren har valt parkeringshistorik(reg nummer)
         case '5': {
            printHistoryIn(historyReg, historyIn, historyOut, historyCost);
            break;
         }
         // Användaren väljer att avsluta programmet
         case 'q': {
            System.out.println("Programmet avslutat");
            quit = true;
            break;
         }
         // Användaren väljer att avsluta programmet, jag har antagit att även stort Q
         // ska resultera i avslut
         case 'Q': {
            quit = true;
            System.out.println("Programmet avslutat");
            break;
         }
         // Felmeddelande om det skulle gå snett
         default: {
            System.out.printf("Här borde vi inte hamna, något har gått snett%n");
            break;
         }
         }
      }
   }

   /**
    * Metod som tar in regnummer och tid för avfärd letar upp regnummer samt
    * beräknar kostnad och lagrar "kvittot" i vektorer avsedda för det slutligen
    * skrivs "kvittot" ut
    */
   public static void searchCar(String[] places)
   {
      String reg;
      reg = regIn();
      for (int i = 0; i < places.length; i++)
      {
         if (places[i] != null)
         {
            if (places[i].equals(reg))
            {
               System.out.printf("%nBilen är parkerad på våning %d, ruta %d", i / 25 + 1, i % 25 + 1);
               return;
            }
         }
      }
      System.out.println("Kunde inte hitta bilen med det registreringsnumret");
   }

   /**
    * Metod som låter bil köra ut och skriver ned historiken
    * 
    * @param place
    * @param inTime
    * @param weekendRate
    * @param historyReg
    * @param historyIn
    * @param historyOut
    * @param historyCost
    */
   public static void driveOut(String[] place, LocalTime[] inTime, char[] weekendRate, String[] historyReg,
         LocalTime[] historyIn, LocalTime[] historyOut, int[] historyCost)
   {
      String reg;
      LocalTime timeOut;
      int pos;
      int cost;
      int parkDayHours = 0;
      int parkOtherHours = 0;
      int historySlot = 0;
      LocalTime tempTime;

      pos = -1;
      reg = regIn();
      timeOut = timeOut();
      for (int i = 0; i < place.length; i++)
      {
         if (place[i] != null)
         {
            if (place[i].equals(reg))
            {
               pos = i;
               break;
            }
         }
      }
      if (pos == -1)
      {
         System.out.printf(
               "%nBilen med regnummer %s kan inte hittas, vänligen ange ett regnummer på en bil som har kört in när du ska köra ut!%n",
               reg);
         return;
      }
      tempTime = inTime[pos];
      // Beräknar bara hela timmar, är ett antagande i koden
      while (tempTime.getHour() < timeOut.getHour())
      {
         if (tempTime.getHour() < 8 || tempTime.getHour() >= 18)
         {
            parkOtherHours += 1;
            tempTime = tempTime.plusHours(1);
         } else
         {
            tempTime = tempTime.plusHours(1);
            parkDayHours += 1;
         }
      }
      cost = parkDayHours * 15 + parkOtherHours * 10;
      for (int i = 0; i < historyReg.length; i++)
      {
         if (historyReg[i] == null)
         {
            historySlot = i;
            break;
         }
      }
      printReceipt(cost, reg, inTime[pos], timeOut);
      // Lägg in parkeringshistoria i rätt slot
      historyReg[historySlot] = reg;
      historyCost[historySlot] = cost;
      historyIn[historySlot] = inTime[pos];
      historyOut[historySlot] = timeOut;

      // Rensa i garaget
      place[pos] = null;

   }

   /**
    * Metod som skriver ut historik baserat på tid som bilen körde in
    * 
    * @param historyReg
    * @param historyIn
    * @param historyOut
    * @param historyCost
    */
   public static void printHistoryIn(String[] historyReg, LocalTime[] historyIn, LocalTime[] historyOut,
         int[] historyCost)
   {
      int historySize;
      int realSize = 0;
      historySize = historyReg.length;
      for (int i = 0; i < historyReg.length; i++)
      {
         if (historyReg[i] == null)
         {
            realSize = i;
            break;
         }
      }
      if (realSize <= 0)
      {
         System.out.println("Ingen historia");
         return;
      }

      // temporära vektorer att sortera mha sortReg
      String[] historyRegTemp = new String[historySize];
      LocalTime[] historyInTemp = new LocalTime[historySize];
      LocalTime[] historyOutTemp = new LocalTime[historySize];
      int[] historyCostTemp = new int[historySize];
      for (int i = 0; i < historyCostTemp.length; i++)
      {
         historyRegTemp[i] = historyReg[i];
         historyInTemp[i] = historyIn[i];
         historyOutTemp[i] = historyOut[i];
         historyCostTemp[i] = historyCost[i];
      }
      sortIn(historyRegTemp, historyInTemp, historyOutTemp, historyCostTemp);
      System.out.println("Regnr IN    UT     KOSTNAD");

      for (int i = 0; i < realSize; i++)
      {

         System.out.printf("%s %s %s %d kr%n", historyRegTemp[i], historyInTemp[i].toString(),
               historyOutTemp[i].toString(), historyCostTemp[i]);
      }
   }

   /**
    * Metod som skriver ut historik, sorterat på reg nummer
    * 
    * @param historyReg
    * @param historyIn
    * @param historyOut
    * @param historyCost
    */
   public static void printHistoryReg(String[] historyReg, LocalTime[] historyIn, LocalTime[] historyOut,
         int[] historyCost)
   {
      int historySize;
      int realSize = 0;
      historySize = historyReg.length;
      for (int i = 0; i < historyCost.length; i++)
      {
         if (historyReg[i] == null)
         {
            realSize = i;
            break;
         }
      }
      if (realSize <= 0)
      {
         System.out.println("Ingen historia");
         return;
      }

      // temporära vektorer att sortera mha sortReg
      String[] historyRegTemp = new String[historySize];
      LocalTime[] historyInTemp = new LocalTime[historySize];
      LocalTime[] historyOutTemp = new LocalTime[historySize];
      int[] historyCostTemp = new int[historySize];
      for (int i = 0; i < historyCostTemp.length; i++)
      {
         historyRegTemp[i] = historyReg[i];
         historyInTemp[i] = historyIn[i];
         historyOutTemp[i] = historyOut[i];
         historyCostTemp[i] = historyCost[i];
      }
      sortReg(historyRegTemp, historyInTemp, historyOutTemp, historyCostTemp);
      System.out.println("Regnr IN    UT    KOSTNAD");

      for (int i = 0; i < realSize; i++)
      {

         System.out.printf("%s %s %s %d kr%n", historyRegTemp[i], historyInTemp[i].toString(),
               historyOutTemp[i].toString(), historyCostTemp[i]);
      }
   }

   /**
    * Metod som skriver ut kvittot efter avslutad parkering
    * 
    * @param cost
    * @param reg
    * @param inTime
    * @param timeOut
    */
   public static void printReceipt(int cost, String reg, LocalTime inTime, LocalTime timeOut)
   {
      /*
       * System.out.println("#########################");
       * System.out.println("#  P-HUS KVITTO         # \n" +
       * "######################### "+"#  Regnr  IN    UT      #");
       * 
       * System.out.printf("#  %s %s %s  #", reg, inTime.toString(),
       * timeOut.toString()); System.out.println("#                       #");
       * System.out.printf("# Avgift %f.2  kr        #%n",cost);
       * System.out.println("#########################");
       */

      System.out.println("#########################");
      System.out.println("#  P-HUS KVITTO         # \n" + "######################### " + "\n#  Regnr  IN    UT      #");

      System.out.printf("#  %s %s %s   #%n", reg, inTime.toString(), timeOut.toString());
      System.out.println("#                       #");
      System.out.printf("#  Avgift %.2f kr      #%n", (double) cost);
      System.out.println("#########################");
   }

   /**
    * Metod som tar in Bilens regnummer och bilens tid för ankomst
    * 
    * @return heltal, plats eller -1 om inga lediga platser
    */
   public static int driveIn(String[] place, LocalTime[] in, char[] weekendRate)
   {
      String reg;
      LocalTime time;
      char rate;
      reg = regIn();
      System.out.printf("%nTid för ankomst (tt:mm):");
      while (true)
      {
         time = inputTime();
         if (!(time.getHour() >= 0 && time.getHour() <= 6))
         {
            break;
         }
         System.out.println("OBS! Förbjudet att parkera mellan 24:00 och 06:00, ange annan tid");
      }
      while (true)
      {
         System.out.printf("%nHelgtaxa (J/N):");
         rate = input();
         if (rate == 'J' || rate == 'N')
         {
            break;
         }
         System.out.printf("%nVänligen ange J eller N:");
      }
      for (int i = 0; i < place.length; i++)
      {
         if (place[i] == null)
         {
            place[i] = reg;
            in[i] = time;
            weekendRate[i] = rate;
            return i;
         }

      }
      System.out.println("Tyvärr finns inga lediga platser");
      return -1;
   }

   /**
    * 1. Metod som skriver ut menyn 2. och anropar metod input 2. för att ta in
    * menyvalet och säkerställa korrekt inmatning 3. Returnera korrekt inmatat val
    * från användaren
    * 
    * @return char som är det val användaren har gjort
    */
   public static char menu()
   {
      // Deklarera variabel
      char choice;

      while (true)
      {
         // Skriv ut menyn
         System.out.printf("%n1. Kör in%n");
         System.out.printf("2. Kör ut%n");
         System.out.printf("3. Sök bil%n");
         System.out.printf("4. Parkeringshistorik (sorterat på ankomsttid)%n");
         System.out.printf("5. Parkeringshistorik (sorterat på regnummer%n");
         System.out.printf("q. Avsluta%n");
         System.out.printf("Ditt val: ");
         choice = input();
         // Säkerställ att valet är korrekt
         if (choice == '1' || choice == '2' || choice == '3' || choice == '4' || choice == '5' || choice == 'q'
               || choice == 'Q')
         {
            break;
         } else
         {
            System.out.printf("%n Vänligen välj ett av alternativen i menyn");
         }
      }
      return choice;
   }

   public static String inputStr()
   {

      String inputString;
      inputString = userInput.nextLine();

      return inputString;
   }

   public static char input()
   {

      char inputChar;
      String inputString;

      // Loopa så länge inte en korrekt inmatning erhållits
      while (true)
      {
         // Extrahera inmatning från användaren
         inputString = userInput.next();
         if (inputString.length() == 1)
         {
            inputChar = inputString.charAt(0);
            userInput.nextLine();
            break;
         } else
         {
            System.out.println("Vänligen mata in endast ett tecken");
         }

      }
      return inputChar;
   }

   public static LocalTime inputTime()
   {

      LocalTime time;
      String inputString;

      // Loopa så länge inte en korrekt inmatning erhållits
      while (true)
      {
         // Extrahera inmatning från användaren
         inputString = userInput.nextLine();
         try
         {
            time = LocalTime.parse(inputString);
            return time;

         } catch (java.time.format.DateTimeParseException e)
         {
            System.out.println("Vänligen mata in en korrekt tid");
         }

      }

   }

   public static String regIn()
   {
      String reg = "";
      boolean matchFound = false;

      System.out.printf("Bilens regnummer:");
      while (!matchFound)
      {
         reg = inputStr();
         matchFound = Pattern.matches("[a-zA-Z]{3}\\d{3}", reg);
         if (!matchFound)
            System.out.println("Ange ett korrekt regnr:");
      }
      return reg;
   }

   public static LocalTime timeIn()
   {
      LocalTime time;
      time = inputTime();
      return time;
   }

   public static LocalTime timeOut()
   {
      LocalTime time;
      System.out.println("Tid för avfärd (tt:mm):");
      time = inputTime();
      return time;
   }

   /**
    * Sort tar en array a och en boolean som anger om det ska sortera i stigande
    * eller fallande ordning 1. Loop i från noll till längd minus ett 1.1 sätt min
    * el max till första kvarvarande variabel att sortera 1.2 Loopa k från i+1 til
    * till längden av arrayen 1.2.1 Om stigande, kolla om position k är < än
    * minormax, i så fall uppdatera minormax 1.2.2 Annars fallande, kolla om
    * position k är > än minormax, i så fall uppdatera minormax 1.2.3 Byt ut a[i]
    * mot minsta el. högsta värdet
    * 
    * @param a,         array att sortera
    * @param ascending, sortera i ökande eller fallande ordning
    */
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

            if (historyReg[k].compareTo(historyReg[minOrMax]) < 0)
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
}