package com.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.UserDao;
import com.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public boolean check(String email) {
		String email2= userDao.check(email);
		if(email2==null) {
			return true;
		}else {
			return false;
		}
	}
	
	public int join(UserVo userVo) {
		return userDao.insert(userVo);
				//내가지어준 이름
	}
	
	public UserVo getUser(String email, String password) {
		return userDao.getUser(email,password);
	}
	
	public UserVo getUser(int no) {	//위에 꺼랑 인자가 다르니깐 됨.
		return userDao.getUser(no);
	}
	
	public int updateUser(UserVo userVo) {
		return userDao.updateUser(userVo);
	}
}
