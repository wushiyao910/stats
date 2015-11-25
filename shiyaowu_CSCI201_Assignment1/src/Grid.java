import java.util.Scanner;


public class Grid {
	private int row;//Here are three private variables: number of rows
	private int column;//number of columns
	private char content [];//those letters
	
	public void setRow(int r)//the method to set the number of rows
	{
		this.row=r;
	}
	
	public void setColumn(int c)//the method to set the number of columns
	{
		this.column=c;
	}
	
	public void setContent(int n, char c [])//the method to set the letters
	{//the parameters are the number of letters, and the array of the letters
		this.content=new char [n];
		this.content=c;
	}
	
	public void adminResult()//the method to show the result for the admin
	{
		
		System.out.println("Here is the grid, just for you admin!");
		for (int i=0; i<this.row; i++)//use two for loops to show the grid
		{
			for (int j=0; j<this.column; j++)
			{
				System.out.print(this.content[i*(this.column)+j]+" ");
			}
			System.out.print("\n");
		}

		System.out.println("Here is the step by step, just for you admin!");
		int rowNumber=0;//showing the steps
		int direction=0;//0=downwards, 1=upwards
		for (int i=0; i<this.column; i++)
		{
			if (rowNumber==0)
			{
				direction=0;//when reach the upper bound, switch to go downwards
			}
			else if (rowNumber==(this.row-1))
			{
				direction=1;//when reach the lower bound, switch to go upwards
			}
			System.out.println("R:"+rowNumber+" C:"+i+" L:"+this.content[rowNumber*(this.column)+i]);
			//System.out.print(this.content[rowNumber*(this.column)+i]);
				
			if (direction==0)
			{
				if (rowNumber!=this.row-1)
				{
					rowNumber++;
				}
			}
			else
			{
				rowNumber--;
			}

		}
		
		normalResult();//call the method to show normal result, to show the final result
		System.out.print("\n");
		System.out.println("Thanks for using this program!");
	}
	
	public void normalResult()//the method to show the result for non-admin
	{
		int rowNumber=0;
		int direction=0;//0=downwards, 1=upwards
		System.out.print("Result: ");
		for (int i=0; i<this.column; i++)
		{
			if (rowNumber==0)
			{
				direction=0;//switch to go downwards
			}
			else if (rowNumber==(this.row-1))
			{
				direction=1;//switch to go upwards
			}
			
			System.out.print(this.content[rowNumber*(this.column)+i]);
				
			if (direction==0)
			{
				if (rowNumber!=this.row-1)
				{
					rowNumber++;
				}
			}
			else
			{
				rowNumber--;
			}

		}
	}
	
	public static void main (String [] args)
	{
		Grid grid=new Grid();
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to the Maze Game!");
		
		System.out.println("Are you an admin?");
		char admin=scan.next().charAt(0);
		scan.nextLine();
		
		Scanner sc1=null;
		int rows;
		System.out.println("How many rows are in the grid?");
		while (true)
		{
			boolean error=false;
			sc1=new Scanner (System.in);
			if (!sc1.hasNextInt())//check if the input from user is an integer
			{
				System.out.println("Error: that is not a number. Please try again");
				continue;
			}
			rows=sc1.nextInt();
			if (rows<1)//check if the input is out of range
			{
				System.out.println("Error: the input is not in the range");
				error=true;
			}
			
			if (error==false)
			{
				break;
			}
			
		}
		grid.setRow(rows);
		//sc1.close();

		
		Scanner sc2=null;
		int columns;
		System.out.println("How many columns are in the grid?");
		while (true)
		{
			boolean error=false;
			sc2=new Scanner (System.in);
			if (!sc2.hasNextInt())//check if the input from user is an integer
			{
				System.out.println("Error: that is not a number. Please try again");
				continue;				
			}
			columns=sc2.nextInt();
			if (columns<1)//check if the input is out of range
			{
				System.out.println("Error: the input is not in the range");
				error=true;				
			}
			
			if (error==false)
			{
				break;
			}
			
			
		}
		grid.setColumn(columns);

		int numLetters=rows*columns;
		
		Scanner sc3=null;
		char[] Array=new char [numLetters];
		String arrayString [];
		System.out.println("Enter "+numLetters+" letters separated by spaces");
		while (true)
		{
			sc3=new Scanner (System.in);
			String input=sc3.nextLine();
			
			
			arrayString=input.split("\\s+");//divide the input into letters by whitespace
			if (arrayString.length!=numLetters)//check if the input has the required number of letters
			{
				System.out.println("Error: That is not "+numLetters+" letters. Try again!");
			}
			else
			{
				break;
			}


		}
	
		for (int i=0; i<numLetters; i++)
		{
			Array[i]=arrayString[i].charAt(0);
		}
		
		grid.setContent(numLetters, Array);
		
		if (admin=='A')
		{
			grid.adminResult();
		}
		else
		{
			grid.normalResult();
		}
		
		
		scan.close();
		sc1.close();
		sc2.close();
		sc3.close();
		
	}

}
