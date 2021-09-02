package com.board.service;

import java.util.List;

import com.board.domain.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> list(int gdsNum) throws Exception;

	public void write(ReplyVO vo) throws Exception;

	public void modify(ReplyVO vo) throws Exception;
	
	public void delete(ReplyVO vo) throws Exception;

	public int count(int gdsNum) throws Exception;
	
	public ReplyVO view(int repNum) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
}
