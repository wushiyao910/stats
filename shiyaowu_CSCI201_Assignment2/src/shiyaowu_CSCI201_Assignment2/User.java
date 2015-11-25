package shiyaowu_CSCI201_Assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

public class User {
	private String name;
	private String password;
	private Checking CheckingAccount;
	private Saving SavingAccount;
	public User (String username, String pass, double checkingBalance, double savingBalance)
	{
		this.name=username;
		this.password=pass;
		CheckingAccount=new Checking(checkingBalance);
		if ((savingBalance+checkingBalance)<1000.00 || (savingBalance+checkingBalance)==1000.00)
		{
			SavingAccount=new Basic(savingBalance);
		}
		else if ((savingBalance+checkingBalance)>1000.00 && (savingBalance+checkingBalance)<10000.00)
		{
			SavingAccount = new Premium((savingBalance));
		}
		else if ((savingBalance+checkingBalance)>=10000.00)
		{
			SavingAccount = new Deluxe((savingBalance));
		}
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public Checking getCheckingAccount()
	{
		return this.CheckingAccount;
	}
	
	public Saving getSavingAccount()
	{
		return this.SavingAccount;
	}
	
	public void TypeAdjust()
	{
		if ((CheckingAccount.getBalance()+SavingAccount.getBalance())<=1000.00)
		{
			double tempBalance=this.SavingAccount.getBalance();
			this.SavingAccount=new Basic(tempBalance);
		}
		else if ((CheckingAccount.getBalance()+SavingAccount.getBalance())>1000.00 && (CheckingAccount.getBalance()+SavingAccount.getBalance())<10000.00)
		{
			double tempBalance=this.SavingAccount.getBalance();
			this.SavingAccount=new Premium(tempBalance);
		}
		else if ((CheckingAccount.getBalance()+SavingAccount.getBalance())>=10000.00)
		{
			double tempBalance=this.SavingAccount.getBalance();
			this.SavingAccount=new Deluxe(tempBalance);
		}
	}
	
	public static void main(String [] args)
	{
		Scanner scan=new Scanner(System.in);
		while (true)
		{
			int numUsers = 0;
			FileReader fr1=null;
			try {
				fr1 = new FileReader("userInfo.txt");
				
				BufferedReader br1=new BufferedReader(fr1);
				while(true)
				{
					String line=null;

					line=br1.readLine();

					if (line==null)
					{
						break;
					}
					else
					{
						numUsers++;
					}
				}
				br1.close();
				fr1.close();
			} catch (IOException e1) {
				//e1.printStackTrace();
			} finally
			{

				
				User users[]=new User[numUsers+1];//the extra one is for the possible new user
				int userCount=0;
				
				
				FileReader fr = null;
				try {
					fr = new FileReader("./userInfo.txt");
					BufferedReader br=new BufferedReader(fr);
					while (true)
					{
						String line = br.readLine();

						if (line==null)
						{
							break;
						}
						else
						{
							String [] info = new String[4];
							info=line.split("\\s+");
							String name=info[0];
							String password=info[1];
							double checkingBalance=Double.parseDouble(info[2]);
							double savingBalance=Double.parseDouble(info[3]);
							users[userCount]=new User(name, password, checkingBalance, savingBalance);
							userCount++;
						}
					}
					br.close();
					fr.close();
				} catch (FileNotFoundException e) {
					//e.printStackTrace();
				} catch (IOException e) {
					//e.printStackTrace();
				} finally
				{

						System.out.println("Welcome to the bank.");
						System.out.println("   1) Existing Account Holder");
						System.out.println("   2) Open a New Account");
						System.out.print("What would you like to do?");
						scan=new Scanner(System.in);
						int input1=scan.nextInt();
						

						if (input1==2)
						{
							while (true)
							{
								System.out.print("Username: ");
								boolean error=false;
								String testName=scan.next();
								if (testName.charAt(0)=='q'&&testName.length()==1)
								{
									break;
								}
								else
								{
									if (numUsers>0)
									{
										for (int i=0; i<numUsers; i++)
										{
											if (users[i].getName().equals(testName))
											{
												error=true;
												break;
											}
										}
									}
									
									if (error==true)
									{
										System.out.println("I’m sorry, but the username “"+testName+"” is already associated with an account. Please try again (or enter ‘q’ to return to the main menu).");
									}
									else
									{
										System.out.println("Great, that username is not in use!");
										System.out.print("Password: ");
										String passWord=scan.next();
										System.out.print("How much would you like to deposit in checking? ");
										double CheckBal=scan.nextDouble();
										System.out.print("How much would you like to deposit in savings? ");
										double SavBal=scan.nextDouble();
										users[numUsers]=new User(testName,passWord,CheckBal, SavBal);
										//numUsers++;
										
										FileWriter fw=null;
										try {
											fw=new FileWriter("./userInfo.txt");
										} catch (IOException e) {
											
											//e.printStackTrace();
										}
										PrintWriter pw=new PrintWriter(fw);
										for (int i=0; i<numUsers+1; i++)
										{
											pw.print(users[i].getName()+" "+users[i].getPassword()+" "+users[i].getCheckingAccount().getBalance()+" "+users[i].getSavingAccount().getBalance());
											pw.print("\n");
											pw.flush();
										}
										pw.close();
										try {
											fw.close();
										} catch (IOException e) {
											//e.printStackTrace();
										}

										break;
									}
								}
							}
						}
						else if (input1==1)
						{
							while (true)
							{
								System.out.print("Username: ");
								String tempName=scan.next();
								if (tempName.charAt(0)=='q' && tempName.length()==1)
								{
									break;
								}
								else
								{
									System.out.print("Password: ");
									String tempPass=scan.next();
									int correctIndex = -1;
									boolean checkAccount=false;
									for (int i=0; i<numUsers; i++)
									{
										if (users[i].getName().equals(tempName) && users[i].getPassword().equals(tempPass))
										{
											checkAccount=true;
											correctIndex=i;
										}
									}
									
									if (checkAccount==false)
									{
										System.out.println("I’m sorry, but that username and password does not match any at our bank. Please try again (or enter ‘q’ to return to the main menu).");
									}
									else
									{
										System.out.println("Welcome to your accounts, "+users[correctIndex].getName()+".");
										while(true)
										{
											System.out.println("   1) View Account Information");
											System.out.println("   2) Make a Deposit");
											System.out.println("   3) Make a Withdrawal");
											System.out.println("   4) Determine Balance in x Years");
											System.out.println("   5) Logout");
											System.out.print("What would you like to do? ");
											
											int choice=scan.nextInt();
											System.out.print("\n");
											
											if (choice==1)
											{
												System.out.println("You have a "+users[correctIndex].getCheckingAccount().getAccountType()+ " account with a balance of "+users[correctIndex].getCheckingAccount().getBalance());
												System.out.println("You have a "+users[correctIndex].getSavingAccount().getAccountType()+" account with a balance of "+users[correctIndex].getSavingAccount().getBalance());
												System.out.print("\n");
											}
											else if (choice ==2)
											{

													System.out.println("Here are the accounts you have:");
													System.out.println("   1) "+users[correctIndex].getCheckingAccount().getAccountType());
													users[correctIndex].TypeAdjust();
													System.out.println("   2) "+users[correctIndex].getSavingAccount().getAccountType());
													System.out.print("Into which account would you like to make a deposit? ");
													int accountChoice=scan.nextInt();
													if (accountChoice==1)
													{
														while(true)
														{
															System.out.print("How much to deposit into your "+users[correctIndex].getCheckingAccount().getAccountType()+"?");
															if (!scan.hasNextDouble())
															{
																String tempInput=scan.next();
																System.out.println("“"+tempInput+"” is not a valid amount.");
															}
															else
															{
																double amount=scan.nextDouble();
																boolean result=users[correctIndex].getCheckingAccount().deposit(amount);
																if (result==true)
																{
																	users[correctIndex].TypeAdjust();
																	break;
																}
															}
														}
													}
													else if (accountChoice==2)
													{
														while (true)
														{
															System.out.println("How much to deposit into your "+users[correctIndex].getSavingAccount().getAccountType()+"?");
															if(!scan.hasNextDouble())
															{
																String tempInput=scan.next();
																System.out.println("“"+tempInput+"” is not a valid amount.");
															}
															else
															{
																double amount=scan.nextDouble();
																boolean result=users[correctIndex].getSavingAccount().deposit(amount);
																if (result==true)
																{
																	users[correctIndex].TypeAdjust();
																	break;
																}
															}
														}
													}
													else
													{
														System.out.println("Wrong input! Please choose again.");
													}
												
											}
											else if (choice == 3)
											{

													System.out.println("Here are the accounts you have:");
													System.out.println("   1) "+users[correctIndex].getCheckingAccount().getAccountType());
													users[correctIndex].TypeAdjust();
													System.out.println("   2) "+users[correctIndex].getSavingAccount().getAccountType());
													System.out.print("From which account would you like to withdraw? ");
													int accountChoice=scan.nextInt();
													if (accountChoice==1)
													{
														while (true)
														{
															System.out.print("How much to withdraw? ");
															if (!scan.hasNextDouble())
															{
																String tempInput=scan.next();
																System.out.println("“"+tempInput+"” is not a valid amount.");
															}
															else
															{
																double amount = scan.nextDouble();
																boolean result = users[correctIndex].getCheckingAccount().withdraw(amount);
																if (result==true)
																{
																	users[correctIndex].TypeAdjust();
																	break;
																}
															}
														}
													}
													else if (accountChoice==2)
													{
														while(true)
														{
															System.out.print("How much to withdraw");
															if (!scan.hasNextDouble())
															{
																String tempInput=scan.next();
																System.out.println("“"+tempInput+"” is not a valid amount.");
															}
															else
															{
																double amount = scan.nextDouble();
																boolean result = users[correctIndex].getSavingAccount().withdraw(amount);
																if (result==true)
																{
																	users[correctIndex].TypeAdjust();
																	break;
																}
															}
														}
													}
													else
													{
														System.out.println("Wrong input! Please choose again.");
													}
												
											}
											else if (choice ==4)
											{
												System.out.print("In how many years? ");
												int numYears;
												while (true)
												{
													boolean inputError=false;
													if (!scan.hasNextInt())
													{
														System.out.println("Error: it is not a valid number. Please try again.");
														continue;
													}
													numYears=scan.nextInt();
													if (numYears<0)
													{
														System.out.println("Error: the input is out of range");
														inputError=true;
													}
													
													if (inputError==false)
													{
														break;
													}
												}
												System.out.println("Your "+users[correctIndex].getSavingAccount().getAccountType()+" account will have the following:");
												System.out.println("Year         Amount         Interest");
												System.out.println("----         ------         --------");
												double tempBalance=users[correctIndex].getSavingAccount().getBalance();
												User tempUser=new User("acb", "abc", users[correctIndex].getCheckingAccount().getBalance(), users[correctIndex].getSavingAccount().getBalance());
												DecimalFormat df=new DecimalFormat("#.00");
												System.out.print("0            $"+df.format(tempBalance));
												for (int i=1; i<numYears+1; i++)
												{
													double amountBefore;
													double interest;
													double amountAfter;
													amountBefore=tempUser.getSavingAccount().getBalance();
													amountAfter=tempUser.getSavingAccount().getBalanceAfterNumYears(1);
													interest=amountAfter-amountBefore;
													tempUser.getSavingAccount().setBalance(amountAfter);
													tempUser.TypeAdjust();
													System.out.print("         $"+df.format(interest)+"\n"+i+"            $"+df.format(amountAfter));
												}
												System.out.print("\n");
											}
											else if (choice==5)
											{
												System.out.println("Thank you for coming into the bank!");
												FileWriter fw=null;
												try {
													fw=new FileWriter("./userInfo.txt");
												} catch (IOException e) {
													
													e.printStackTrace();
												}
												PrintWriter pw=new PrintWriter(fw);
												for (int i=0; i<numUsers; i++)
												{
													pw.print(users[i].getName()+" "+users[i].getPassword()+" "+users[i].getCheckingAccount().getBalance()+" "+users[i].getSavingAccount().getBalance());
													pw.print("\n");
													pw.flush();
												}
												pw.close();
												try {
													fw.close();
												} catch (IOException e) {
													e.printStackTrace();
												}
												System.exit(0);;
											}
											else
											{
												System.out.println("Wrong input! Please choose again.");
											}
											
										}
										
									}
								}
							}
						}
						else
						{
							System.out.println("Wrong input! Please choose again.");
						}
						//scan.close();
					
				}
			}
		}
	}

}
