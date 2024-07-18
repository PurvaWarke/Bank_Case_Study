package com.account;

import java.util.Scanner;
///////////////////////////////////// PURVA WARKE /////////////////////////////////////////////
public class CurrentAccount extends Account implements Operable{

	Scanner sc=new Scanner(System.in);
	double overDraftLimit;
	double overDraftedAmt;
	
	public CurrentAccount(int accountNum, String holderName, double balance,String password,String accType) {
		super(accountNum,holderName,balance,password,accType);
		this.overDraftLimit = 40000;
		this.overDraftedAmt = 0;
	}
	
	public double getOverDraftLimit() {
		return overDraftLimit;
	}

	public void setOverDraftLimit(double overDraftLimit) {
		this.overDraftLimit = overDraftLimit;
	}

	public double getOverDraftedAmt() {
		return overDraftedAmt;
	}

	public void setOverDraftedAmt(double overDraftedAmt) {
		this.overDraftedAmt = overDraftedAmt;
	}

	@Override
	public double deposite() {
		// TODO Auto-generated method stub
		System.out.println("Enter Amount to Deposite in Your Account : ");
		double amount=sc.nextDouble();
		if(overDraftedAmt==0)
		{
			setBalance(getBalance()+amount);
			System.out.println("Deposited Succesfully !! \n Your Balance is : "+this.getBalance());
			return this.getBalance();
		}
		else
		{
			if(amount >= overDraftedAmt)
			{
				setOverDraftLimit(this.getOverDraftedAmt()+this.getOverDraftLimit());
				setBalance(amount-this.overDraftedAmt);
				amount=amount-overDraftLimit;
				setOverDraftedAmt(0);
				System.out.println("Deposited Succesfully !! \n Your Balance is : "+this.getBalance());
				return this.getBalance();
			}
			else
			{
				setOverDraftedAmt(this.getOverDraftedAmt()-amount);
				setOverDraftLimit(this.getOverDraftLimit()-amount);
			}
		}
		return 0;
	}

	@Override
	public double withdraw() {
		// TODO Auto-generated method stub
		System.out.println("Enter Amount to Withdraw in Your Account : ");
		double amount=sc.nextDouble();
		if(getBalance()>=amount)
		{
			setBalance(getBalance()-amount);
			System.out.println("Withdraw Succesfully !! \n Your Balance is : "+this.getBalance());
			System.out.println("Your Over Draft Limit is : "+this.getOverDraftLimit());
			return this.getBalance();
		}
		else if(this.getOverDraftLimit() + this.getBalance() >= amount)
		{
			amount=amount-this.getBalance();
			setOverDraftedAmt(getOverDraftedAmt()+amount);
			setBalance(0);
			setOverDraftLimit(getOverDraftLimit()-amount);
			System.out.println("Withdraw Succesfully !! \n Your Balance is : "+this.getBalance());
			System.out.println("Your Over Draft Limit is : "+this.getOverDraftLimit());
			return this.getBalance();
		}
		else
		{
			System.out.println("You Can Withdraw Only "+(this.getOverDraftLimit()+this.getBalance()));
			return 0;
		}
	}

	@Override
	public void checkBalance() {
		// TODO Auto-generated method stub
		System.out.println("Your Balance is : "+this.getBalance());
		System.out.println("Your Over Drafted Amount is : "+this.getOverDraftedAmt());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public void calInterest() {
		// TODO Auto-generated method stub
		System.out.println("Your Account is Current Account that's why Your Account Have No interest !!");
	}
	@Override
	public void closeAccount() {
		if(this.getBalance()==0)
		{
			System.out.println("Please Confirm Your Account Number !!");
			int i=MainBank.findAccount();
			if(this.getBalance() == 0 )
			{
//				while(i < (MainBank.ctr)) 
//				     { 
//					      MainBank.arrAcc[i]=MainBank.arrAcc[i+1]; 
					      MainBank.arrAcc.remove(i);
//					      i++; 
//				     } 
//				     MainBank.ctr--; 
				     Transaction.trr.add(new Transaction(this.getAccountNum(),"Account Deleted",0)); 
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
//					while(i < (MainBank.ctr)) 
//					     { 
//						      MainBank.arrAcc[i]=MainBank.arrAcc[i+1]; 
								MainBank.arrAcc.remove(i);
//						      i++; 
//					     } 
//					     MainBank.ctr--;
					     if(overDraftedAmt==0)
					     {
					    	 Transaction.trr.add(new Transaction(this.getAccountNum(),"Account Deleted",0)); 
								System.out.println("Account Close Successfully !!");
					     }
					     else
					    	 System.out.println("Please Clear OverDraft !!");
				}
			}
			
			else if(choice ==2)
			{
				System.out.println("Account Close Cancle !!");
				return;
			}
		}
	}
}
