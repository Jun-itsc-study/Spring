package com.koreait;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.dto.BoardCommentDTO;
import com.koreait.dto.BoardDTO;
import com.koreait.dto.BoardQnaDTO;
import com.koreait.dto.FileDTO;
import com.koreait.dto.MemberDTO;
import com.koreait.service.BoardService;
import com.koreait.service.MemberService;
import com.koreait.service.QnaService;
import com.koreait.vo.PaggingVO;

@Controller
public class MainController {
	private BoardService boardService;
	private MemberService memberService;
	private QnaService qnaService;
	public MainController(BoardService boardService, MemberService memberService, QnaService qnaService) {
		this.boardService = boardService;
		this.memberService = memberService;
		this.qnaService = qnaService;
	}

	@RequestMapping("/")
	public String main(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, Model model) {
//		System.out.println(pageNo);
		List<BoardDTO> list = boardService.selectBoardList(pageNo);
		model.addAttribute("list", list);

		// 페이징 처리
		int count = boardService.selectBoardCount();
		PaggingVO vo = new PaggingVO(count, pageNo, 10, 5);
		model.addAttribute("pagging", vo);

		return "main";
	}

	@RequestMapping("/boardView.do")
	public String boardView(int bno, Model model, HttpSession session) {
		BoardDTO dto = boardService.selectBoardDTO(bno);
		List<FileDTO> flist = boardService.selectFileList(bno);
		List<BoardCommentDTO> comment = boardService.selectCommentDTO(bno);
		// 게시글 조회수 증가
		HashSet<Integer> set = (HashSet<Integer>) session.getAttribute("bno_history");
		if (set == null)
			set = new HashSet<Integer>();

		if (set.add(bno))
			boardService.addBoardCount(bno);
		session.setAttribute("bno_history", set);
		model.addAttribute("board", dto);
		model.addAttribute("flist", flist);
		model.addAttribute("comment", comment);
		return "board_detail_view";
	}

	@RequestMapping("loginView.do")
	public String loginView() {return "login";}

	@RequestMapping("login.do")
	public String login(String id, String pass, HttpSession session) {
		MemberDTO dto = memberService.login(id, pass);
		if (dto != null) {
			session.setAttribute("login", true);
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", dto.getName());
			session.setAttribute("gradeNo", dto.getGradeNo());
			return "redirect:/";
		} else {
			session.setAttribute("login", false);
			return "login";
		}
	}
	
	@RequestMapping("deleteBoard.do")
	public String delete(int bno) {
		List<FileDTO> flist = boardService.selectFileList(bno);
		for(int i=0;i<flist.size();i++) {
			File file = new File(flist.get(i).getPath());
			try {
			if(file.delete())System.out.println("파일 삭제 성공");;
			}catch(Exception e) {System.out.println(e.getMessage());}
		}
		boardService.deleteBoard(bno);
		return "redirect:/";
	}
	
