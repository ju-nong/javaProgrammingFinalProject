/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.*;

/**
 *
 * @author seungmin
 */
public class UserDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    public UserDAO() {
        String strDriver = "com.mysql.cj.jdbc.Driver";
        String strURL = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC";
        String strUser = "root";
        String strPWD = "1234";
   
        try {
            Class.forName(strDriver);
            conn = DriverManager.getConnection(strURL, strUser, strPWD);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public boolean isOverlap(String userId) {
        boolean result = false;
        String SQL = "select * from user where userId = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, userId);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
                result = true;
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public int register(User user) {
        int result = -1;
        String SQL = "insert into user set userId = ?, userName = ?, userPw = ?";
        try{
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getUserPw());
            
            result = pstmt.executeUpdate();
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public User login(String userId, String userPw) {
        User result = null;
        String SQL = "select * from user where userId = ? and userPw = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPw);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
                result = new User();
                result.setUserNo(rs.getInt("userNo"));
                result.setUserId(rs.getString("userId"));
                result.setUserName(rs.getString("userName"));
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    
    public void dbClose() {
        try {
            rs.close();
            pstmt.close();
            conn.close();
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
