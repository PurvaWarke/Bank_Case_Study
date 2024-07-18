package com.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.db.connection.DatabaseConnectivity;

public class MainBank 
{
	public static Scanner sc=new Scanner(System.in);
	static int  accountNo = 101101101;
	public static int choice=0;
//	static Account[] arrAcc = new Account[50];
	 static List<Account> arrAcc=new ArrayList<Account>();
	 static DatabaseConnectivity db=new DatabaseConnectivity();
	public static	Connection con=db.getConnection();
	public static void main(String[] args)
	{
		accountsDataFromDatabase();
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
				{
					addAccountInDatabse();
					System.out.println("THANK YOU !!");
				}
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
						if(arrAcc.get(acc) instanceof SavingAccount)
						{
							SavingAccount sacc=(SavingAccount)arrAcc.get(acc);
							double amount = sacc.deposite();
							Transaction.trr.add(new Transaction(accountNo,"Deposited in Account",amount));
							addTransactionInDatabase();
						}
						else if(arrAcc.get(acc) instanceof SalaryAccount)
						{
							SalaryAccount sacc=(SalaryAccount)arrAcc.get(acc);
							double amount = sacc.deposite();
							Transaction.trr.add(new Transaction(accountNo,"Deposited in Account",amount));
							addTransactionInDatabase();
						}
						else if(arrAcc.get(acc) instanceof CurrentAccount)
						{
							CurrentAccount cacc=(CurrentAccount)arrAcc.get(acc);
							double amount = cacc.deposite();
							Transaction.trr.add(new Transaction(accountNo,"Deposited in Account",amount));
							addTransactionInDatabase();
						}
						else if(arrAcc.get(acc) instanceof LoanAccount)
						{
							LoanAccount lacc=(LoanAccount)arrAcc.get(acc);
							double amount = lacc.deposite();
							Transaction.trr.add(new Transaction(accountNo,"Deposited in Account",amount));
							addTransactionInDatabase();
						}
						else
						{
							System.out.println("This Account is Not Available !!");
						}
						break;
					}
					case 2:
					{
						if(arrAcc.get(acc) instanceof SavingAccount)
						{
							SavingAccount sacc=(SavingAccount)arrAcc.get(acc);
							double amount = sacc.withdraw();
							Transaction.trr.add(new Transaction(accountNo,"WithDraw from Account",amount));
							addTransactionInDatabase();
						}
						else if(arrAcc.get(acc) instanceof SalaryAccount)
						{
							SalaryAccount sacc=(SalaryAccount)arrAcc.get(acc);
							double amount = sacc.withdraw();
							Transaction.trr.add(new Transaction(accountNo,"WithDraw from Account",amount));
							addTransactionInDatabase();
						}
						else if(arrAcc.get(acc) instanceof CurrentAccount)
						{
							CurrentAccount cacc=(CurrentAccount)arrAcc.get(acc);
							double amount = cacc.withdraw();
							Transaction.trr.add(new Transaction(accountNo,"WithDraw from Account",amount));
							addTransactionInDatabase();
						}
						else if(arrAcc.get(acc) instanceof LoanAccount)
						{
							LoanAccount lacc=(LoanAccount)arrAcc.get(acc);
							double amount = lacc.withdraw();
							Transaction.trr.add(new Transaction(accountNo,"WithDraw from Account",amount));
							addTransactionInDatabase();
						}
						else
						{
							System.out.println("This Account is Not Available !!");
						}
						break;
						
					}
					case 3:
					{
						arrAcc.get(acc).transferMoney();
						break;
					}
						
