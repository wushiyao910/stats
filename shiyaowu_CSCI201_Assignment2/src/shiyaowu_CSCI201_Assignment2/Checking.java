package shiyaowu_CSCI201_Assignment2;

public class Checking extends BaseAccount{

	public Checking(double balance) {
		super(balance);
	}
	
	public double getBalanceAfterNumYears(int numYears)
	{
		return this.getBalance();
	}
	
	public String getAccountType()
	{
		return "Checking";
	}

}
