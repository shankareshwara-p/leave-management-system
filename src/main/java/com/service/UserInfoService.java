package com.service;

import com.dao.UserInfoDao;
import com.model.UserInfo;

public class UserInfoService {

    
    private UserInfoDao userInfoDao = new UserInfoDao();

    
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public UserInfo getUserInfo(){
//
//	 return this.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
//	
//    }
    
    public UserInfo findUserByEmail(String email) {
    	System.out.println("service");
	return userInfoDao.findByEmail(email);  
	}

//    public void saveUser(UserInfo userInfo) {
//	userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
//	userInfo.setActive(false);
//	UserInfoDao.save(userInfo);
//
//    }
//
//    public List<UserInfo> getUsers() {
//
//	return UserInfoDao.findAllByOrderById();
//    }
//
//    public UserInfo getUserById(int id) {
//
//	return UserInfoDao.findById(id);
//    }
//
//    public void deleteUser(int id) {
//	UserInfoDao.delete(id);
//    }
//
//    public void blockUser(int id) {
//
//	UserInfoDao.blockUser(id);
//
//    }
//
//    public void unBlockUser(int id) {
//
//	UserInfoDao.unBlockUser(id);
//    }
//
//	

}