
public class Test {
	public static void main (String [] args)
	{
		Pet myRooster=new Rooster();
		Pet myViper=new Viper();
		
		myRooster.setName("Rudy");
		myViper.setName("Vince");
		System.out.println(myRooster.speak());
		System.out.println(myViper.speak());
		
		if (myRooster instanceof Nameable)
		{
			System.out.println("myRooster is Nameable");
		}
		else
		{
			System.out.println("myRooster is not Nameable");
		}
		
		if (myRooster instanceof Pet)
		{
			System.out.println("myRooster is a pet");
		}
		else
		{
			System.out.println("myRooster is not a pet");
		}
		
		if (myRooster instanceof Viper)
		{
			System.out.println("myRooster is a viper");
		}
		else
		{
			System.out.println("myRooster is not a viper");
		}
	}
	
}
