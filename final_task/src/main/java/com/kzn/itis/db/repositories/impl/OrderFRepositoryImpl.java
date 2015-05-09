package com.kzn.itis.db.repositories.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kzn.itis.db.config.DatabaseFConfiguration;
import com.kzn.itis.db.model.OrderF;
import com.kzn.itis.db.model.UserF;
import com.kzn.itis.db.repositories.OrderFRepository;

@Repository("OrderRepository")
public class OrderFRepositoryImpl implements OrderFRepository {
	
	public OrderFRepositoryImpl(){}
	public OrderFRepositoryImpl(DatabaseFConfiguration config){
		this.config = config;
	}
	
	@Autowired
	private DatabaseFConfiguration config;

	public DatabaseFConfiguration getConfig() {
		return config;
	}

	public void setConfig(DatabaseFConfiguration config) {
		this.config = config;
	}

	private int IDCounter = 0;

	public int getIDCounter() {
		return IDCounter;
	}

	private void setIDCounter(int temp) {
		IDCounter = temp;
	}

	public OrderF addOrder(OrderF order) {
		Connection con = null;
		Statement stmnt = null;
		try {
			System.out.println("TEST_"+config.getDbUrl());
			con = DriverManager.getConnection(config.getDbUrl());
			System.out.println("Connection : " + con);
			stmnt = con.createStatement();
			System.out.println("Statement : " + stmnt);
		} catch (SQLException e) {
			System.out.println("AddOrderStatementCreationFailure");
			System.out.println("Connection : " + con);
			System.out.println("Statement : " + stmnt);
		}
		String sql = "INSERT INTO Orders VALUES (" + getIDCounter() + ",'"
				+ order.getUser_id() + "','" + order.getBrand() + "',"
				+ order.getPrice() + ")";

		setIDCounter(getIDCounter() + 1);
		try {
			stmnt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Insertion failed");
		}
		return order;
	}

	public OrderF removeOrder(int id) {
		Connection con = null;
		Statement stmnt = null;
		try {
			con = DriverManager.getConnection(config.getDbUrl());
			stmnt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("RemoveOrderStatementCreationFailure");
			System.out.println("Connection : " + con);
			System.out.println("Statement : " + stmnt);
		}
		OrderF temp = getOrder(id);
		String sql = "DELETE FROM Orders WHERE id = " + id;
		try {
			stmnt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Deleting failed");
		}
		return temp;
	}

	public void orderFUpdate(int id, OrderF order) {
		Connection con = null;
		Statement stmnt = null;
		try {
			con = DriverManager.getConnection(config.getDbUrl());
			stmnt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("UpdateOrderStatementCreationFailure");
			System.out.println("Connection : " + con);
			System.out.println("Statement : " + stmnt);
		}
		String sql = "UPDATE TABLE Orders SET User_id = '" + order.getUser_id()
				+ "',brand = '" + order.getBrand() + "',price = "
				+ order.getPrice() + " WHERE id =  " + id + ";";
		try {
			stmnt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Updating failed");
		}

	}

	public long getCount() {
		Connection con = null;
		Statement stmnt = null;
		try {
			con = DriverManager.getConnection(config.getDbUrl());
			stmnt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("GetOrderCountStatementCreationFailure");
			System.out.println("Connection : " + con);
			System.out.println("Statement : " + stmnt);
		}
		long count = 0;
		ResultSet res = null;
		OrderF temp = null;
		for (int i = 0; i < IDCounter; i++) {
			temp = getOrder(i);
			if (temp != null)
				count++;
		}

		return count;
	}

	public OrderF getOrder(int id) {
		Connection con = null;
		Statement stmnt = null;
		try {
			con = DriverManager.getConnection(config.getDbUrl());
			stmnt = con.createStatement();
		} catch (SQLException e) {
			System.out.println("GetOrderStatementCreationFailure");
			System.out.println("Connection : " + con);
			System.out.println("Statement : " + stmnt);
		}
		ResultSet res = null;
		OrderF temp = new OrderF();
		String sql = "SELECT * FROM Orders WHERE id = " + id + ";";
		try {
			res = stmnt.executeQuery(sql);
			temp.setOrder_id(res.getInt("order_id"));
			temp.setUser_id(res.getInt("user_id"));
			temp.setBrand(res.getString("brand"));
			temp.setPrice(res.getInt("price"));
			return temp;
		} catch (SQLException e) {
			System.out.println("Geting order failed");
		}
		return null;
	}

}
