import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author wushiyao
 *
 */
public class TicTacToe {	
	public static void main (String []args) throws IOException
	{
		int playerCounter=0;
		int grid []=new int [9];
		String result="Neither";
		for (int i=0; i<9; i++)
		{
			grid[i]=0;//initiate each spot to 0, which means unused
		}
		
		System.out.print("Enter the file name as input:");
		Scanner scan=new Scanner(System.in);
		String fileName=scan.nextLine();
		//System.out.println(fileName);
		FileReader fr=new FileReader(fileName);
		BufferedReader br= new BufferedReader (fr);
		while (true)
		{
			playerCounter++;
			String line = br.readLine();
			if (line == null)
			{
				break;
			}
			else
			{
				//Scanner sc2=new Scanner (line);
				String[] arrayInt=new String [2];
				arrayInt=line.split(", ");
				int row = Integer.parseInt(arrayInt[0]);
				int column = Integer.parseInt(arrayInt[1]);
				int number=row*3+column;
				if (grid[number]!=0)
				{
					result="Repeated move";
					break;
				}
				else if (number>8)
				{
					result="Out of bounds";
					break;
				}
				else
				{
					if (playerCounter%2==1)
					{
						grid[number]=1;
					}
					else
					{
						grid[number]=2;
					}
				}
				
				if (grid[0]==1 && grid[1]==1 && grid[2]==1)
				{
					result="Player One";
					break;
				}
				else if (grid[3]==1 && grid[4]==1 && grid[5]==1)
				{
					result="Player One";
					break;
				}
				else if (grid[6]==1 && grid[7]==1 && grid[8]==1)
				{
					result="Player One";
					break;
				}
				else if (grid[0]==1 && grid[3]==1 && grid[6]==1)
				{
					result="Player One";
					break;
				}
				else if (grid[1]==1 && grid[4]==1 && grid[7]==1)
				{
					result="Player One";
					break;
				}
				else if (grid[2]==1 && grid[5]==1 && grid[8]==1)
				{
					result="Player One";
					break;
				}
				else if (grid[0]==1 && grid[4]==1 && grid[8]==1)
				{
					result="Player One";
					break;
				}
				else if (grid[2]==1 && grid[4]==1 && grid[6]==1)
				{
					result="Player One";
					break;
				}
				else if (grid[0]==2 && grid[1]==2 && grid[2]==2)
				{
					result="Player Two";
					break;
				}
				else if (grid[3]==2 && grid[4]==2 && grid[5]==2)
				{
					result="Player Two";
					break;
				}
				else if (grid[6]==2 && grid[7]==2 && grid[8]==2)
				{
					result="Player Two";
					break;
				}
				else if (grid[0]==2 && grid[3]==2 && grid[6]==2)
				{
					result="Player Two";
					break;
				}
				else if (grid[1]==2 && grid[4]==2 && grid[7]==2)
				{
					result="Player Two";
					break;
				}
				else if (grid[2]==2 && grid[5]==2 && grid[8]==2)
				{
					result="Player Two";
					break;
				}
				else if (grid[0]==2 && grid[4]==2 && grid[8]==2)
				{
					result="Player Two";
					break;
				}
				else if (grid[2]==2 && grid[4]==2 && grid[6]==2)
				{
					result="Player Two";
					break;
				}
			}
		}
		
		FileWriter fw=new FileWriter("Output.txt");
		PrintWriter pw = new PrintWriter(fw);
		for (int i=0; i<3; i++)
		{
			for (int j=0; j<3; j++)
			{
				if (grid[i*3+j]==0)
				{
					pw.print('_');
				}
				else if (grid[i*3+j]==1)
				{
					pw.print('X');
				}
				else if (grid[i*3+j]==2)
				{
					pw.print('O');
				}
			}
			pw.print("\n");
		}
		pw.println(result);
		fr.close();
		br.close();
		fw.close();
		pw.close();
		scan.close();
		
	}
		 
}