	@RequestMapping("insertComment.do")
	public void insertComment(BoardCommentDTO dto, HttpServletResponse res) {
		int result = boardService.insertComment(dto);
		System.out.println(result);
		try {
			res.getWriter().write(String.valueOf(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//insertComment
	
	@RequestMapping("plusLikeDislike.do")
	public void plusLikeDislike(int bno, int mode, HttpServletResponse res, HttpSession session) {
		int result = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("id", (String)session.getAttribute("id"));
		try {
		if(mode == 0) result = boardService.plusLike(map);
		else result = boardService.plusDislike(map);
		} catch(Exception e) {
			if(mode == 0) boardService.minusLike(map);
			else boardService.minusDislike(map);
		} finally {
			try {
				res.getWriter().write(String.valueOf(result));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}//plusLikeDislike
	
	@RequestMapping("commentLikeDislike.do")
	public String commentLikeDislike(BoardCommentDTO dto, int mode, HttpServletResponse res, HttpSession session) {
		dto.setWriter((String)session.getAttribute("id"));
		try {
		if(mode == 0) boardService.commentPlusLike(dto);
		else boardService.commentPlusDislike(dto);
		} catch(Exception e) {
			if(mode == 0) boardService.commentMinusLike(dto);
			else boardService.commentMinusDislike(dto);
		} 
		return "redirect:/boardView.do?bno="+dto.getBno();
	}//commentLikeDislike
	
	
	@RequestMapping("boardWriteView.do")
	public String boardWriteView() {return "board_write_view";}
	
	@RequestMapping("boardWrite.do")
	public String boardWrite(BoardDTO dto, MultipartHttpServletRequest req) {
		int bno = boardService.boardWrite(dto);
		String root = "c:\\fileUpload\\";
		File userRoot = new File(root);
		if(!userRoot.exists())
			userRoot.mkdirs();
		
		List<MultipartFile> fileList = req.getFiles("file");
		ArrayList<FileDTO> list = new ArrayList<FileDTO>();
		
		for(MultipartFile mf : fileList) {
			String fileName = mf.getOriginalFilename();
//			System.out.println(fileName);
			if(mf.getSize() == 0) continue;
			
			//실제 저장할 경로
			File uploadFile = new File(root + "\\" + fileName);
//			System.out.println(uploadFile.getAbsolutePath());
			try {
				mf.transferTo(uploadFile);
				list.add(new FileDTO(uploadFile, bno, list.size()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(FileDTO file : list) {
			boardService.fileUpload(file);
		}
		
		return "redirect:/boardView.do?bno="+bno;
	}
	
	@RequestMapping("fileDown.do")
	public void fileDown(FileDTO dto, HttpServletResponse response) {
		String path = boardService.fileDown(dto);
		File file = new File(path);
//		String file = path.substring(path.lastIndexOf("\\")+1);
		response.setHeader("Content-Disposition", "attachement;fileName="+file.getName());
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setContentLength((int)file.length());
		
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[10485760];
			while(true) {
				int size = fis.read(buffer);
				if(size == -1) break;
				bos.write(buffer,0,size);
				bos.flush();
			}
			fis.close();
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("imageLoad")
	public void imageLoad(FileDTO dto, HttpServletResponse res) {
		String path = boardService.fileDown(dto);
		try {
			res.getWriter().write(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("registerView.do")
	public String registerView() {return "register";}
	
	@RequestMapping("register.do")
	public String register(MemberDTO dto) {
		memberService.insertMember(dto);
		return "redirect:/loginView.do";
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("memberManageView.do")
	public String memberManage(Model model) {
		List<MemberDTO> list = memberService.selectAllMember();
		model.addAttribute("list",list);
		return "member_manage";
	}
	
	@RequestMapping("memberUpdate.do")
	public void memberUpdate(MemberDTO dto, HttpServletResponse res) {
		int result = memberService.updateMember(dto);
		try {
			res.getWriter().write(String.valueOf(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("memberDelete.do")
	public void memberDelete(String id, HttpServletResponse res) {
		int result = memberService.deleteMember(id);
		try {
			res.getWriter().write(String.valueOf(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("memberSearch.do")
	public ResponseEntity<List<MemberDTO>> memberSerach(String kind, String search) {
		List<MemberDTO> list = memberService.memberSearch(kind, search);
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping("commentDelete.do")
	public String commentDelete(BoardCommentDTO dto) {
		boardService.commentDelete(dto);
		return "redirect:/boardView.do?bno="+dto.getBno();
	}
	
	@RequestMapping("fileUpload.do")
	public void fileUpload(@RequestParam(value="upload")MultipartFile fileload, HttpServletResponse res, HttpSession session) {
		//서버에 파일을 저장할 때에는 파일명을 시간값으로 변경
		//DB에 저장할 때에는 원본파일명과 시간값을 모두 저장
		//filename 저장
		String originFileName = fileload.getOriginalFilename();
		//upload경로 설정
		String root = "c:\\fileupload\\";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		String date = sdf.format(Calendar.getInstance().getTime());
		System.out.println("원본파일 : "+originFileName);
		System.out.println(originFileName.indexOf("."));
		System.out.println(originFileName.substring(originFileName.indexOf(".")+1));
		System.out.println(date);
		
		String fileName = date+"_"+session.getAttribute("id")+originFileName.substring(originFileName.indexOf("."));
		System.out.println(fileName);
		File file = new File(root+"\\"+fileName);
		
		int fno = boardService.insertImage(file.getAbsolutePath());
		try {
			PrintWriter pw = res.getWriter();
			fileload.transferTo(file);
			JSONObject obj = new JSONObject();
			obj.put("uploaded", true);
			obj.put("url", "imageDown.do?fno="+fno);
			pw.write(obj.toString());
		}catch (IOException e) {
			JSONObject obj = new JSONObject();
			obj.put("uploaded", false);
			JSONObject msg = new JSONObject();
			msg.put("message", "파일 업로드 중 에러 발생");
			obj.put("error", msg);
		}
	}
	
	@RequestMapping("imageDown.do")
	public void imageDown(int fno, HttpServletResponse res) {
		String path = boardService.selectImageFile(fno);
		
		File file = new File(path);
		res.setHeader("Content-Disposition", "attachement;fileName="+file.getName());
		res.setHeader("Content-Transfer-Encoding", "binary");
		res.setContentLength((int)file.length());
		
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(res.getOutputStream());
			byte[] buffer = new byte[10485760];
			while(true) {
				int size = fis.read(buffer);
				if(size == -1) break;
				bos.write(buffer,0,size);
				bos.flush();
			}
			fis.close();
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("qnaView.do")
	public String qnaView(HttpSession session, Model model) {
		List<BoardQnaDTO> list = new ArrayList<BoardQnaDTO>();
		int gradeNo = (int) session.getAttribute("gradeNo");
		String writer = (String)session.getAttribute("id");
		if(writer == null) return "redirect:/loginView.do";
		int page = 1;
		String url="board_qna";
		if(gradeNo >= 6) {
			list = qnaService.selectQnaListAll();
			url = "board_qna_list";
		}else {
			list = qnaService.selectQnaList(writer, page);
		}
		model.addAttribute("list",list);
		
		return url;
	}
	
	@RequestMapping("boardQnaWrite.do")
	public String boardQnaWrite(BoardQnaDTO dto, HttpServletResponse res) {
		qnaService.insertQna(dto);
		return "redirect:/qnaView.do";
	}
	
	@RequestMapping("qnaDetail.do")
	public String qnaDetail(int qno, HttpSession session, Model model) {
		BoardQnaDTO dto = qnaService.selectQna(qno);
		if((int)session.getAttribute("gradeNo") >= 6 && dto.getStatus() == 0) {
			qnaService.changeStatus(qno, 1);
		}
		model.addAttribute("dto",dto);
		return "qna_detail";
	}
	@RequestMapping("insertResponse.do")
	public void insertResponse(BoardQnaDTO dto, HttpServletResponse res) {
		int result = qnaService.insertResponse(dto);
		if(result == 1) {
			int r = qnaService.changeStatus(dto.getQno(), 2);
		}
		try {
			res.getWriter().write(String.valueOf(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("qnaMore.do")
	public ResponseEntity<HashMap<String,Object>> qnaMore(int page, HttpSession session) {
		List<BoardQnaDTO> list = new ArrayList<BoardQnaDTO>();
		String id = (String)session.getAttribute("id");
		list = qnaService.selectQnaList(id, page);
		int nextPage = qnaService.selectQnaList(id, page+1).size();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("nextPage", nextPage);
		
		return ResponseEntity.ok(map);
		
	}
}
