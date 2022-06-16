package com.anjaniy.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.anjaniy.pms.models.Product;

public class ProductDAO {
	
	//Database Configurations:
	private static final String DBURL = "jdbc:mysql://localhost:3306/productdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Anjaniy@12345";
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	
	//Database Queries:
	private static final String INSERT_PRODUCT = "INSERT INTO product" + "  (name, description, category, price) VALUES "
			+ " (?, ?, ?, ?);";
	private static final String READ_PRODUCTS = "select * from product";
	private static final String READ_PRODUCT_BY_ID = "select id, name, description, category, price from product where id = (?)";
	private static final String UPDATE_PRODUCT = "update product set name=(?),description=(?),category=(?),price=(?) where id=(?);";
	private static final String DELETE_PRODUCT = "delete from product where id = (?)";
	
	public ProductDAO () {
		
	}
	
	private Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertProduct(Product product) {
		try(Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setString(3, product.getCategory());
			preparedStatement.setLong(4, product.getPrice());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		try(Connection connection = getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(READ_PRODUCTS);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				 int id = resultSet.getInt("id");
				 String name = resultSet.getString("name");
				 String description = resultSet.getString("description");
				 String category = resultSet.getString("category");
				 long price = resultSet.getLong("price");
				 products.add(new Product(id, name, description, category, price));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public Product getProductById(int id) {
		Product product = null;
		try(Connection connection = getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(READ_PRODUCT_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			
			 while(resultset.next()) {
				 String name = resultset.getString("name");
				 String description = resultset.getString("description");
				 String category = resultset.getString("category");
				 long price = resultset.getLong("price");
				 product = new Product(name, description, category, price);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
	
	public boolean updateProduct(Product product) {
		boolean isUpdated = false;
		try(Connection connection = getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setString(3, product.getCategory());
			preparedStatement.setLong(4, product.getPrice());
			preparedStatement.setInt(5, product.getId());
			
			isUpdated = (preparedStatement.executeUpdate() > 0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdated;
	}
	
	public boolean deleteProduct(int id) {
		boolean isDeleted = false;
		try(Connection connection = getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
			preparedStatement.setInt(1, id);
			isDeleted = (preparedStatement.executeUpdate() > 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDeleted;
	}
}
