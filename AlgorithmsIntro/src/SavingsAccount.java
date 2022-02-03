
public class SavingsAccount
{
    static double rate=0.5;
    double balance;
    
    public SavingsAccount(double balance)
    {
        this.balance = balance;
    }
    public void calculateMonthlyInterest()
    {
        balance = balance + (balance*rate)/12;
    }
    
    public static void setRate(double newRate)
    {
        if (newRate < 0 || newRate >1) throw new IllegalArgumentException("Ränta ska vara mellan 0 och 100%");
        rate = newRate;
    }
    
    public double getBalance()
    {
        return balance;
    }
    

    public static void main(String[] args)
    {
        SavingsAccount saving1 = new SavingsAccount(2000);
        SavingsAccount saving2 = new SavingsAccount(3000);
        
        SavingsAccount.setRate(0.01);
        saving1.calculateMonthlyInterest();
        saving2.calculateMonthlyInterest();
        
        System.out.println(saving1.getBalance());
        System.out.println(saving2.getBalance());
        
        
         
            
    }
    
}
