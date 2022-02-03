
public class SetOfIntegers
{
    boolean[] intSet;

    public SetOfIntegers()
    {
        intSet = new boolean[101];
    }
    
    public void addElement(int k)
    {
        if (k < 0 || k > 100 ) throw new IllegalArgumentException("Ange ett tal mellan 0-100");
        intSet[k] = true;
    }
    
    public boolean inSet(int k)
    {
        if (intSet[k]) return true;
        return false;
    }
    /*public  void union(SetOfIntegers set1, SetOfIntegers set2)
    {
        for (int i = 0; i < 101; i++)
        {
            if (set1.inSet(i) || set2.inSet(i)) this.intSet[i] = true;
        }
        
    }*/
    
    public  static SetOfIntegers union(SetOfIntegers set1, SetOfIntegers set2)
    {
        SetOfIntegers retSet = new SetOfIntegers();
        for (int i = 0; i < 101; i++)
        {
            if (set1.inSet(i) || set2.inSet(i)) retSet.intSet[i] = true;
        }
        return retSet;
    }
    public void printSet()
    {
        boolean emptySet = true;
        boolean first = true;
        for (int i = 0; i < intSet.length; i++)
        {
            
            if (intSet[i]) 
            {
                if (first)
                {
                    first = false;
                    emptySet = false;
                    System.out.printf("%d", i);
                }
                else {System.out.printf(",%d", i);}
            }
            
        }
        if (emptySet) System.out.println("-");
    }

    public static void main(String[] args)
    {
        SetOfIntegers set = new SetOfIntegers();
        set.printSet();
        for (int i = 0; i < 30; i++)
        {
            set.addElement(i);
        }
        set.printSet();
        SetOfIntegers set2 = new SetOfIntegers();
        for (int i = 30; i <= 50; i++)
        {
            set2.addElement(i);
        }
        SetOfIntegers set3 = new SetOfIntegers();
        
        set3 = union(set, set2);
        System.out.println();
        set3.printSet();
        
        
    }

}