					case 4:
					{
						if(arrAcc.get(acc) instanceof SavingAccount)
						{
							SavingAccount sacc=(SavingAccount)arrAcc.get(acc);
							sacc.calInterest();
						}
						else if(arrAcc.get(acc) instanceof SalaryAccount)
						{
							SalaryAccount sacc=(SalaryAccount)arrAcc.get(acc);
							sacc.calInterest();
						}
						else if(arrAcc.get(acc) instanceof CurrentAccount)
						{
							CurrentAccount cacc=(CurrentAccount)arrAcc.get(acc);
							cacc.calInterest();
						}
						else if(arrAcc.get(acc) instanceof LoanAccount)
						{
							LoanAccount lacc=(LoanAccount)arrAcc.get(acc);
							lacc.calInterest();
						}
						else
						{
							System.out.println("This Account is Not Available !!");
						}
						break;
					}
					case 5:
					{
						if(arrAcc.get(acc) instanceof SavingAccount)
						{
							SavingAccount sacc=(SavingAccount)arrAcc.get(acc);
							sacc.checkBalance();
						}
						else if(arrAcc.get(acc) instanceof SalaryAccount)
						{
							SalaryAccount sacc=(SalaryAccount)arrAcc.get(acc);
							sacc.checkBalance();
						}
						else if(arrAcc.get(acc) instanceof CurrentAccount)
						{
							CurrentAccount cacc=(CurrentAccount)arrAcc.get(acc);
							cacc.checkBalance();
						}
						else if(arrAcc.get(acc) instanceof LoanAccount)
						{
							LoanAccount lacc=(LoanAccount)arrAcc.get(acc);
							lacc.checkBalance();
						}
						else
						{
							System.out.println("This Account is Not Available !!");
						}
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
				sc.nextLine();
				System.out.println("Enter Name : ");
				String name = stringInput();
				
				System.out.println("Set Your Password : ");
				String password=stringInput();
		
				double balance;
				do {
					System.out.println("Min Balance Want 10000 \nEnter Amount You Want to Deposite in Your Account : ");
					balance = sc.nextDouble();
					if (balance >= 10000)
					{
						accountNo=arrAcc.get(arrAcc.size()-1).getAccountNum(); // previous account numchy next num auto 
						arrAcc.add(new SavingAccount(++accountNo,name,balance,password,"saving"));
//						addAccountInDatabse();
						Transaction.trr.add(new Transaction(accountNo,"Saving Account Created",balance));
						System.out.println(Transaction.trr);
						addTransactionInDatabase();
					} else
						System.out.println("Please Give us Min Balance at Least 10000");
				} while (balance <= 10000);
				System.out.println("\nSAVING ACCOUNT CREATED SUCCESSFULLY !!");
				System.out.println("-------YOUR ACCOUNT NUMBER IS : "+accountNo++ +" -------");
				break;
			}
			case 2: //salary account
			{
				sc.nextLine();
				System.out.println("Enter Name : ");
				String name = stringInput();
				System.out.println("Set Your Password : ");
				String password=stringInput();
				
				double balance;
				String lastTransaction = null;
				System.out.println("Enter Balance : ");
				balance = sc.nextInt();
				
				System.out.println("Enter Last Transaction Date like formate YYYY-MM-DD : ");
				lastTransaction=sc.next();
				accountNo=arrAcc.get(arrAcc.size()-1).getAccountNum();
				arrAcc.add(new SalaryAccount(++accountNo,name,balance,password,lastTransaction,"salary"));
//				addAccountInDatabse();
//				
//				String query="insert into salaryaccount values(?,?)";
//				PreparedStatement pstmt;
//				try 
//				{
//					pstmt = con.prepareStatement(query);
//					System.err.println(accountNo);
//					System.err.println(lastTransaction.toString());
//					pstmt.setInt(1,accountNo);
//					pstmt.setString(2, lastTransaction.toString());
//					pstmt.executeUpdate();
//					
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				Transaction.trr.add(new Transaction(accountNo,"Salary Account Created",balance));
				addTransactionInDatabase();
				System.out.println("SALARY ACCOUNT CREATED SUCCESSFULLY !!");
				System.out.println("-------YOUR ACCOUNT NUMBER IS : "+accountNo++ +"-------");
				break;
			}
			case 3: // current account
			{
				sc.nextLine();
				System.out.println("Enter Name : ");
				String name = stringInput();
				System.out.println("Set Your Password : ");
				String password=stringInput();
				
				System.out.println("Enter Balance if you Want otherwise Enter Zero : ");
				double balance = sc.nextDouble();
				accountNo=arrAcc.get(arrAcc.size()-1).getAccountNum();
				arrAcc.add(new CurrentAccount(++accountNo,name,balance,password,"current"));
//				addAccountInDatabse();
//				String query="insert into curentaccount values(?,?)";
//				PreparedStatement pstmt;
//				try 
//				{
//					pstmt = con.prepareStatement(query);
//					pstmt.setInt(1,accountNo);
//					pstmt.setDouble(2,0);
//					pstmt.executeUpdate();
//					
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				Transaction.trr.add(new Transaction(accountNo,"Current Account Created",balance));
				addTransactionInDatabase();
				System.out.println("CURRENT ACCOUNT CREATED SUCCESSFULLY !!");
				System.out.println("-------YOUR ACCOUNT NUMBER IS : "+accountNo++ +"-------");
				break;
			}
			case 4: //loan account
			{
				sc.nextLine();
				System.out.println("Enter Name : ");
				String name =stringInput();
				System.out.println("Set Your Password : ");
				String password=stringInput();
				
				System.out.println("Enter Loan Amount : ");
				double balance=sc.nextDouble();
				
				accountNo=arrAcc.get(arrAcc.size()-1).getAccountNum();
				arrAcc.add(new LoanAccount(++accountNo,name,balance,password,"loan"));
//				addAccountInDatabse();
//				String query="insert into loanaccount values(?,?,?)";
//				PreparedStatement pstmt;
//				try 
//				{
//					pstmt = con.prepareStatement(query);
//					pstmt.setInt(1,accountNo);
//					pstmt.setDouble(2, balance);
//					pstmt.setDouble(3, 0.0);
//					pstmt.executeUpdate();
//					
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				Transaction.trr.add(new Transaction(accountNo,"Loan Account Created",balance));
				addTransactionInDatabase();
				System.out.println("\nLOAN ACCOUNT CREATED SUCCESSFULLY !!");
				System.out.println("-------YOUR ACCOUNT NUMBER IS : "+accountNo++ +" -------");
				break;
			}
			case 0:
			{
				addAccountInDatabse();
				break;
			}
				
