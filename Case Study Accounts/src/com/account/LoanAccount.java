package com.account;

import java.util.Scanner;
///////////////////////////////////// PURVA WARKE /////////////////////////////////////////////
public class LoanAccount extends Account{
	Scanner sc=new Scanner(System.in);
	double loanGiven,loanPaid;
	static double interestRate=0.8; 
	
	public LoanAccount(int accountNum,String holderName, double balance,int password) 
	{
		super(accountNum,holderName,balance,password);
		balance+=(balance/100)*interestRate;
		this.loanGiven = balance;
		setBalance(0-getLoanGiven());
		this.loanPaid = 0;
	}
	
	public double getLoanGiven() {
		return loanGiven;
	}

	public void setLoanGiven(double loanGiven) {
		this.loanGiven = loanGiven;
	}

	public double getLoanPaid() 
	{
		return loanPaid;
	}

	public void setLoanPaid(double loanPaid) {
		this.loanPaid = loanPaid;
	}
	
	@Override
	public double deposite() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter Amount to Paid Loan : ");
		double amount=sc.nextDouble();

		if(this.getBalance()<0)
		{
			if(this.getBalance()+amount<0)
			{
				this.setBalance(this.getBalance()+amount);
				System.out.println("\nDeposited Succesfully !! \nYour Balance is : "+this.getBalance()+"\n");
				return this.getBalance();
			}
			else
			{
				System.out.println("Your Amount is Greater Than Your Remain Loan !!");
				return 0;
			}		
		}
		else if(this.getBalance()==0)
		{
			System.out.println("Your Complete Loan is Paid !!");
			
		}
		return 0;
	}

	@Override
	public double withdraw() {
		// TODO Auto-generated method stub
		if(this.getBalance()==0)
		{
			System.out.println("Amount to Withdraw: ");
			double amt=sc.nextDouble();
			this.loanGiven=this.getBalance();
			this.setBalance(0-amt);
			System.out.println("Withdraw Succesfully !! \nYour Balance is : "+this.getBalance());
			return this.getBalance();
		}
		else
		{
			System.out.println("Previous Loan Pending !!");
			return 0;
		}
	}

	@Override
	public void checkBalance() {
		// TODO Auto-generated method stub
		System.out.println("Your Balance is : "+this.getBalance());
	}

	@Override
	public void calInterest() {
		// TODO Auto-generated method stub
		double interestAmt=(this.getBalance()/100)*interestRate;
		System.out.println("Interest Rate is "+interestRate);
		System.out.println("Interest Amount is "+ interestAmt+" for Loan Account ");
	}

	@Override
	public void closeAccount() {
		// TODO Auto-generated method stub
		
	}
}
