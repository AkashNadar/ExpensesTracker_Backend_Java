package com.idfc.service;

import java.util.List;

import com.idfc.model.User;

public interface UserService {
	
	public List<User> getUsers();
	
	public int login(User user);
	
	public String updatePassword(int userId, String updatePass);
}
