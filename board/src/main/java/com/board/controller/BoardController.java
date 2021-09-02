package com.board.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.board.domain.GoodsVO;
import com.board.domain.ReplyVO;
import com.board.service.GoodsService;
import com.board.service.ReplyService;
import com.board.utils.UploadFileUtils;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private GoodsService service;

	@Inject
	private ReplyService replyService;

	@Resource(name = "uploadPath")
	private String uploadPath;

	static boolean checkViewUpdate = false;
	static boolean check = false;
	static boolean checkReplyWrite = false;

	// listing + paging
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void getListPage(Model model, @RequestParam(value = "num", defaultValue = "1") int num,
			@RequestParam(value = "cateCode", defaultValue = "ALL") String cateCode) throws Exception {

		int startPageNum = 1;
		int endPageNum = 1;
		
		int count = service.countCate(cateCode);   // number of posts for category

		int postNum = 3;   // post number for one page

		int pageNum = (int) Math.ceil((double) count / postNum);   // all page number

		int displayPost = (num - 1) * postNum;   // start display post number for each page

		if (num <= 3) {   // set start page and end page for printed
			endPageNum = 5;
			startPageNum = 1;
		} else {
			endPageNum = num + 2;
			startPageNum = num - 2;
		}

		boolean check = (count == 0) ? false : true;   // check if any posts exist

		boolean prev = (num == 1 || count == 0) ? false : true;   // check can move to previous page
		boolean next = (num == pageNum || count == 0) ? false : true;   // check can move to next page

		// to check where current category is
		int curCate = 0;
		if (cateCode.equals("ALL"))
			curCate = 0;
		else if (cateCode.equals("Toy"))
			curCate = 1;
		else if (cateCode.equals("Clothes"))
			curCate = 2;
		else if (cateCode.equals("Fruits"))
			curCate = 3;
		else if (cateCode.equals("Electronics"))
			curCate = 4;
		else if (cateCode.equals("Books"))
			curCate = 5;

		List<GoodsVO> list = null;  // get goods list for category
		list = service.list(displayPost, postNum, cateCode); // get goods list belong to specific category
		for (GoodsVO v : list) {
			v.setGdsPrice(String.format("%,d", Long.parseLong(v.getGdsPrice()))); // transform price
		}
		model.addAttribute("list", list);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("cateCode", cateCode);
		model.addAttribute("count", count);
		model.addAttribute("check", check);
		model.addAttribute("curPage", num);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("curCate", curCate);
		model.addAttribute("pageNum", pageNum); // convey necessary information to view file
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWirte() throws Exception {

	}

	// write post
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWrite(GoodsVO vo, MultipartFile file, HttpServletRequest req, HttpServletResponse res,
			@RequestParam("gdsWriter") String gdsWriter, @RequestParam("gdsPwd") String gdsPwd,
			@RequestParam("gdsDes") String gdsDes, @RequestParam("cateCode") String cateCode,
			@RequestParam("gdsPrice") String gdsPrice) throws Exception {

		String imgUploadPath = uploadPath + File.separator + "imgUpload";
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String fileName = null;

		boolean emp = false;
		// check input box's information is all filled
		if (gdsWriter.isEmpty() || gdsPwd.isEmpty() || gdsDes.isEmpty() || cateCode.isEmpty() || gdsPrice.isEmpty())
			emp = true;
		else
			emp = false;

		if (emp) { // if some field is unfilled print pop-up
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>alert('모든 정보를 입력해주세요'); history.go(-1);</script>");
			out.flush();
			return "redirect:/board/write";   // back to write page
		} else { // all input field is filled
			if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
			} else { // if image is not uploaded print pop-up
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter out = res.getWriter();
				out.println("<script>alert('모든 정보를 입력해주세요'); history.go(-1);</script>");
				out.flush();
				return "redirect:/board/write";   // back to write page
			}
		}

		// all information is filled
		fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);

		vo.setGdsImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
		vo.setGdsThumbImg(
				File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		// store image stored path
		service.write(vo); // add new goods data to database
		return "redirect:/board/listPage?cateCode=" + vo.getCateCode() + "&num=1";   // redirect to first page of current category
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(Model model) throws Exception {

		return "redirect:/board";
	} // go back to home

	// view goods
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public void getView(@RequestParam("gdsNum") int gdsNum,
			@RequestParam(value = "repNum", defaultValue = "0") int repNum, Model model) throws Exception {

		check = false;
		checkReplyWrite = ReplyController.getReplyWrite(); // check if reply write and back to view page

		// check empty filed is exist in reply write
		if (ReplyController.getCheckNull()) {
			check = true;
		} else
			check = false;

		if (!checkViewUpdate && !checkReplyWrite)
			service.updateView(gdsNum); // update view count just when enter view page newly
		else
			checkViewUpdate = false;

		ReplyController.setCheckNull(false);
		ReplyController.setReplyWrite(false);

		GoodsVO vo = service.view(gdsNum); // get goods data
		int repCount = replyService.count(gdsNum); // get goods's reply count

		String cateCode = vo.getCateCode(); // current category

		// to check where current category is
		int curCate = 0;
		if (cateCode.equals("ALL"))
			curCate = 0;
		else if (cateCode.equals("Toy"))
			curCate = 1;
		else if (cateCode.equals("Clothes"))
			curCate = 2;
		else if (cateCode.equals("Fruits"))
			curCate = 3;
		else if (cateCode.equals("Electronics"))
			curCate = 4;
		else if (cateCode.equals("Books"))
			curCate = 5;

		String price = String.format("%,d", Long.parseLong(vo.getGdsPrice())); // transform price

		List<ReplyVO> reply = null;
		reply = replyService.list(gdsNum); // get reply data

		model.addAttribute("reply", reply);
		model.addAttribute("view", vo);
		model.addAttribute("gdsPrice", price);
		model.addAttribute("curCate", curCate);
		model.addAttribute("repCount", repCount);
		model.addAttribute("check", check);   // convey necessary information to view file
	}

	@RequestMapping(value = "/modifycheck", method = RequestMethod.POST)
	public String modifyCheck(@RequestParam("gdsNum") int gdsNum, @RequestParam("inputGdsPwd") String inputGdsPwd,
			@RequestParam(value = "repNum", defaultValue = "0") int repNum, Model model, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		GoodsVO vo = service.view(gdsNum);
		if (inputGdsPwd.equals(vo.getGdsPwd())) { // if input password is correct go to modify page
			return "redirect:/board/modify?cateCode=" + vo.getCateCode() + "&gdsNum=" + vo.getGdsNum();
		} else { // if input password is incorrect print pop-up
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>alert('게시글 비밀번호가 일치하지 않습니다.'); history.go(-1);</script>");
			out.flush();
			return "redirect:/board/view?cateCode=" + vo.getCateCode() + "&gdsNum=" + vo.getGdsNum();
		}

	}

	// modify page
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify(@RequestParam("gdsNum") int gdsNum, Model model) throws Exception {
		GoodsVO vo = service.view(gdsNum);
		String cateCode = vo.getCateCode();
		
		// to check where current category is
		int curCate = 0;
		if (cateCode.equals("ALL"))
			curCate = 0;
		else if (cateCode.equals("Toy"))
			curCate = 1;
		else if (cateCode.equals("Clothes"))
			curCate = 2;
		else if (cateCode.equals("Fruits"))
			curCate = 3;
		else if (cateCode.equals("Electronics"))
			curCate = 4;
		else if (cateCode.equals("Books"))
			curCate = 5;

		model.addAttribute("view", vo);
		model.addAttribute("curCate", curCate);   // convey necessary information to view file
	}

	// modify post
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(@RequestParam("cateCode") String cateCode, GoodsVO vo, MultipartFile file,
			HttpServletRequest req) throws Exception {

		// check it new file if uploaded
		if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
			// delete exist image file
			new File(uploadPath + req.getParameter("gdsImg")).delete();
			new File(uploadPath + req.getParameter("gdsThumbImg")).delete();

			// add new uploaded image file
			String imgUploadPath = uploadPath + File.separator + "imgUpload";
			String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
			String fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(),
					ymdPath);

			vo.setGdsImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);   // set new img path
			vo.setGdsThumbImg(
					File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
			// set new thumb image path
		} else { // if new image is not uploaded
			// new origin image
			vo.setGdsImg(req.getParameter("gdsImg"));
			vo.setGdsThumbImg(req.getParameter("gdsThumbImg"));

		}

		String[] cates = cateCode.split(",");
		cateCode = cates[cates.length - 1];
		vo.setCateCode(cateCode);
		service.modify(vo); // modify data

		checkViewUpdate = false;   

		return "redirect:/board/listPage?cateCode=" + vo.getCateCode() + "&num=1";   // move to current category's first page
	}

	// reply modify
	@RequestMapping(value = "/replymodify", method = RequestMethod.GET)
	public void getReplyModify(@RequestParam("repNum") int repNum, Model model) throws Exception {
		ReplyVO vo = replyService.view(repNum);
		// store modify reply data information
		String repWriter = vo.getRepWriter();
		String repContent = vo.getRepContent();
		String repPwd = vo.getRepPwd();
		int gdsNum = vo.getGdsNum();

		replyService.delete(vo); // delete reply data from database
		int delGdsNum = vo.getGdsNum();
		GoodsVO view = service.view(gdsNum); // get current goods data
		int repCount = replyService.count(gdsNum); // get updated reply count

		List<ReplyVO> reply = null;
		reply = replyService.list(gdsNum); // get updated reply data

		String price = String.format("%,d", Long.parseLong(view.getGdsPrice()));   // transform price

		model.addAttribute("reply", reply);
		model.addAttribute("modify", vo);
		model.addAttribute("repNum", repNum);
		model.addAttribute("repCount", repCount);
		model.addAttribute("view", view);
		model.addAttribute("gdsPrice", price);
		model.addAttribute("delRepWriter", repWriter);
		model.addAttribute("delRepContent", repContent);
		model.addAttribute("delRepPwd", repPwd);
		model.addAttribute("delGdsNum", delGdsNum);   // convey necessary information to view file
	}

	// reply modify post
	@RequestMapping(value = "/replymodify", method = RequestMethod.POST)
	public String postReplyModify(ReplyVO vo) throws Exception {
		replyService.write(vo); // add modified reply newly to database
		GoodsVO gvo = service.view(vo.getGdsNum());

		// redirect to goods view page
		return "redirect:/board/view?cateCode=" + gvo.getCateCode() + "&gdsNum=" + vo.getGdsNum();
	}
   
	// check if can modify reply
	@RequestMapping(value = "/replymodifycheck", method = RequestMethod.POST)
	public String replyModifyCheck(@RequestParam("repNum") int repNum, @RequestParam("gdsNum") int gdsNum,
			@RequestParam("inputRepPwd") String inputRepPwd, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ReplyVO vo = replyService.view(repNum); // get information of reply want to modify
		List<ReplyVO> repList = replyService.list(gdsNum);
		int i=0;
		for (i = 0; i < repList.size(); i++) {
			if(repList.get(i).getRepNum() == repNum)
				break;
		}
		String pwd = vo.getRepPwd(); // get input password

		String[] checkPwd = inputRepPwd.split(",");
		inputRepPwd = checkPwd[i];

		GoodsVO gvo = service.view(vo.getGdsNum());
		if (inputRepPwd.equals(pwd)) { // if correct password go to modify page
			return "redirect:/board/replymodify?repNum=" + repNum + "&gdsNum=" + vo.getGdsNum();
		} else { // if incorrect password back to view page print pop-up
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>alert('댓글 비밀번호가 일치하지 않습니다.'); history.go(-1);</script>");
			out.flush();
			return "redirect:/board/view?cateCode=" + gvo.getCateCode() + "&gdsNum=" + vo.getGdsNum();   // move to view page
		}

	}

	// delete post
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(@RequestParam("gdsNum") int gdsNum, @RequestParam("cateCode") String cateCode)
			throws Exception {
		service.delete(gdsNum); // delete data

		return "redirect:/board/listPage/?cateCode=" + cateCode + "&num=1"; // back to category page
	}

	// check if can delete post
	@RequestMapping(value = "/deletecheck", method = RequestMethod.POST)
	public String deleteCheck(@RequestParam("gdsNum") int gdsNum, @RequestParam("inputGdsPwd") String inputGdsPwd,
			@RequestParam(value = "repNum", defaultValue = "0") int repNum, Model model, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		GoodsVO vo = service.view(gdsNum);
		if (inputGdsPwd.equals(vo.getGdsPwd())) { // if input password is correct delete data
			return "redirect:/board/delete?cateCode=" + vo.getCateCode() + "&gdsNum=" + vo.getGdsNum();
		} else { // if input password is incorrect print pop-up
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<script>alert('게시글 비밀번호가 일치하지 않습니다.'); history.go(-1);</script>");
			out.flush();
			return "redirect:/board/view?cateCode=" + vo.getCateCode() + "&gdsNum=" + vo.getGdsNum();   // redirect to view page
		}

	}

}