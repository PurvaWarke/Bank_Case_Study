package com.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.db.connection.DatabaseConnectivity;

 public  class  Account
 {
	
	static Scanner sc=new Scanner(System.in);
	int accountNum;
	String holderName;
	double balance;
	String password;
	String accType;
	static DatabaseConnectivity db=new DatabaseConnectivity();
	public static	Connection con=db.getConnection();
	public Account() {
		// TODO Auto-generated constructor stub
	}
	public Account(int accountNum,String holderName, double balance,String password,String accType) {
		super();
		this.accountNum = accountNum;
		this.holderName = holderName;
		this.balance = balance;
		this.password=password;
		this.accType=accType;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public int getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public static void design()
	{
		System.out.println("|------------------------------------------------------|");
		System.out.println("|              BANK MANAGEMENT SYSTEM                  |");
		System.out.println("|------------------------------------------------------|");
		System.out.printf("| %-"+16+"s | %-"+20+"s | %-"+10+"s |\n", "Account Number", "Holder Name", "Balance");
		System.out.println("|------------------------------------------------------|");
	}
	public void display()
	{
		design();
		System.out.printf("| %-"+16+"s | %-"+20+"s | %-"+10+"s |\n" ,this.getAccountNum(),this.getHolderName(),this.getBalance());
		System.out.println("|------------------------------------------------------|");
	}
	
	public static void displayAll()
	{
		design();
		for(int i=0;i<MainBank.arrAcc.size();i++)
		{
			System.out.printf("| %-"+16+"s | %-"+20+"s | %-"+10+"s |\n" ,MainBank.arrAcc.get(i).getAccountNum(),MainBank.arrAcc.get(i).getHolderName(),MainBank.arrAcc.get(i).getBalance());
		}
		System.out.println("|------------------------------------------------------|");
	}
	public void closeAccount() {
		// TODO Auto-generated method stub
		if(this.getBalance()==0)
		{
			System.out.println("Please Confirm Your Account Number !!");
			int i=MainBank.findAccount();
			if(this.getBalance() == 0 )
			{
				deleteFromDatabase(i);
				MainBank.arrAcc.remove(i); 
			    Transaction.trr.add(new Transaction(this.getAccountNum(),"Account Deleted",0));
			    MainBank.addTransactionInDatabase();
				System.out.println("Account Close Successfully !!");
			}
		}
		else
		{
			
			System.out.println("Your Account Balance is : "+this.getBalance());
			System.out.println("1.Withdraw \n2.Cancle Close Account");
			int choice=sc.nextInt();
			if(choice==1)
			{
				double amount;
				do{
					System.out.println("\nYou Need to Withdraw "+this.getBalance()+" Amount");
					System.out.println("Enter Amount to Withdraw  : ");
					amount=sc.nextDouble();
					if(this.getBalance() - amount == 0)	
					{
						setBalance(getBalance()-amount);
						System.out.println("\nWithdraw Succesfully !! \n Your Balance is : "+this.getBalance());
					}
					else
					{
						System.out.println("Please Check Yout Withdraw Amount ! ");
					}	
				}while(this.getBalance() > 0);
				System.out.println("Please Confirm Your Account Number !!");
				int i=MainBank.findAccount();
				if(this.getBalance() == 0 )
				{
					deleteFromDatabase(i);
					MainBank.arrAcc.remove(i);
				    Transaction.trr.add(new Transaction(this.getAccountNum(),"Account Deleted",0)); 
					System.out.println("Account Close Successfully !!");
				}
			}
			
			else if(choice ==2)
			{
				System.out.println("Account Close Cancle !!");
				return;
			}
		}
	}
	private void deleteFromDatabase(int accNo) {
		// TODO Auto-generated method stub
		try 
		{
			int accno=MainBank.arrAcc.get(accNo).getAccountNum();
			if(MainBank.arrAcc.get(accNo).getAccType().equalsIgnoreCase("saving"))
			{
				System.err.println("saving"+accNo);
				String query="delete from transactions where accNo=?";
				PreparedStatement pstmt=con.prepareStatement(query);
				pstmt.setInt(1, MainBank.arrAcc.get(accNo).getAccountNum());
				pstmt.executeUpdate();
				query="delete from accounts where aNo=?";
				 pstmt=con.prepareStatement(query);
				pstmt.setInt(1, MainBank.arrAcc.get(accNo).getAccountNum());
				int res=pstmt.executeUpdate();
				if(res!=0)
					System.err.println("Deleted Successfully !!");
				else
					System.out.println("Not Deleted Account !! Try Again Some Time !!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int userCheck() 
	{
		String systemPassword=this.getPassword();
		System.out.print("Enter Password : ");
		String userPassword=sc.next();
		if(systemPassword.equals(userPassword))
		{
			return 0;
		}
		else
		{
			System.out.println("Please Enter Correct Password ");
			return 1;
		}
	}
	
	//transfermoney
	public void transferMoney() 
	{
		int ourAccount = this.getAccountNum();
		System.out.println("Another");
		int ontherAccount = MainBank.findAccount();
		int choice = 0;
		do {
			
			System.out.println("Enter Amount To Transfer : ");
			double amount=sc.nextDouble();
			System.out.println("Want to Continue Press 0 otherwise 1");
			choice=sc.nextInt();
			if(ourAccount !=-1)
			{
				if(ontherAccount!=-1)
				{
					if(MainBank.arrAcc.get(ourAccount).getBalance() >= amount )
					{
						double transfered = MainBank.arrAcc.get(ourAccount).getBalance()-amount;
						MainBank.arrAcc.get(ontherAccount).setBalance(MainBank.arrAcc.get(ontherAccount).getBalance()+amount);
						MainBank.arrAcc.get(ourAccount).setBalance(transfered);
//						MainBank.arrAcc.get(ourAccount).checkBalance();
					}
					else
					{
						System.out.println("Insufficent Balance !!");
					}
				}
				else
				{
					System.out.println("Please Enter Correct Account Number of Reciever's !! ");
				}
			}
			else
				System.out.println("Your Account Number Didn't Match !!");	
		}while(choice==0);	
	}
}
