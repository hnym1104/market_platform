package com.board.dao;

import java.util.List;

import com.board.domain.GoodsVO;

public interface GoodsDAO {
	
	public List<GoodsVO> list(int displayPost, int postNum, String cateCode) throws Exception; 
	
	public void write(GoodsVO vo) throws Exception;
	
	public int count() throws Exception;
	
	public int countCate(String cate) throws Exception;
	
	public GoodsVO view(int gdsNum) throws Exception;
	
	public void delete(int gdsNum) throws Exception;
	
	public void modify(GoodsVO vo) throws Exception;
	
	public void updateView(int gdsNum) throws Exception;

}
