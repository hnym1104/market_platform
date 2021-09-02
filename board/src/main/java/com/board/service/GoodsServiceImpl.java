package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.board.dao.GoodsDAO;
import com.board.dao.ReplyDAO;
import com.board.domain.GoodsVO;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Inject
	private GoodsDAO dao;
	
	@Inject
	private ReplyDAO replyDao;

	@Override
	public List<GoodsVO> list(int displayPost, int postNum, String cateCode) throws Exception {

		return dao.list(displayPost, postNum, cateCode);   // changed
	}

	@Override
	public void write(GoodsVO vo) throws Exception {
		
		dao.write(vo);

	}

	@Override
	public GoodsVO view(int gdsNum) throws Exception {
		return dao.view(gdsNum);
	}

	@Override
	public void modify(GoodsVO vo) throws Exception {
		
		dao.modify(vo);
	}

	// 게시물 삭제
	@Override
	public void delete(int gdsNum) throws Exception {
		replyDao.deleteAllReply(gdsNum);
		dao.delete(gdsNum);
	}

	// 게시물 개수
	@Override
	public int count() throws Exception {
		
		return dao.count();
	}
	
	public int countCate(String cate) throws Exception
	{
		return dao.countCate(cate);
	}

	@Override
	public void updateView(int gdsNum) throws Exception {
		dao.updateView(gdsNum);
	}
}