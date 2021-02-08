package com.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {

	List<Alien> aliens;

	public List<Alien> getAliens() {
		aliens = new ArrayList<Alien>();
		String sql = "select * from alien";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jerseydemo", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setPoints(rs.getInt("points"));
				aliens.add(a);
			}
		} catch (Exception e) {
			System.out.println("Error in getAliens");
		}
		return aliens;
	}

	public Alien getAlien(int id) {
		aliens = new ArrayList<Alien>();
		Alien a = new Alien();
		String sql = "select * from alien where id=" + id;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jerseydemo", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setPoints(rs.getInt("points"));
			}
		} catch (Exception e) {
			System.out.println("Error in getAlien");
		}
		return a;
		/*
		 * for (Alien a : aliens) { if (a.getId() == id) return a; }
		 */
//		return new Alien();
	}

	public void create(Alien a) {
//		aliens.add(a);
		String sql = "insert into alien values(?,?,?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jerseydemo", "root", "root");
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, a.getId());
			stmt.setString(2, a.getName());
			stmt.setInt(3, a.getPoints());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Alien a) {
		String sql = "update alien set name=?, points=? where id=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jerseydemo", "root", "root");
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(3, a.getId());
			stmt.setString(1, a.getName());
			stmt.setInt(2, a.getPoints());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteAlien(int id) {
		String sql = "delete from alien where id=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jerseydemo", "root", "root");
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error in deleteAlien");
		}
	}
}
