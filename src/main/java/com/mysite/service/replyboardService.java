package com.mysite.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.replyboardDao;
import com.mysite.vo.ReplyboardVo;

@Service
public class replyboardService {

	@Autowired
	private replyboardDao replyDao;
	
	public List<ReplyboardVo> list(){
		return replyDao.list();
	}
	
	public int write(ReplyboardVo replyVo) {
			return replyDao.write(replyVo);
	}
	
	public ReplyboardVo getRead (int no) {
		ReplyboardVo replyVo =replyDao.getRead(no);
		
		if(replyVo != null) {
			replyDao.updateHit(no);
		}
		return replyVo ;
	}
	
	public int reply(ReplyboardVo replyVo) {
		//no하나 올리기
		replyDao.update(replyVo.getGroupNo(),replyVo.getOrderNo());
			
		//저장
		return replyDao.reply(replyVo);
	}
	
	public int delete(ReplyboardVo vo) {
		return replyDao.delete(vo);
	}
	
	public ReplyboardVo getBoard(int no) {
		return replyDao.getBoard(no);
	}
	
	public int update2(ReplyboardVo replyVo) {
		return replyDao.update2(replyVo);
	}
}
