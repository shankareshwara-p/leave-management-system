package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.UserInfo;
import com.util.DBConnection;

public class UserInfoDao {

	public UserInfo findByEmail(String email) {
		System.out.println("Dao");
        UserInfo user = null;

        String sql = "SELECT * FROM userinfo WHERE email = ? AND active = 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);  // set email parameter
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // user exists, populate UserInfo object
                user = new UserInfo();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password")); // optional
                user.setRole(rs.getString("role"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user + "user");
        return user;  // null if user not found
    }
}
