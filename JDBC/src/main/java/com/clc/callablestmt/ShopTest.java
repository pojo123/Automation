package com.clc.callablestmt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopTest {
	
	Scanner sc= new Scanner(System.in);
	
	public void insertShop() throws ClassNotFoundException, SQLException {
		
		Shop s= new Shop();
		
		Connection cn=ShopUtility.getJdbcConnection();
		CallableStatement cs=cn.prepareCall("insert into shop_Data(name,city)values(?,?)");
		
		System.out.println("Enter name");
		s.setName(sc.next());
		System.out.println("Enter city");
		s.setCity(sc.next());
		
		cs.setString(1, s.getName());
		cs.setString(2, s.getCity());
		
		int rslt=cs.executeUpdate();
		if (rslt > 0) {
			System.out.println("Successful");
		} else {
            System.out.println("Failure...!!");
		}
		
	}
	
	    public void selectShop() throws ClassNotFoundException, SQLException {
	    	
	    	List<Shop> l= new ArrayList<Shop>();
	    	
	    	Connection ct=ShopUtility.getJdbcConnection();
	    	CallableStatement cst=ct.prepareCall("select * from shop_Data");
	    	ResultSet rst=cst.executeQuery();
	    	
	    	while(rst.next()) {
	    		
	    		Shop ss= new Shop();
	    		ss.setId(rst.getInt(1));
	    		ss.setName(rst.getString(2));
	    		ss.setCity(rst.getString(3));
	    		
	    		l.add(ss);	
	    	}
	    	for (Shop sh : l) {
				System.out.println(sh.getId()+"\t"+sh.getName()+"\t"+sh.getCity());
			}    	
	    	
	    }
	    
	    public void deleteShop() throws ClassNotFoundException, SQLException{
	    	
	    	Shop sp= new Shop();
	    	
	    	Connection con=ShopUtility.getJdbcConnection();
	    	System.out.println("Enter id");
	    	sp.setId(sc.nextInt());
	    	
	    	CallableStatement ca=con.prepareCall("delete from shop_Data where id="+sp.getId());
	    	int rt=ca.executeUpdate();
	    	if (rt > 0) {
				System.out.println("Delete successful..");
			} else {
                System.out.println("Delete failed");
			}   	
	    	   	
	    }
	    
	    public void updateShop() throws ClassNotFoundException, SQLException {
	    	
	    	Shop s= new Shop();
	    	
	    	Connection conn=ShopUtility.getJdbcConnection();
	    	
	    	System.out.println("Enter id");
	    	s.setId(sc.nextInt());
	    	CallableStatement cnn=conn.prepareCall("update shop_Data set city='NewYork' where id="+s.getId());
	    	int result=cnn.executeUpdate();
	    	
	    	if (result > 0) {
				System.out.println("Success");
			} else {
                System.out.println("Failed");
			}
	    }
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		ShopTest st= new ShopTest();
		//st.insertShop();
		//st.deleteShop();
		st.updateShop();
		st.selectShop();
	}

}
