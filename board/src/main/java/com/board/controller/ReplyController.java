package com.board.controller;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.domain.GoodsVO;
import com.board.domain.ReplyVO;
import com.board.service.GoodsService;
import com.board.service.ReplyService;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {

	@Inject
	ReplyService service;

	@Inject
	GoodsService service2;
	
	static boolean checkNull = false;
	static boolean replyWrite = false;
	
	public static boolean getCheckNull()
	{
		return checkNull;
	}
	
	public static void setCheckNull(boolean checkNull2)
	{
		checkNull = checkNull2;
	}
	
	public static boolean getReplyWrite()
	{
		return replyWrite;
	}
	
	public static void setReplyWrite(boolean replyWrite2)
	{
		replyWrite = replyWrite2;
	}
	
	// reply write
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWirte(ReplyVO vo, Model model, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// check if all field is filled
		if (vo.getRepWriter().isEmpty() || vo.getRepContent().isEmpty() || vo.getRepPwd().isEmpty())
		{   // if some field is not filled print pop-up
			checkNull = true;
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>alert('이름, 비밀번호, 내용 란을 모두 채워 주십시오.'); history.go(-1);</script>");
			out.flush();
		}
		else
		{   // all field filled
			service.write(vo);   // write new reply data to database
			checkNull = false;
		}
		
		replyWrite = true;

		GoodsVO vo2 = service2.view(vo.getGdsNum());

		return "redirect:/board/view?gdsNum=" + vo.getGdsNum() + "&cateCode=" + vo2.getCateCode();
	}

}
