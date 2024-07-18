package com.account;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
///////////////////////////////////// ASMITA PAWAR //////////////////////////////////////////
public class SalaryAccount extends Account{

	Scanner sc=new Scanner(System.in);
	LocalDate lastTransaction;
	int timeLimit;
	static double interestRate=1; 
	
	public SalaryAccount(int accountNum,String holderName, double balance,int password,String lastTransaction) {
		super(accountNum,holderName,balance,password);
		this.timeLimit=2;
		this.lastTransaction = LocalDate.parse(lastTransaction);
	}
	
	public LocalDate getLastTransaction() {
		return lastTransaction;
	}


	public void setLastTransaction(LocalDate lastTransaction) {
		this.lastTransaction = lastTransaction;
	}


	public static double getInterestRate() {
		return interestRate;
	}


	public static void setInterestRate(double interestRate) {
		SalaryAccount.interestRate = interestRate;
	}


	@Override
	public double deposite() {
		// TODO Auto-generated method stub
		System.out.println("Enter Amount to Deposite in Your Account : ");
		double amount=sc.nextDouble();
		this.setBalance(amount+this.getBalance());
		setLastTransaction(LocalDate.now());
		System.out.println("\nDeposited Succesfully !! \nYour Balance is : "+this.getBalance()+"\n");
		return this.getBalance();
	}

	@Override
	public double withdraw() {
		// TODO Auto-generated method stub
		
		if((ChronoUnit.MONTHS.between(lastTransaction,LocalDate.now())) >= timeLimit)
		{
			System.out.println("Your Account is FREEZE !! You Cannot Withdraw !!\n");
			return 0;
		}
		else
		{
			System.out.println("Enter Amount to Withdraw in Your Account : ");
			double amount=sc.nextDouble();
			setBalance(getBalance()-amount);
			setLastTransaction(LocalDate.now());
			System.out.println("Withdraw Succesfully !! \nYour Balance is : "+this.getBalance());
			return this.getBalance();
		}		
	}

	@Override
	public void checkBalance() {
		// TODO Auto-generated method stub
		System.out.println("Your Balance is : "+this.getBalance());
	}

	@Override
	public void calInterest() 
	{
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
