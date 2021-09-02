package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.ReplyDAO;
import com.board.domain.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	ReplyDAO dao;

	@Override
	public List<ReplyVO> list(int gdsNum) throws Exception {
		return dao.list(gdsNum);
	}

	@Override
	public void write(ReplyVO vo) throws Exception {
		dao.write(vo);
		
	}

	@Override
	public void modify(ReplyVO vo) throws Exception {
		dao.modify(vo);
		
	}

	@Override
	public void delete(ReplyVO vo) throws Exception {
		dao.delete(vo);
	}

	@Override
	public int count(int gdsNum) throws Exception {
		return dao.count(gdsNum);
	}

	@Override
	public ReplyVO view(int repNum) throws Exception {
		return dao.view(repNum);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		dao.update(vo);
	}

}
