package shiyaowu_CSCI201_Assignment2;

public abstract class BaseAccount {
	private double balance;
	
	public BaseAccount(double balance) {
		setBalance(balance);
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	// returns the balance after numYears has passed
	// if the account has interest, this method will account for it
	protected abstract double getBalanceAfterNumYears(int numYears);
	// returns a string representing the type of account
	// such as “Checking”, “Deluxe Savings”, etc.
	// Note that the quotation marks will need to be changed if you
	// try to copy and paste from here into Eclipse
	public abstract String getAccountType();
	public boolean withdraw(double amount) {
	// you need to fill the content of this method
		if (amount<0)
		{
			System.out.println("You are not allowed to withdraw a negative amount.");
			return false;
		}
		else
		{
			if (amount>this.balance)
			{
				System.out.println("You do not have "+amount+" in your "+this.getAccountType()+" account.");
				return false;
			}
			else
			{
				this.balance=this.balance-amount;
				System.out.println("$"+amount+" withdrawn from your "+this.getAccountType()+" account.");
				return true;
			}
		}
	}
	public boolean deposit(double amount) {
	// you need to fill the content of this method
		if (amount<0)
		{
			System.out.println("You are not allowed to deposit a negative amount.");
			return false;
		}
		else
		{
			this.balance=this.balance+amount;
			System.out.println("$"+amount+" deposited into your "+this.getAccountType()+" account.");
			return true;
		}
	}
}
