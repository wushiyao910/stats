package shiyaowu_CSCI201_Assignment2;

public class Premium extends Saving {

	public Premium(double balance) {
		super(balance);
	}
	
	public double getBalanceAfterNumYears(int numYears)
	{
		double tempBalance=this.getBalance();
		for (int i=0; i<numYears; i++)
		{
			tempBalance=tempBalance*1.01;
		}
		return tempBalance;
	}
	
	public String getAccountType()
	{
		return "Premium Savings";
	}
	

}
