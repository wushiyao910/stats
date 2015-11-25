import java.util.Scanner;


public class Array {
	private int arr[];
	public Array (int number)
	{
		arr=new int [number];
		for (int i=0; i<number; i++)
		{
			arr[i]=i+1;
		}
	}
	
	public void BruteForceSearch (int target)
	{
		int check=0;
		int steps=0;
		for (int i=0; i<arr.length; i++)
		{
			steps++;
			if (arr[i]==target)
			{
				check=1;
				System.out.println("The target exists with index "+i+", and took "+steps+" steps.");
			}
		}
		if (check==0)
		{
			System.out.println("The target does not exist");
		}
	}
	
	public void binarySearch(int [] array, int key, int min, int max, int steps)
	{
		steps++;
		if (min>max)
		{
			System.out.println("The target does not exist");
			return;
		}
		else
		{
			int mid=min+((max-min)/2);
			if (array[mid]==key)
			{
				System.out.println("Target founded at index "+mid+", and took "+steps+" steps.");
			}
			else if (key<array[mid])
			{
				binarySearch(array,key, min, mid ,steps);
			}
			else
			{
				binarySearch(array, key, mid,max,steps);
			}
		}
	}
	
	public void binSearch(int target)
	{
		binarySearch(arr, target, 0, arr.length, 0);
	}
	
	public static void main (String [] args)
	{
		Scanner scan= new Scanner (System.in);
		System.out.print("How many numbers are there in the array?");
		int num=scan.nextInt();
		scan.nextLine();
		Array arr=new Array(num);
		System.out.print("What is your target?");
		int target=scan.nextInt();
		scan.nextLine();
		System.out.println("Using Brute Force Search");
		arr.BruteForceSearch(target);
		System.out.println("Using Binary Search");
		arr.binSearch(target);

		scan.close();
	}
	
	public int [] getArray()
	{
		return arr;
	}

}
