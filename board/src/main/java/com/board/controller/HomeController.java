package com.board.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.GoodsVO;
import com.board.service.GoodsService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private GoodsService service;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, @RequestParam(value = "num", defaultValue = "1") int num)
			throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		String cateCode = "ALL";

		int startPageNum = 1;
		int endPageNum = 1;

		int count = service.count();   // all posts number

		int postNum = 3;

		int pageNum = (int) Math.ceil((double) count / postNum);   // page number

		int displayPost = (num - 1) * postNum;   // post to start display in current page

		// print page in current page
		if (num <= 3) {
			endPageNum = 5;
			startPageNum = 1;
		} else {
			endPageNum = num + 2;
			startPageNum = num - 2;
		}

		boolean prev = (num == 1 || count == 0) ? false : true;   // check can move prev page
		boolean next = (num == pageNum || count == 0) ? false : true;   // check can move next page

		boolean check = (count == 0) ? false : true;

		model.addAttribute("prev", prev);
		model.addAttribute("next", next);

		List<GoodsVO> list = null;
		list = service.list(displayPost, postNum, "ALL");   // get all data from database
		for (GoodsVO v : list) {
			v.setGdsPrice(String.format("%,d", Long.parseLong(v.getGdsPrice())));
		}   // transform price
		
		model.addAttribute("list", list);
		model.addAttribute("check", check);
		model.addAttribute("curPage", num);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("cateCode", cateCode);   // convey necessary information to view file
		
		return "home";
	}

}
