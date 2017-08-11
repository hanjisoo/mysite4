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
		//글 불러와
		BoardVo boardVo = boardDao.getRead(no);
		
		if(boardVo != null) {
			//hit+1. hit은 안넘겨줘도 됨
			boardDao.updateHit(no);
		}
		//글 넘겨줘
		return boardVo ;
	}
	
	/*public int updateHit(int no) {
		return boardDao.updateHit(no);
	}*/
	
	public int write(BoardVo boardVo) {
		return boardDao.write(boardVo);
	}
	
	public int delete(BoardVo vo) {
		return boardDao.delete(vo);
	}
	
	public BoardVo getBoard(int no) {
		return boardDao.getBoard(no);
	}
	
	public int update(BoardVo boardVo) {
		return boardDao.update(boardVo);
	}
}
