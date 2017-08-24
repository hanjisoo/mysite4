package com.mysite.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.GuestbookDao;
import com.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	public List<GuestbookVo> list() {//보내줄 조건 없고 리스트로 받아
		return guestbookDao.getGuestbook();
	}
	
	public int add(GuestbookVo vo) {
		return guestbookDao.insert(vo);
	}
	
	public int delete(GuestbookVo vo) {
		return guestbookDao.delete(vo);
	}
	
	
	public GuestbookVo add2(GuestbookVo guestbookVo) {
		int no=guestbookDao.insertNo(guestbookVo);
		
		/*guestbookVo=guestbookDao.selectByNo(guestbookVo.getNo());*/
		guestbookVo=guestbookDao.selectByNo(no);
		return guestbookVo;
	
	}
}
