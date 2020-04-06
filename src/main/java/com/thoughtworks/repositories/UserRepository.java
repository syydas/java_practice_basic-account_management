package com.thoughtworks.repositories;

import com.thoughtworks.entities.User;
import com.thoughtworks.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository implements UserRepositoryI {
    @Override
    public Boolean userRegister(User user) {
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            conn = JDBCUtil.connectToDB();
            String sql = "INSERT INTO user (name,phone_number,email,password) values(?,?,?,?)";
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, user.getName());
            ptmt.setString(2, user.getPhoneNumber());
            ptmt.setString(3, user.getEmail());
            ptmt.setString(4, user.getPassword());
            ptmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.releaseSource(conn, ptmt);
        }
        return true;
    }

    @Override
    public User userLogin(String name, String password) {
        Connection conn = null;
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        User user = new User();
        try {
            conn = JDBCUtil.connectToDB();
            String sql = "SELECT name,phone_number,email,error_number,status FROM user WHERE name = ? AND password = ?";
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setString(2, password);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                user.setName(rs.getString("name"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setEmail(rs.getString("email"));
                user.setErrorNumber(rs.getInt("error_number"));
                user.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(conn, ptmt, rs);
        }
        return user;
    }

    @Override
    public User isUserExist(String name) {
        Connection conn = null;
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        User user = new User();
        try {
            conn = JDBCUtil.connectToDB();
            String sql = "SELECT name,phone_number,email,error_number,status FROM user WHERE name = ?";
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                user.setName(rs.getString("name"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setEmail(rs.getString("email"));
                user.setErrorNumber(rs.getInt("error_number"));
                user.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(conn, ptmt, rs);
        }
        return user;
    }

    @Override
    public void updateErrorNumber(String name, int errorNumber) {
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            conn = JDBCUtil.connectToDB();
            String sql = "UPDATE user SET error_number = ? WHERE name = ?";
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, errorNumber);
            ptmt.setString(2, name);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(conn, ptmt);
        }
    }

    @Override
    public void updateStatus(String name, String status) {
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            conn = JDBCUtil.connectToDB();
            String sql = "UPDATE user SET status = ? WHERE name = ?";
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, status);
            ptmt.setString(2, name);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.releaseSource(conn, ptmt);
        }
    }
}
