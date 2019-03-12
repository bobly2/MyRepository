package com.how2java.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.how2java.springboot.pojo.User;


@Mapper
public interface UserMapper {
	 //新增用户 
    @Insert(" insert into user (username,email,password) values (#{username,jdbcType=VARCHAR},#{email,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR}) ") 
    public void addUser(@Param("username")String username,@Param("email") String email,@Param("password") String password);
    
    //登录    
    @Select("select email,username,password from user where password= #{password} and username = #{username} ") 
    public User findByUsernameAndPassword(@Param("username")String username,@Param("password") String password); 
    
    @Select("select username from user where username = #{username} ") 
    public User findByUsernameExist(@Param("username")String username); 
    
    @Select("select email from user where email= #{email} ") 
    public User findByEmailExist(@Param("email")String email); 
}	

