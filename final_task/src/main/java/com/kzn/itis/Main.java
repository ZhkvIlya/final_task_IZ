package com.kzn.itis;

import com.kzn.itis.db.config.DatabaseFConfiguration;
import com.kzn.itis.db.model.OrderF;
import com.kzn.itis.db.model.UserF;
import com.kzn.itis.db.repositories.UserFRepository;
//import com.kzn.itis.db.repositories.impl.UserRepositoryImpl;

import com.kzn.itis.db.repositories.impl.OrderFRepositoryImpl;
import com.kzn.itis.db.repositories.impl.UserFRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 */
public class Main {

	private static Properties pros;

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	@Autowired
	private DatabaseFConfiguration config;

	public DatabaseFConfiguration getConfig() {
		return config;
	}

	public void setConfig(DatabaseFConfiguration config) {
		this.config = config;
	}

	public void run() {
		try {
			pros = new Properties();
			InputStream is = Main.class
					.getResourceAsStream("/derby.properties");
			pros.load(is);
		} catch (IOException e1) {
			System.out.println("Load failure");
		}

		// FileInputStream iStream = new FileInputStream(
		String url = pros.getProperty("connectionUrl");
		// Для вечной БД вместо ":memory..." пишем адрес директории
		// оканчивающийся папкой с именем БД
		System.out.println("URL " + url);
		Statement stmnt = null;
		String sql = "";
		Connection con;
		try {
			con = DriverManager.getConnection(url);
			stmnt = con.createStatement();
			System.out.println("Statement : " + stmnt);
			System.out.println("Connection : " + con);
		} catch (SQLException e) {
			System.out
					.println("Failure of geting connection and creating statement");
			e.printStackTrace();
		}
		/*
		sql = "CREATE TABLE Users(" + "user_id INTEGER not NULL,"
				+ "firstname VARCHAR(256)," + "lastname VARCHAR(256),"
				+ "age INTEGER," + "PRIMARY KEY(user_id))";
		try {
			stmnt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Warning:the database Users wasn't created");
		}

		sql = "CREATE TABLE Orders("
				+ "order_id INTEGER not NULL , user_id INTEGER not NULL,"
				+ "price INTEGER NOT NULL," + "brand VARCHAR(256),"
				+ "PRIMARY KEY(order_id))";
		try {
			stmnt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Warning:the database Orders wasn't created");
		}
		*/
		OrderF order = new OrderF(0, 0, "Blitz", 10000);
		UserF user = new UserF(0, "Yummy", "Dummy", 24);
		OrderFRepositoryImpl orderCRUD = new OrderFRepositoryImpl(config);
		UserFRepositoryImpl userCRUD = new UserFRepositoryImpl();
		//logger.info("url=" + config.getDbUrl());
		System.out.println(orderCRUD.getConfig());
		orderCRUD.addOrder(order);
		//userCRUD.addUser(user);
		order = new OrderF(0, 1, "Beintz", 15000);
		orderCRUD.orderFUpdate(0, order);
		System.out.println(orderCRUD.getCount());
		System.out.println(orderCRUD.removeOrder(0));
		System.out.println(orderCRUD.getCount());
	}

	public static void main(String... args) throws SQLException {
		Main main= new Main(); 
		main.run();
	}
}
