package com.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.ReplyVO;

@Repository
public class ReplyDAOIml implements ReplyDAO {

	@Inject
	private SqlSession sql;

	private static String namespace = "com.board.mappers.reply";
	
	@Override
	public List<ReplyVO> list(int gdsNum) throws Exception {
		return sql.selectList(namespace + ".replyList", gdsNum);
	}

	@Override
	public void write(ReplyVO vo) throws Exception {
		sql.insert(namespace + ".replyWrite", vo);

	}

	@Override
	public void modify(ReplyVO vo) throws Exception {
		sql.update(namespace + ".replyModify", vo);
	}

	@Override
	public void delete(ReplyVO vo) throws Exception {
		sql.delete(namespace + ".replyDelete", vo);
	}

	@Override
	public int count(int gdsNum) throws Exception {
		return sql.selectOne(namespace + ".count", gdsNum);
	}

	@Override
	public ReplyVO view(int repNum) throws Exception {
		return sql.selectOne(namespace + ".view", repNum);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		sql.update(namespace + ".replyUpdate", vo);
	}

	@Override
	public void deleteAllReply(int gdsNum) throws Exception {
		sql.delete(namespace + ".deleteAllReply", gdsNum);
	}

}
