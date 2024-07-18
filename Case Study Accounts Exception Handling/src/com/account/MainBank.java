package com.account;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainBank 
{
	// Group Name : Kolar
	//Group Member : 1. Purva Warke  2.Asmita Pawar  3.Renuka Kalyankar

	public static Scanner sc=new Scanner(System.in);
	static int  accountNo = 101101101;
	public static int choice=0;
//	static Account[] arrAcc = new Account[50];
	 static List<Account> arrAcc=new ArrayList<Account>();
	public static void main(String[] args)
	{
		bankOperation();
	}
	
	public static void bankOperation()
	{	
		do 
		{
			System.out.println("\n|--------------------------------------------------|");
			System.out.println("|             BANK MANAGEMENT SYSTEM               |");
			System.out.println("|--------------------------------------------------|");
			System.out.println("|   Option   |             Operations              |");
			System.out.println("|--------------------------------------------------|");
			System.out.println("|      1     |          Login As Admin             |");
			System.out.println("|      2     |          Login As User              |");
			System.out.println("|      3     |          Open New Account           |");
			System.out.println("|      0     |               Exit                  |");
			System.out.println("|--------------------------------------------------|");
			int count=0;
//			System.out.println("\nEnter Choice : ");
			choice=choiceInput();
			switch (choice)
			{
				case 1: 
				{
					if(adminCheck()==0)
						admin();
					break;
				}
				case 2: 
				{
					
						user();
						break;
				}
				case 3: 
				{
					openAccount();
					break;
				}
				case 0:
					System.out.println("THANK YOU !!");
			}
		}while(choice!=0);
	}
	
	//////////////////////////////////////// ASMITA PAWAR ////////////////////////////////////////////
	//admin accounts
	public static void admin()
	{
		System.out.println("\n|--------------------------------------------------|");
		System.out.println("|             BANK MANAGEMENT SYSTEM               |");
		System.out.println("|--------------------------------------------------|");
		System.out.println("|   Option   |             Operations              |");
		System.out.println("|--------------------------------------------------|");
		System.out.println("|      1     |    Display All Account Details      |");
		System.out.println("|      2     |         Daily Report                |");
		System.out.println("|      3     |       Single Account Details        |");
		System.out.println("|      0     |             <- Back                 |");
		System.out.println("|--------------------------------------------------|");
	
		do
		{
//			System.out.println("\nEnter Choice : ");
			choice = choiceInput();
			switch (choice) 
			{
				case 1:
				{
					Account.displayAll();
					break;
				}
				case 2:
				{
					Transaction.transactionDisplay();
					break;
				}
				case 3:
				{
					int ind=findAccount();
					arrAcc.get(ind).display();
					break;
				}
				case 0:
					break;
				}
		}while(choice!=0);

		}	

//////////////////////////////////////////// PURVA WARKRE ////////////////////////////////////////
	//user accounts
	public static void user()
	{
		int acc=findAccount();
		if(acc!=-1)
		{
			if(arrAcc.get(acc).userCheck()==0)
			{
				System.out.println("\n|--------------------------------------------------|");
				System.out.println("|             BANK MANAGEMENT SYSTEM               |");
				System.out.println("|--------------------------------------------------|");
				System.out.println("|   Option   |             Operations              |");
				System.out.println("|--------------------------------------------------|");
				System.out.println("|      1     |              Deposite               |");
				System.out.println("|      2     |              Withdraw               |");
				System.out.println("|      3     |              Transfer               |");
				System.out.println("|      4     |         Calculate Interest          |");
				System.out.println("|      5     |            Check Balance            |");
				System.out.println("|      6     |            Close Account            |");
				System.out.println("|      0     |             <- Back                 |");
				System.out.println("|--------------------------------------------------|");
//				System.out.println("\nEnter Choice : ");
				choice = choiceInput();
			
				switch (choice) 
				{
					case 1:
					{
						double amount = arrAcc.get(acc).deposite();
						Transaction.trr.add(new Transaction(accountNo,"Deposited in Account",amount));
						break;
					}
					case 2:
					{
						double amount = arrAcc.get(acc).withdraw();
						Transaction.trr.add(new Transaction(accountNo,"Withdraw in Account",amount));
						break;
					}
					case 3:
					{
						arrAcc.get(acc).transferMoney();
						break;
					}
						
					case 4:
					{
						arrAcc.get(acc).calInterest();
						break;
					}
					case 5:
					{
						arrAcc.get(acc).checkBalance();
						break;
					}
					case 6:
					{
						arrAcc.get(acc).closeAccount();
						break;
					}
					case 0:
						break;
					default:
						System.out.println("Invalid Choice !!");
						break;
				}
			}
		}
		else
			System.out.println("Please Enter Correct Account Number");
	}

	//open account
	public static void openAccount()
	{
		System.out.println("\n|--------------------------------------------------|");
		System.out.println("|             BANK MANAGEMENT SYSTEM               |");
		System.out.println("|--------------------------------------------------|");
		System.out.println("|   Option   |             Operations              |");
		System.out.println("|--------------------------------------------------|");
		System.out.println("|      1     |          Saving Account             |");
		System.out.println("|      2     |          Salary Account             |");
		System.out.println("|      3     |          Current Account            |");
		System.out.println("|      4     |            Loan Account             |");
		System.out.println("|      0     |             <-Back                  |");
		System.out.println("|--------------------------------------------------|");
//		System.out.println("\nEnter Choice : ");
		choice = choiceInput();
	
		switch(choice)
		{
			case 1: //saving account
			{
				System.out.println("Enter Name : ");
				String name = stringInput();;
				System.out.println("Set Your Password : ");
				String password=stringInput();
				double balance;
				do {
					System.out.println("Min Balance Want 10000 \n Enter Amount You Want to Deposite in Your Account : ");
					balance = sc.nextDouble();
					if (balance >= 10000)
					{
						arrAcc.add(new SavingAccount(accountNo,name,balance,password));
						Transaction.trr.add(new Transaction(accountNo,"Saving Account Created",balance));
						System.out.println(Transaction.trr);
					} else
						System.out.println("Please Give us Min Balance at Least 10000");
				} while (balance <= 10000);
				System.out.println("\nSAVING ACCOUNT CREATED SUCCESSFULLY !!");
				System.out.println("-------YOUR ACCOUNT NUMBER IS : "+accountNo++ +" -------");
				break;
			}
			case 2: //salary account
			{
				System.out.println("Enter Name : ");
				String name = stringInput();
				double balance;
				String lastTransaction = null;
				System.out.println("Enter Balance : ");
				balance = sc.nextInt();
				System.out.println("Set Your Password : ");
				String password=stringInput();
				System.out.println("Enter Last Transaction Date like formate YYYY-MM-DD : ");
				lastTransaction=sc.next();
				arrAcc.add(new SalaryAccount(accountNo,name,balance,password,lastTransaction)); 
				Transaction.trr.add(new Transaction(accountNo,"Salary Account Created",balance));
				System.out.println(Transaction.trr);
				System.out.println("SALARY ACCOUNT CREATED SUCCESSFULLY !!");
				System.out.println("-------YOUR ACCOUNT NUMBER IS : "+accountNo++ +"-------");
				break;
			}
			case 3: // current account
			{
				System.out.println("Enter Name : ");
				String name = stringInput();
				System.out.println("Enter Balance if you Want otherwise Enter Zero : ");
				double balance = sc.nextDouble();
				System.out.println("Set Your Password : ");
				String password=stringInput();
				arrAcc.add(new CurrentAccount(accountNo,name,balance,password));
				Transaction.trr.add(new Transaction(accountNo,"Current Account Created",balance));
				System.out.println("CURRENT ACCOUNT CREATED SUCCESSFULLY !!");
				System.out.println("-------YOUR ACCOUNT NUMBER IS : "+accountNo++ +"-------");
				break;
			}
			case 4: //loan account
			{
				System.out.println("Enter Name : ");
				String name =stringInput();
				System.out.println("Enter Loan Amount : ");
				double balance=sc.nextDouble();
				System.out.println("Set Your Password : ");
				String password=stringInput();
				arrAcc.add(new LoanAccount(accountNo,name,balance,password)); 
				Transaction.trr.add(new Transaction(accountNo,"Loan Account Created",balance));
				System.out.println("\nLOAN ACCOUNT CREATED SUCCESSFULLY !!");
				System.out.println("-------YOUR ACCOUNT NUMBER IS : "+accountNo++ +" -------");
				break;
			}
			case 0:
				break;
			default:
				System.out.println("Invalid Choice !!");
				break;
			}
	}
	
////////////////////////////////////////ASMITA PAWAR ////////////////////////////////////////////
//find account num
	public static int findAccount()
	{
		int flag=0;
		System.out.println("Enter Account Number : ");
		int accNo = sc.nextInt();
		for (int i = 0; i <arrAcc.size(); i++) 
		{
			if (arrAcc.get(i).getAccountNum() == accNo) 
			{
				flag=1;
				return i;
			} 
		}
		if(flag==0)
		{
			System.out.println("Didn't Found Account !!\n");
			return -1;
		}
		return -1;
	}

/////////////////////////////// PURVA WARKE //////////////////////////////////////////////
	//password check - admin
	public static int adminCheck()
	{
		int count=0;
		do
		{
			String systemname="purva";
			String password = "purva2510";
			System.out.print("Enter User Name : ");
			String username=sc.next();
			System.out.print("Enter Password : ");
			String pass=sc.next();
			try {
				if(systemname.contentEquals(username) && password.contentEquals(pass))
				{
					return 0;
				}
				else
				{
					count++;
					throw new InputMismatchException("Please Enter Correct User Name And Password \n");
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println(e.getMessage());
			}	
		}while(count<3);
		return 1;
	}
	//choice (Integer) input with exception
	public static int choiceInput()
	{
		while(true)
		{
			try
			{
				System.out.println("\nEnter Choice : ");
				choice=sc.nextInt();
				return choice; 
			}
			catch(InputMismatchException e)
			{
				System.out.println("Input Mismatch !! \nPlease Enter Only From Above List Numeric Option !!");
				sc.next();
			}
		}
	}
///// string exception
	public static String stringInput()
	{
		
		while(true)
		{
			try {
					sc.nextLine();
					String str=sc.nextLine();
					if(!str.matches("[a-zA-Z0-9 ]+"))
					{
						throw new InputMismatchException("Input Mismatch !! \nEnter Only Alphabetic and Numeric  !!");
					}
					else
						return str;
			}
			catch(InputMismatchException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
}
