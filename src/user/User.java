/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author seungmin
 */
public class User {
    private int userNo;
    private String userId;
    private String userName;
    private String userPw;

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }
    
        public int getUserNo() {
        return userNo;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPw() {
        return userPw;
    }
    
    
}
