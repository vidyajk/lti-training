package com.lti.training.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.lti.training.model.Product;

public class ProductDao {

			public List<Product> fetchProducts(int from , int to) {
			Connection conn =null;
			PreparedStatement pstmt =null;
			ResultSet rs= null;
			try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			pstmt = conn.prepareStatement("select * from (select p.*, rownum r from Product_Info p) where r between ? and ?");
			pstmt.setInt(1,from);
			pstmt.setInt(2,to);
			rs = pstmt.executeQuery();
			
			List<Product> products =new ArrayList<Product>();
			while(rs.next()) {
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getDouble("price"));
			product.setQuantity(rs.getInt("quantity"));
			products.add(product);
		
			}
			return products;
		}
			catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
        	return null;
		
		}
			finally {
			try { rs.close();} catch(Exception e) {}
			try {pstmt.close();} catch(Exception e) {}
			try { conn.close();} catch(Exception e) {}
		}
	}
}
