package com.account;

import java.util.Scanner;
//////////////////////////////////////// RENUKA KALYANKAR  ////////////////////////////////////////////
public class SavingAccount extends Account{

	int minBalance;
	static double interestRate=0.5; 
	static Scanner sc=new Scanner(System.in);
	
	public SavingAccount(int accountNum, String holderName, double balance,String password) {
		super(accountNum,holderName,balance,password);
		this.minBalance = 10000;
	}

	public static double getInterestRate() {
		return interestRate;
	}

	public static void setInterestRate(double interestRate) {
		SavingAccount.interestRate = interestRate;
	}

	@Override
	public double deposite() {
		// TODO Auto-generated method stub
		System.out.println("Enter Amount to Deposite  : ");
		double amount=sc.nextDouble();
		this.setBalance(amount+this.getBalance());
		System.out.println("\nDeposited Succesfully !! \n Your Balance is : "+this.getBalance());
		return this.getBalance();
	}

	@Override
	public double withdraw() {
		// TODO Auto-generated method stub
		System.out.println("Enter Amount to Withdraw  : ");
		double amount=sc.nextDouble();
		if(this.getBalance() - amount >= minBalance)	//amount greater than minbalance
		{
			setBalance(getBalance()-amount);
			System.out.println("\nWithdraw Succesfully !! \n Your Balance is : "+this.getBalance());
			return this.getBalance();
		}
		else
		{
			System.out.println("\nYou Can Withdraw only "+(this.getBalance()-minBalance) +"Rupee !! \n Need Minimum Balance for Accoun is 10000 !!");
			return this.getBalance();
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
		System.out.println("Your Balance : ");
		System.out.println("Your Annual Interest Rate is : "+getInterestRate());
		System.out.println("Enter Time Period in Year : ");
		double year=sc.nextDouble();
		double interestAmount = (getBalance() * getInterestRate() * year)/100;
		double interestBalance = getBalance() + interestAmount;
		System.out.println("Your Interest Amount is : "+interestAmount);
		System.out.println("Your Balance Will Be : "+interestBalance);
	}
		@Override
		public void closeAccount() {
			// TODO Auto-generated method stub
			super.closeAccount();
		}
}
