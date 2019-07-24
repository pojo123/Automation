package com.clc.callablestmt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.clc.utility.ProductUtility;
import java.sql.CallableStatement;

public class ProductTest {

	Scanner sc = new Scanner(System.in);

	public void insertProduct() throws ClassNotFoundException, SQLException {

		Product p = new Product();

		Connection c = ProductUtility.getJdbcConnection();
		CallableStatement cs= c.prepareCall("insert into product(name,quantity,price,type)values(?,?,?,?)");
	
	    System.out.println("Enter name");
	    p.setName(sc.next());
	    System.out.println("Enter quantity");
	    p.setQuantity(sc.nextInt());
	    System.out.println("Enter price");
	    p.setPrice(sc.nextFloat());
	    System.out.println("Enter type");
	    p.setType(sc.next());
	    
	    cs.setString(1, p.getName());
	    cs.setInt(2, p.getQuantity());
	    cs.setFloat(3, p.getPrice());
	    cs.setString(4, p.getType());
	    
	    int result=cs.executeUpdate();
	    if (result > 0) {
			System.out.println("success");
		} else {
             System.out.println("failure...");
		}
	
	}
	
	    public void selectProduct() throws ClassNotFoundException, SQLException {
	    	
	    	List<Product> l= new ArrayList<Product>();
	    	
	    	Connection con=ProductUtility.getJdbcConnection();
	    	//CallableStatement cs=con.prepareCall("select * from product");
	    	CallableStatement cs=con.prepareCall("select quantity,name,price from product order by quantity desc limit 3");
	    	ResultSet rs=cs.executeQuery();
	    	
	    	while(rs.next()) {
	    		
	    		Product p= new Product();
			/*
			 * p.setId(rs.getInt(1)); p.setName(rs.getString(2));
			 * p.setQuantity(rs.getInt(3)); p.setPrice(rs.getFloat(4));
			 * p.setType(rs.getString(5));
			 */
	    		p.setQuantity(rs.getInt(1));
	    		p.setName(rs.getString(2));
	    		p.setPrice(rs.getFloat(3));
	    		
	    		l.add(p);
	    	}
	    	for (Product product : l) {
	    		System.out.println(product.getName()+"\t"+product.getQuantity()+"\t"+product.getPrice());
	    		//System.out.println(product.getId()+"\t"+product.getName()+"\t"+product.getQuantity()+"\t"+product.getPrice()+"\t"+product.getType());
			}
	    	
	    }
	    
	         public void deleteProduct() throws ClassNotFoundException, SQLException {
	        	 
	        	 Product p= new Product();
	        	 Connection ct=ProductUtility.getJdbcConnection();
	        	 System.out.println("Enter id");
	        	 p.setId(sc.nextInt());
	        	 CallableStatement cst=ct.prepareCall("delete from product where id="+p.getId());
	        	 int r=cst.executeUpdate();
	        	 if (r > 0) {
					System.out.println("pass");
				} else {
                    System.out.println("fail");
				}
	         }
	         
	         public void updateProduct() throws ClassNotFoundException, SQLException {
	        	 
	        	 Product p=new Product();
	        	 System.out.println("Enter id");
	        	 p.setId(sc.nextInt());
	        	 
	        	 Connection cc=ProductUtility.getJdbcConnection();
	        	 CallableStatement cl=cc.prepareCall("update product set price=35000 where id="+p.getId());
	        	 int rt=cl.executeUpdate();
	        	 if (rt > 0) {
					System.out.println("pass");
				} else {
                    System.out.println("fail");
				}
	         }
          
	     public static void main(String[] args) throws ClassNotFoundException, SQLException {
			
	    	 ProductTest pt= new ProductTest();
	    	 //pt.insertProduct();
	    	// pt.deleteProduct();
	    	// pt.updateProduct();
	    	 pt.selectProduct();
		}
}