			default:
				System.out.println("Invalid Choice !!");
				break;
			}
	}
	

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
					
					String str=sc.nextLine();
					if(!str.matches("[a-zA-Z]+"))
					{
						throw new InputMismatchException("Input Mismatch !! \nEnter Only Alphabetic !!");
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
	
	//add data in transaction table in database
	public static void addTransactionInDatabase()
	{
//		try 
//		{
//			String table="insert into transactions values(?,?,?,?)";
//
//			PreparedStatement pstmt=con.prepareStatement(table);
//			pstmt.setInt(1, Transaction.trr.get(Transaction.trr.size()-1).getTransactionNo());
//			pstmt.setInt(2, Transaction.trr.get(Transaction.trr.size()-1).getAccountNum());
//			pstmt.setString(3, Transaction.trr.get(Transaction.trr.size()-1).getTransactionType());
//			pstmt.setDouble(4, Transaction.trr.get(Transaction.trr.size()-1).getAmount());
//
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	//add acount in database table account
//	
	public static void addAccountInDatabse() {
	    try {
	        // Prepare statements for different tables
	        PreparedStatement accountable = con.prepareStatement("insert into accounts values(?,?,?,?,?)");
	        PreparedStatement salarytable = con.prepareStatement("insert into salaryaccount values(?,?)");
	        PreparedStatement currenttable = con.prepareStatement("insert into currentaccount values(?,?)");
	        PreparedStatement loantable = con.prepareStatement("insert into loanaccount values(?,?,?)");
	        PreparedStatement checkAccountExists = con.prepareStatement("select count(*) from accounts where aNo = ?");

	        con.setAutoCommit(false); // Start transaction

	        for (Account account : arrAcc) {
	            // Check if account already exists
	            checkAccountExists.setInt(1, account.getAccountNum());
	            ResultSet rs = checkAccountExists.executeQuery();
	            rs.next();
	            int count = rs.getInt(1);
	            rs.close();

	            if (count > 0) {
	                // Account already exists, skip insertion
	                continue;
	            }

	            // Set parameters for the accounts table
	            accountable.setInt(1, account.getAccountNum());
	            accountable.setString(2, account.getHolderName());
	            accountable.setDouble(3, account.getBalance());
	            accountable.setString(4, account.getPassword());
	            accountable.setString(5, account.getAccType());
	            accountable.addBatch(); // Add to batch

	            if (account instanceof SalaryAccount) {
	                SalaryAccount salaryAccount = (SalaryAccount) account;
	                // Set parameters for the salaryaccount table
	                salarytable.setInt(1, salaryAccount.getAccountNum());
	                salarytable.setString(2, salaryAccount.getLastTransaction().toString());
	                salarytable.addBatch();
	            } else if (account instanceof CurrentAccount) {
	                CurrentAccount currentAccount = (CurrentAccount) account;
	                // Set parameters for the currentaccount table
	                currenttable.setInt(1, currentAccount.getAccountNum());
	                currenttable.setDouble(2, currentAccount.getOverDraftedAmt());
	                currenttable.addBatch();
	            } else if (account instanceof LoanAccount) {
	                LoanAccount loanAccount = (LoanAccount) account;
	                // Set parameters for the loanaccount table
	                loantable.setInt(1, loanAccount.getAccountNum());
	                loantable.setDouble(2, loanAccount.getLoanGiven());
	                loantable.setDouble(3, loanAccount.getLoanPaid());
	                loantable.addBatch();
	            }
	        }

	        // Execute all batches
	        accountable.executeBatch(); // Execute batch for accounts table
	        salarytable.executeBatch(); // Execute batch for salaryaccount table
	        currenttable.executeBatch(); // Execute batch for currentaccount table
	        loantable.executeBatch(); // Execute batch for loanaccount table

	        con.commit(); // Commit transaction

	    } catch (SQLException e) {
	        
	            e.printStackTrace();
	        
	    } finally {
	        try {
	            if (con != null) {
	                con.close(); 
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	// fetch data from accounts in database
	public static void accountsDataFromDatabase() {
		// TODO Auto-generated method stub
		String query="select * from accounts";
		try 
		{
			Statement stmt=con.createStatement();
			ResultSet rs= stmt.executeQuery(query);
			while(rs.next())
			{
				Account acc=new Account(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5));
				arrAcc.add(acc);
				accountNo++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
