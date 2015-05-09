package com.kzn.itis.db.repositories.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kzn.itis.db.config.DatabaseFConfiguration;

import com.kzn.itis.db.model.UserF;
import com.kzn.itis.db.repositories.UserFRepository;

@Repository("UserRepository")
public class UserFRepositoryImpl implements UserFRepository {

	private static final Logger logger = LoggerFactory
			.getLogger(UserFRepositoryImpl.class);

	private int IDCounter = 0;

	public int getIDCounter() {
		return IDCounter;
	}

	private void setIDCounter(int temp) {
		IDCounter = temp;
	}

	@Autowired
	private DatabaseFConfiguration config;

	public DatabaseFConfiguration getConfig() {
		return config;
	}

	public void setConfig(DatabaseFConfiguration config) {
		this.config = config;
	}

	/**
	 * Trying with EntityManager
	 *
	 * @param user
	 * @return
	 */
	// @Override
	public UserF addUser(UserF user) {
		Connection con = null;
		Statement stmnt = null;
		try {
			con = DriverManager.getConnection(config.getDbUrl());
			System.out.println("Connection : " + con);
			stmnt = con.createStatement();
			System.out.println("Statement : " + stmnt);
		} catch (SQLException e) {
			System.out.println("Failure during statement creation");
		}
		String sql = "INSERT INTO Users VALUES (" + getIDCounter() + ",'"
				+ user.getFirstname() + "','" + user.getLastname() + "',"
				+ user.getAge() + ")";

		setIDCounter(getIDCounter() + 1);
		try {
			stmnt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Insertion failed");
		}
		return user;
	}

	/**
	 * There are several ways to get count.
	 *
	 * Here we implement it with the Hibernate Session - by using unwrap from
	 * JPA 2.0
	 *
	 * @return
	 */
	// @Override
	public long getCount() {
		Connection con = null;
		Statement stmnt = null;
		try {
			con = DriverManager.getConnection(config.getDbUrl());
			System.out.println("Connection : " + con);
			stmnt = con.createStatement();
			System.out.println("Statement : " + stmnt);
		} catch (SQLException e) {
			System.out.println("GetCountStatementCreationFailure");
		}
		long count = 0;
		ResultSet res = null;
		for (int i = 0; i < IDCounter; i++) {
			String sql = "SELECT * FROM Users WHERE id = " + i;
			try {
				res = stmnt.executeQuery(sql);
				if (res != null)
					count++;
			} catch (SQLException e1) {
				System.out.println("failure");
			}
		}

		return count;
	}

	public UserF removeUser(int id) {
		Connection con = null;
		Statement stmnt = null;
		try {
			con = DriverManager.getConnection(config.getDbUrl());
			System.out.println("Connection : " + con);
			stmnt = con.createStatement();
			System.out.println("Statement : " + stmnt);
		} catch (SQLException e) {
			System.out.println("RemoveUserStatementCreationFailure");
		}
		UserF temp = getUser(id);
		String sql  = "DELETE FROM Users WHERE id = " + id;
		try {
			stmnt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("no lines with such id or incorrect syntaxis");
		}
		return temp;
	}

	public void userUpdate(int id, UserF user) {
		Connection con = null;
		Statement stmnt = null;
		try {
			con = DriverManager.getConnection(config.getDbUrl());
			System.out.println("Connection : " + con);
			stmnt = con.createStatement();
			System.out.println("Statement : " + stmnt);
		} catch (SQLException e) {
			System.out.println("Failure during statement creation");
		}
		String sql = "UPDATE TABLE Users SET Firstname = '"
				+ user.getFirstname() + "',Lastname = '" + user.getLastname()
				+ "',Age = " + user.getAge() + " WHERE id =  " + id + ";";
		try {
			stmnt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("no lines with such id or incorrect syntaxis");
		}
		
	}

	public UserF getUser(int id) {
		logger.info("getting user");
		Connection con = null;
		Statement stmnt = null;
		try {
			con = DriverManager.getConnection(config.getDbUrl());
			System.out.println("Connection : " + con);
			stmnt = con.createStatement();
			System.out.println("Statement : " + stmnt);
		} catch (SQLException e) {
			System.out.println("Failure during statement creation");
		}
		ResultSet res = null;
		UserF temp = new UserF();
		String sql = "SELECT * FROM Users WHERE id = " + id + ";";
		try {
			res = stmnt.executeQuery(sql);
			temp.setId(res.getInt("id"));
			temp.setFirstname(res.getString("firstname"));
			temp.setLastname(res.getString("lastname"));
			temp.setAge(res.getInt("age"));
		} catch (SQLException e) {
			System.out.println("Query failure");
		}
		return temp;
	}



}
