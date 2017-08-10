package com.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.BoardDao;
import com.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> list(){
		return boardDao.list();
	}
	
	public BoardVo getRead(int no) {
		return boardDao.getRead(no);
	}
	
	public int write(BoardVo boardVo) {
		return boardDao.write(boardVo);
	}
	
	public int delete(BoardVo vo) {
		return boardDao.delete(vo);
	}
	
	public BoardVo getUser(BoardVo no) {
		return boardDao.getUser(no);
	}
	
	public int update(BoardVo boardVo) {
		return boardDao.update(boardVo);
	}
}
