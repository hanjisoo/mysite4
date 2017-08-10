package com.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public List<BoardVo> list(){
		return sqlSession.selectList("board.getList");
	}
	
	public BoardVo getRead(int no) {//boardVo에 담아줄꺼야
		return sqlSession.selectOne("board.selectRead", no);
	}
	
	public int write( BoardVo boardVo) {
		return sqlSession.insert("board.insert", boardVo);
	}
	
	public int delete(BoardVo vo) {
		return sqlSession.delete("board.delete", vo);
	}
	
	public BoardVo getUser(BoardVo no) {
		return sqlSession.selectOne("board.selectBoard", no);
		
	}
	
	public int update(BoardVo boardVo) {
		return sqlSession.update("board.update",boardVo);
	}
}
