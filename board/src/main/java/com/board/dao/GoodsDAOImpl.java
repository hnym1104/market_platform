package com.board.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.GoodsVO;

@Repository
public class GoodsDAOImpl implements GoodsDAO {

	@Inject
	private SqlSession sql;

	private static String namespace = "com.board.mappers.goods";

	// 게시글 작성
	@Override
	public void write(GoodsVO vo) throws Exception {
		sql.insert(namespace + ".write", vo);
	}

	// 게시글 조회
	@Override
	public List<GoodsVO> list(int displayPost, int postNum, String cateCode) throws Exception {
		HashMap<String, Integer> data = new HashMap<String, Integer>();
		
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		
		if(cateCode.equals("Toy"))
			return sql.selectList(namespace + ".listPageToy", data);
		else if(cateCode.equals("Clothes"))
			return sql.selectList(namespace + ".listPageClothes", data);
		else if(cateCode.equals("Fruits"))
			return sql.selectList(namespace + ".listPageFruits", data);
		else if(cateCode.equals("Electronics"))
			return sql.selectList(namespace + ".listPageElectronics", data);
		else if(cateCode.equals("Books"))
			return sql.selectList(namespace + ".listPageBooks", data);
		else
			return sql.selectList(namespace + ".listPage", data);
	}

	@Override
	public int count() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".count");
	}

	@Override
	public int countCate(String cate) throws Exception {
		String cateCode = cate;
		if(cate.equals("ALL"))
			return sql.selectOne(namespace + ".count");
		else
			return sql.selectOne(namespace + ".countCate", cateCode);
	}

	@Override
	public GoodsVO view(int gdsNum) throws Exception {
		return sql.selectOne(namespace + ".view", gdsNum);
	}

	@Override
	public void delete(int gdsNum) throws Exception {
		sql.delete(namespace + ".delete", gdsNum);
	}

	@Override
	public void modify(GoodsVO vo) throws Exception {
		sql.update(namespace + ".modify", vo);
	}

	@Override
	public void updateView(int gdsNum) throws Exception {
		sql.update(namespace + ".updateView", gdsNum);	
	}

	/*
	 * @Override public List<GoodsVO> listPage(int displayPost, int postNum) throws
	 * Exception { HashMap data = new HashMap();
	 * 
	 * data.put("displayPost", displayPost); data.put("postNum", postNum);
	 * 
	 * return sql.selectList(namespace + ".listPage", data); }
	 */

}
