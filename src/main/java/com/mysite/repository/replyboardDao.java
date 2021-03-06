package com.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.ReplyboardVo;

@Repository
public class replyboardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<ReplyboardVo> list(){
		return sqlSession.selectList("reply.getList");
	}
	
	public int write(ReplyboardVo replyVo) {
		return sqlSession.insert("reply.insert", replyVo);
	}
	
	public ReplyboardVo getRead(int no) {
		return sqlSession.selectOne("reply.selectRead", no);
	}
	
	public int updateHit(int no) {
		return sqlSession.update("reply.updateHit", no);
	}
	
	
	public int update(int groupNo, int orderNo) {
		Map<String, Object> userMap=new HashMap<String, Object>();
		userMap.put("groupNo",groupNo);
		userMap.put("orderNo",orderNo);
		return sqlSession.update("reply.update", userMap);
	}
	/*public int update(ReplyboardVo replyVo) {
		return sqlSession.update("reply.update", replyVo);
	}*/
	
	public int reply(ReplyboardVo replyVo) {
		int orderNo=replyVo.getOrderNo();
		int depth=replyVo.getDepth();
		++orderNo;
		++depth;
		replyVo.setOrderNo(orderNo);
		replyVo.setDepth(depth);
		return sqlSession.insert("reply.insert2", replyVo);
	}
	
	public int delete(ReplyboardVo vo) {
		return sqlSession.delete("reply.delete", vo);
	}
	
	public ReplyboardVo getBoard(int no) {
		return sqlSession.selectOne("reply.selectBoard", no);
	}
	
	public int update2(ReplyboardVo replyVo) {
		return sqlSession.update("reply.update2",replyVo);
	}
}
