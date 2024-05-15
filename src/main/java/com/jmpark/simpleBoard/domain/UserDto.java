package com.jmpark.simpleBoard.domain;

import java.util.Date;

public class UserDto {

    private String userId;
    private String userPwd;
    private String userName;
    private String userPhone;
    private String userEmail;
    private char status;
    private char isUser;
    private Date regDate;
    private Date lastDate;
    private Date withdrawalDate;


    public UserDto() { }

    public UserDto(String userId, String userPwd, String userName, String userPhone, String userEmail, char status, char isUser, Date regDate, Date lastDate, Date withdrawalDate) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.status = status;
        this.isUser = isUser;
        this.regDate = regDate;
        this.lastDate = lastDate;
        this.withdrawalDate = withdrawalDate;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", status=" + status +
                ", isUser=" + isUser +
                ", regDate=" + regDate +
                ", lastDate=" + lastDate +
                ", withdrawalDate=" + withdrawalDate +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public char getIsUser() {
        return isUser;
    }

    public void setIsUser(char isUser) {
        this.isUser = isUser;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Date getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(Date withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }
}