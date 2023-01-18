package com.tester;

import java.util.List;
import java.util.Scanner;
import static com.util.DbUtil.openConnection;
import com.dal.AccountDallImpl;
import com.pojo.Account;

public class AccountTester {
	
	public static void main(String[] args) {
		
		try(Scanner sc =  new Scanner(System.in)) {
			openConnection();
			AccountDallImpl acc = new AccountDallImpl();
			boolean exit = false;
			
			while(!exit) {
				System.out.println("1. Get All Account 2.Add Account 3. Update Account 4. Delete Account 5. Transfer Fund 6. exit");
				System.out.println("Enter Choice : ");
				switch (sc.nextInt()) {
				case 1:
					List<Account> list = acc.getAllAccounts();
					list.forEach(q->System.out.println(q));
					break;
				case 2:
					System.out.println("Enter Bank Account details : Actno, Name, Email, city, Balance ");
					Account dto = new Account(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.nextDouble());
					int i = acc.addAccount(dto);
					if(i>0) {
						System.out.println("Account Added Successfully..!!!");
					}
					break;
				case 3:
					System.out.println("Edit Account : Actno, Name, Email, city, Balance");
					dto = new Account(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.nextDouble());
					 i = acc.updateAccount(dto);
					if(i>0) {
						System.out.println("Account Updated Successfully..!!!");
					}
					break;
				case 4:
					System.out.println("Enter Account No to delete : ");
					i = acc.deleteAccount(sc.nextInt());
					if(i>0)
					{
						System.out.println("Deleted");
					}
					else
					{
						System.out.println("Not Found...");
					}
					break;
				case 5:
					System.out.println("Enter Sender Account Reciever Account and Amount to Transfer : ");
					String status = acc.transfer_fund(sc.nextInt(), sc.nextInt(), sc.nextDouble());
					System.out.println(status);
					break;
				case 6:
					exit = true;
					break;
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
