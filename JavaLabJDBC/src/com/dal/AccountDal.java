package com.dal;

import java.sql.SQLException;
import java.util.List;

import com.pojo.Account;

public interface AccountDal {

	List<Account> getAllAccounts()throws SQLException;
	
	int addAccount(Account obj) throws SQLException;
	
	int updateAccount(Account obj) throws SQLException;
	
	int deleteAccount(int id)throws SQLException;
	
	String transfer_fund(int sid,int rid,double amount) throws SQLException;
	
}
