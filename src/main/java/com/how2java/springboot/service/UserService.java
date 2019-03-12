package com.how2java.springboot.service;

import com.how2java.springboot.pojo.User;

public interface UserService {
	public User login(String username, String password) throws Exception;

    public int checkEmailExist(String email) throws Exception;

    public int checkUsernameExist(String username) throws Exception;
    
    public void gister(String username,String password, String email)throws Exception;
}
