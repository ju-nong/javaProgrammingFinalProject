/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeTable;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author seungmin
 */
public class TimeTableDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    public TimeTableDAO() {
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
    
    public int addSubject (TimeTable tt) {
        int result = -1;
        String SQL = "insert into timeTable set subject = ?, professor = ?, classroom = ?, day = ?, startTime = ?, endTime = ?, userNo = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, tt.getSubject());
            pstmt.setString(2, tt.getProfessor());
            pstmt.setString(3, tt.getClassroom());
            pstmt.setInt(4, tt.getDay());
            pstmt.setInt(5, tt.getStartTime());
            pstmt.setInt(6, tt.getEndTime());
            pstmt.setInt(7, tt.getUserNo());
            
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public ArrayList<TimeTable> getList(int userNo) {
        ArrayList<TimeTable> result = new ArrayList<>();
        String SQL = "select * from timeTable where userNo = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, userNo);
            
            rs = pstmt.executeQuery();
            while(rs.next()) {
                TimeTable tt = new TimeTable();
                tt.setSubject(rs.getString("subject"));
                tt.setProfessor(rs.getString("professor"));
                tt.setClassroom(rs.getString("classroom"));
                tt.setDay(rs.getInt("day"));
                tt.setStartTime(rs.getInt("startTime"));
                tt.setEndTime(rs.getInt("endTime"));
                
                result.add(tt);
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public TimeTable getOne(TimeTable tt) {
        TimeTable result = new TimeTable();
        String SQL = "select * from timeTable where subject = ? and professor = ? and classroom = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, tt.getSubject());
            pstmt.setString(2, tt.getProfessor());
            pstmt.setString(3, tt.getClassroom());
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
                result.setStartTime(rs.getInt("startTime"));
                result.setEndTime(rs.getInt("endTime"));
                result.setDay(rs.getInt("day"));
                result.setTimeNo(rs.getInt("timeNo"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public int isOverlap(String subject,int userNo) {
        int result = -1;
        String SQL = "select * from timeTable where subject = ? and userNo = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, subject);
            pstmt.setInt(2, userNo);
            rs = pstmt.executeQuery();
            if(rs.next()) result = 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public int isOverlap(String subject,int userNo,int timeNo) {
        int result = -1;
        String SQL = "select * from timeTable where subject = ? and userNo = ? and timeNo != ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, subject);
            pstmt.setInt(2, userNo);
            pstmt.setInt(3, timeNo);
            rs = pstmt.executeQuery();
            if(rs.next()) result = 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    
    public int update(TimeTable tt) {
        int result = -1;
        String SQL = "update timeTable set subject = ?, professor = ?, classroom = ?, day = ?, startTime = ?, endTime = ? where timeNo = ? and userNo = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, tt.getSubject());
            pstmt.setString(2, tt.getProfessor());
            pstmt.setString(3, tt.getClassroom());
            pstmt.setInt(4, tt.getDay());
            pstmt.setInt(5, tt.getStartTime());
            pstmt.setInt(6, tt.getEndTime());
            pstmt.setInt(7, tt.getTimeNo());
            pstmt.setInt(8, tt.getUserNo());
            
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public int delete(TimeTable tt) {
        int result = -1;
        String SQL = "delete from timeTable where timeNo = ? and userNo = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, tt.getTimeNo());
            pstmt.setInt(2, tt.getUserNo());
            
            result = pstmt.executeUpdate();
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
