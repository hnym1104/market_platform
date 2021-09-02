package com.board.service;

import java.util.List;

import com.board.domain.GoodsVO;

public interface GoodsService {
	
	public List<GoodsVO> list(int displayPost, int postNum, String cateCode) throws Exception;   // changed

	public void write(GoodsVO vo) throws Exception;   // changed
	
	public GoodsVO view(int gdsNum) throws Exception;   // changed
	
	public void modify(GoodsVO vo) throws Exception;
	
	public void delete(int bno) throws Exception;
	
	public int count() throws Exception;   // changed
	
	public int countCate(String cate) throws Exception;
	
	public void updateView(int gdsNum) throws Exception;
}
