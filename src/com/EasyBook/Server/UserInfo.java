package com.EasyBook.Server;

public class UserInfo {
	private String userName;
	private String password; // password md5

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String psw) {
		this.password = psw;
	}

	@Override
    public String toString() {
        return "UserInfo{" +
               "userName='" + userName + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}
