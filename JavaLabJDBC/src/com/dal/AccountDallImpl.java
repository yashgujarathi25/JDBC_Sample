package com.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.pojo.Account;
import com.util.DbUtil;

public class AccountDallImpl implements AccountDal {

	private Connection con;
	private Statement stmt;
	private CallableStatement cstmt;
	private ResultSet rset;
	private PreparedStatement pstmt1, pstmt2, pstmt3;
	
	public AccountDallImpl() throws SQLException {
		con=DbUtil.getCon();
		
		stmt = con.createStatement();
		pstmt1=con.prepareStatement("insert into account values(?,?,?,?,?)");
		pstmt2=con.prepareStatement("update account set name=?, email=?,city=?,balance=? where actno=?");
		pstmt3 =con.prepareStatement("delete from account where actno=?");
		cstmt = con.prepareCall("{call transfer_fund(?,?,?,?,?)}");
		//register out parameter
		
		cstmt.registerOutParameter(4, Types.DOUBLE);
		cstmt.registerOutParameter(5, Types.DOUBLE);
	}

	@Override
	public List<Account> getAllAccounts() throws SQLException {
		List<Account> allAccount = new ArrayList<Account>();
		rset = stmt.executeQuery("select * from account");
		while(rset.next()) {
			allAccount.add(new Account(rset.getInt("actno"), 
										rset.getString("name"),
										rset.getString("email"), 
										rset.getString("city"), 
										rset.getDouble("balance")));
		}
		
		return allAccount;
	}

	@Override
	public int addAccount(Account obj) throws SQLException {
		pstmt1.setInt(1, obj.getActno());
		pstmt1.setString(2, obj.getName());
		pstmt1.setString(3, obj.getEmail());
		pstmt1.setString(4, obj.getCity());
		pstmt1.setDouble(5, obj.getBalance());
		int i = pstmt1.executeUpdate();
		
		System.out.println("Insert Successfully----Obj "+ obj);
		return i;
	}

	@Override
	public int updateAccount(Account obj) throws SQLException {
		pstmt2.setString(1, obj.getName());
		pstmt2.setString(2, obj.getEmail());
		pstmt2.setString(3, obj.getCity());
		pstmt2.setDouble(4, obj.getBalance());
		pstmt2.setInt(5, obj.getActno());
		
		int i = pstmt2.executeUpdate();
		
		System.out.println("Update Obj" + obj);
		return 0;
	}

	@Override
	public int deleteAccount(int id) throws SQLException {
		pstmt3.setInt(1, id);
		
		int i = pstmt3.executeUpdate();
		
		return i;
	}

	@Override
	public String transfer_fund(int sid,int rid, double amount) throws SQLException {
		cstmt.setInt(1, sid);
		cstmt.setInt(2, rid);
		cstmt.setDouble(3, amount);
		
		cstmt.execute();
		
		return "Money Transfer : Sender Balance = "+cstmt.getDouble(4)+" Reciever Balance = "+cstmt.getDouble(5);
	}
	
	
}
