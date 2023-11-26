package com.hrf.library.entity;

import java.io.Serializable;

public class LoginUser implements Serializable {

    /**
     * default UID
     */
    @Override
    public String toString() {
        return "LoginUser{" + "loginName=" + name + ", password=" + password + ", secretKey=" + secretKey + ", roleId=" + roleId + ", token=" + token + '}';
    }

	private static final long serialVersionUID = 1L;

	private String name;
	
	private String password;
	
	private String secretKey;
	
	private String roleId;  // 角色代码 1-普通用户 9-管理员
	
	private String token;  // UUID，区分登录用户
	
	LoginUser(String name, String password, String secretKey){
		this.setName(name);
		this.setPassword(password);
		this.setSecretKey(secretKey);
	}
	public LoginUser(){
		this.setName("");
		this.setPassword("");
		this.setSecretKey("");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param tocken the token to set
	 */
	public void setToken(String tocken) {
		this.token = token;
	}
}
