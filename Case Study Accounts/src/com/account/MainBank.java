package com.account;

import java.util.Scanner;

public class MainBank 
{
	// Group Name : Kolar
	//Group Member : 1. Purva Warke  2.Asmita Pawar  3.Renuka Kalyankar

	public static Scanner sc=new Scanner(System.in);
	static int  accountNo = 101101101;
	public static int ctr = 0,tctr=0;
	static Account[] arrAcc = new Account[50];
	
	public static void main(String[] args)
	{
		bankOperation();
	}
	
	public static void bankOperation()
	{
////////////////////////////////////////////RENUKA KALYANKAR  ////////////////////////////////////////
		int choice;
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
			System.out.println("\nEnter Choice : ");
			choice=sc.nextInt();
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
	
		System.out.println("\nEnter Choice : ");
		int choice1 = sc.nextInt();
		switch (choice1) 
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
				arrAcc[findAccount()].display();
				break;
			}
			case 0:
				break;
			default:
				System.out.println("Invalid Choice !!");
				break;
			}
	}	

//////////////////////////////////////////// PURVA WARKRE ////////////////////////////////////////
	//user accounts
	public static void user()
	{
		int acc=findAccount();
		if(acc!=-1)
		{
			if(arrAcc[acc].userCheck()==0)
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
				System.out.println("\nEnter Choice : ");
				int choice2 = sc.nextInt();
			
			
				switch (choice2) 
				{
					case 1:
					{
						double amount = arrAcc[acc].deposite();
						Transaction.trr[tctr++]=new Transaction(accountNo,"Deposited in Account",amount);
						break;
					}
					case 2:
					{
						double amount = arrAcc[acc].withdraw();
						Transaction.trr[tctr++]=new Transaction(accountNo,"Withdraw in Account",amount);
						break;
					}
					case 3:
					{
//						transferMoney();
						break;
					}
						
					case 4:
					{
						arrAcc[acc].calInterest();
						break;
					}
					case 5:
					{
						arrAcc[acc].checkBalance();
						break;
					}
					case 6:
					{
						arrAcc[acc].closeAccount();
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
		System.out.println("\nEnter Choice : ");
		int choice2 = sc.nextInt();
		
//////////////////////////////////////////// RENUKA KALYANKAR  ////////////////////////////////////////
		switch(choice2)
		{
			case 1: //saving account
			{
				System.out.println("Enter Name : ");
				String name = sc.next();
				System.out.println("Set Your Password : ");
				int password=sc.nextInt();
				double balance;
				do {
					System.out.println("Min Balance Want 10000 \n Enter Amount You Want to Deposite in Your Account : ");
					balance = sc.nextDouble();
					if (balance >= 10000)
					{
						arrAcc[ctr++]=new SavingAccount(accountNo,name,balance,password);
						Transaction.trr[tctr++]=new Transaction(accountNo,"Saving Account Created",balance);
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
				String name = sc.next();
				double balance;
				String lastTransaction = null;
				System.out.println("Enter Balance : ");
				balance = sc.nextInt();
				System.out.println("Set Your Password : ");
				int password=sc.nextInt();
				System.out.println("Enter Last Transaction Date like formate YYYY-MM-DD : ");
				lastTransaction=sc.next();
				arrAcc[ctr++]=new SalaryAccount(accountNo,name,balance,password,lastTransaction); 
				Transaction.trr[tctr++]=new Transaction(accountNo,"Salary Account Created",balance);
				System.out.println("SALARY ACCOUNT CREATED SUCCESSFULLY !!");
				System.out.println("-------YOUR ACCOUNT NUMBER IS : "+accountNo++ +"-------");
				break;
			}
			case 3: // current account
			{
				System.out.println("Enter Name : ");
				String name = sc.next();
				System.out.println("Enter Balance if you Want otherwise Enter Zero : ");
				double balance = sc.nextDouble();
				System.out.println("Set Your Password : ");
				int password=sc.nextInt();
				arrAcc[ctr++]=new CurrentAccount(accountNo,name,balance,password);
				Transaction.trr[tctr++]=new Transaction(accountNo,"Current Account Created",balance);
				System.out.println("CURRENT ACCOUNT CREATED SUCCESSFULLY !!");
				System.out.println("-------YOUR ACCOUNT NUMBER IS : "+accountNo++ +"-------");
				break;
			}
			case 4: //loan account
			{
				System.out.println("Enter Name : ");
				String name = sc.next();
				System.out.println("Enter Loan Amount : ");
				double balance=sc.nextDouble();
				System.out.println("Set Your Password : ");
				int password=sc.nextInt();
				arrAcc[ctr++]=new LoanAccount(accountNo,name,balance,password); 
				Transaction.trr[tctr++]=new Transaction(accountNo,"Loan Account Created",balance);
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
	for (int i = 0; i <ctr; i++) 
	{
		if (arrAcc[i].getAccountNum() == accNo) 
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


//password check - admin
public static int adminCheck()
{
	String systemname="kolar";
	String password = "kolar123";
	System.out.print("Enter User Name : ");
	String username=sc.next();
	System.out.print("Enter Password : ");
	String pass=sc.next();
	if(systemname.contentEquals(username) && password.contentEquals(pass))
	{
		return 0;
	}
	else
	{
		System.out.println("Please Enter Correct User Name And Password ");
	return 1;
	}
}


//public static void closeAccount() 
//{ 
//	 int acc=findAccount() ;
//	 
//	 for(int i=0;i<ctr;i++) 
//	 { 
//		  if(acc!=0) 
//		  { 
//			   if(arrAcc[i].balance == 0) 
//			   { 
//			    while(i<ctr) 
//			    { 
//				     arrAcc[i]=arrAcc[i+1]; 
//				     i++; 
//			    } 
//			    ctr--; 
//			    System.out.println("Account removed Successfully..!"); 
//			    Transaction.trr[tctr++]=new Transaction(accountNo,"Account Deleted",0); 
//		   } 
//		   else if (arrAcc[i].balance <0) 
//		   { 
//			    System.out.println("You can not delete your account.Your acccount is loan account "); 
//			    System.out.println("First your have to pay your loan and then you close your account"); 
//			    System.out.println("The amount is "+(-arrAcc[i].balance )); 
//			    System.out.println("\n1.deposite\n2.cancel Deletion\nEnter your choice:"); 
//			    int choice= sc.nextInt(); 
//			    //if(choice == 1) 
//			    if(choice == 1) 
//			    { 
//				     System.out.println("Enter "+(-arrAcc[i].balance)+"amount"); 
//				     arrAcc[i].deposite(); 
//				     while(i<ctr) 
//				     { 
//					      arrAcc[i]=arrAcc[i+1]; 
//					      i++; 
//				     } 
//				     ctr--; 
//				     System.out.println("Account removed Successfully..!"); 
//				     Transaction.trr[tctr++]=new Transaction(acc,"Account Deleted",0); 
//				} 
//			    else 
//			    { 
//			    	System.out.println("your account deletion is cancelled!!!!");  
//			    } 
//		   } 
//		   else 
//		   { 
//			    System.out.println("You can not delete your account, You have Rs. "+arrAcc[i].balance+"\n1.Withdraw\n2.cancel Deletion\nEnter your choice:"); 
//			    int choice= sc.nextInt(); 
//			    if(choice == 1) 
//			    { 
//				     System.out.println("Enter "+arrAcc[i].balance+"amount"); 
//				     arrAcc[i].withdraw(); 
//				     while(i<ctr) 
//				     { 
//				    	 arrAcc[i]=arrAcc[i+1]; 
//				    	 i++; 
//				     } 
//				     ctr--; 
//				     System.out.println("Account removed Successfully..!"); 
//				     Transaction.trr[tctr++]=new Transaction(accountNo,"Account Deleted",0); 
//				} 
//			    else 
//			    { 
//			    	System.out.println("your account deletion is cancelled!!!!");   
//			    } 
//			} 
//		  } 
//	 } 
//}
	
/////////////////////////////// PURVA WARKE //////////////////////////////////////////////
//	public static void transferMoney() 
//	{
//		int ourAccount = findAccount();
//		System.out.println("Another");
//		int ontherAccount = findAccount();
//		System.out.println("Enter Amount To Transfer : ");
//		double amount=sc.nextDouble();
//		if(ourAccount !=0)
//		{
//			if(ontherAccount!=0)
//			{
//				if(arrAcc[ourAccount].getBalance() >= amount )
//				{
//					double transfered = arrAcc[ourAccount].getBalance()-amount;
//					arrAcc[ontherAccount].setBalance(arrAcc[ontherAccount].getBalance()+amount);
//					arrAcc[ourAccount].setBalance(transfered);
//					arrAcc[ourAccount].checkBalance();
//				}
//				else
//				{
//					System.out.println("Insufficent Balance !!");
//				}
//			}
//			else
//			{
//				System.out.println("Please Enter Correct Account Number of Reciever's !! ");
//			}
//		}
//		else
//			System.out.println("Your Account Number Didn't Match !!");	
//	}
}
