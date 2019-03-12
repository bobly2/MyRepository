package com.how2java.springboot.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.springboot.mapper.UserMapper;
import com.how2java.springboot.pojo.User;
import com.how2java.springboot.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	public User login(String username, String password) throws Exception{
		User user = userMapper.findByUsernameAndPassword(username, password);
        return user;
	}
	
	
    public int checkUsernameExist(String username)throws Exception {
    	User user =userMapper.findByUsernameExist(username);
    	if (user==null) {
    		return 1;
		}
    	return 0;
    }

    public int checkEmailExist(String email)throws Exception {
    	User user =userMapper.findByEmailExist(email);
    	if (user==null) {
    		return 1;
		}
    	return 0;
    }
    public void gister(String username,String email,String password)throws Exception{
    	userMapper.addUser(username,email, password);
    
    }
}
