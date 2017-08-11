package com.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;	//어플리케이션.xml에서 만들어주고 있음
	
	
	public int insert(UserVo userVo) {
		return sqlSession.insert("user.insert",userVo);
	}
	
	public UserVo getUser(String email, String password) {
		Map<String, Object> userMap=new HashMap<String, Object>();
		userMap.put("email",email);//xml에 있는 키값(#{email})을 가져와서 담아
		userMap.put("password",password);
		UserVo userVo= sqlSession.selectOne("user.selectUserByEmailPw",userMap);
		return userVo;
		/*return sqlSession.selectOne("user.selectUserByEmailPw",userMap);*/
	}
	
	public UserVo getUser(int no) {
		UserVo userVo=sqlSession.selectOne("user.selectUserByNo",no);
		return userVo;
	}
	
	public int updateUser(UserVo userVo) {
		System.out.println(userVo.toString());
		return sqlSession.update("user.update",userVo);
	}
}
