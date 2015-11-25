package shiyaowu_CSCI201_Assignment2;

public class Basic extends Saving {

	public Basic(double balance) {
		super(balance);
	}
	
	public double getBalanceAfterNumYears(int numYears)
	{
		double tempBalance=this.getBalance();
		for (int i=0; i<numYears; i++)
		{
			tempBalance=tempBalance*1.001;
		}
		return tempBalance;
	}
	
	public String getAccountType()
	{
		return "Basic Savings";
	}
	

}
