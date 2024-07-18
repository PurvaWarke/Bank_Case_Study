package com.account;

import java.util.ArrayList;
import java.util.List;

public class Transaction {

	int transactionNo;
	int accountNum;
	String transactionType;
	double amount;
	static int num=1;
//	public static Transaction[] trr = new Transaction[50];
	public static List<Transaction> trr=new ArrayList<Transaction>();
	
	public Transaction() {
		
	}

	public Transaction( int accountNum, String transactionType, double amount) {
		super();
		transactionNo = num++;
		this.accountNum = accountNum;
		this.transactionType = transactionType;
		this.amount = amount;
	}

	public int getTransactionNo() {
		return transactionNo;
	}

	public int getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public static void transactionDisplay()
	{
		System.out.println("|-------------------------------------------------------------------------|");
		System.out.println("|                          BANK MANAGEMENT SYSTEM                         |");
		System.out.println("|-------------------------------------------------------------------------|");
		System.out.printf("| %-"+10+"s | %-"+17+"s | %-"+25+"s | %-"+10+"s |\n", "TransNo", "Account Number", "Operations", "Amount");
		System.out.println("|-------------------------------------------------------------------------|");
		for (int i = 0; i < Transaction.trr.size(); i++) 
		{
			System.out.printf("| %-"+10+"s | %-"+17+"s | %-"+25+"s | %-"+10+"s |\n", trr.get(i).getTransactionNo(), trr.get(i).getAccountNum(), trr.get(i).getTransactionType(), trr.get(i).getAmount());
		}
		System.out.println("|-------------------------------------------------------------------------|");
	}

	@Override
	public String toString() {
		return "Transaction [transactionNo=" + transactionNo + ", accountNum=" + accountNum + ", transactionType="
				+ transactionType + ", amount=" + amount + "]";
	}
	
}
