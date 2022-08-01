package com.example;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.dto.MemberDTO;
import com.example.service.MemberService;

@Controller
public class MainConotrller {
	private MemberService service;

	public MainConotrller(MemberService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("/")
	public String main(Model model) {
		//전체 회원정보 읽어와서 출력
		List<MemberDTO> list = service.selectAllMember();
		model.addAttribute("list", list);
		return "main";
	}
	@RequestMapping("/register.do")
	public String register(MemberDTO dto) {
		service.insertMember(dto);
		return "redirect:/";
	}
	
	@RequestMapping("/delete.do")
	public String delete(String id) {
		service.deleteMember(id);
		return "redirect:/";
	}
	
	@RequestMapping("/updateView.do")
	public String updateView(String id, Model model) {
		model.addAttribute("dto",service.selectMember(id));
		return "updateView";
	}
	
	@RequestMapping("/update.do")
	public String update(MemberDTO dto) {
		service.updateMember(dto);
		return "redirect:/";
	}
	
//	@RequestMapping("/search.do")
//	public void	search(String kind, String search, HttpServletResponse response) throws IOException {
//		System.out.println(kind + " " + search);
//		//검색을 수행
//		List<MemberDTO> list = service.searchMember(kind,search);
//		System.out.println(list.size());
//		//검색 결과를 받아서 JSON으로 변환한뒤에 클라이언트에 전송
//		JSONArray json = new JSONArray(list);
//		response.setContentType("text/html;charset=UTF-8");
//		response.getWriter().write(json.toString());
//	}
	@RequestMapping("/search.do")
	public ResponseEntity<List<MemberDTO>> search(String kind, String search) {
		System.out.println(kind + " " + search);
		//검색을 수행
		List<MemberDTO> list = service.searchMember(kind,search);
		System.out.println(list.size());
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping("/file.do")
	public String file() {
		return "file_form";
	}
	
	@RequestMapping("/fileUpload.do")
	public String fileUpload(String writer, MultipartHttpServletRequest request) {
		String root = "c:\\fileUpload\\";
		File userRoot = new File(root);
		if(!userRoot.exists())
			userRoot.mkdirs();
		
		List<MultipartFile> fileList = request.getFiles("file");
		ArrayList<String> list = new ArrayList<String>();
		for(MultipartFile mf : fileList) {
			String fileName = mf.getOriginalFilename();
			System.out.println(fileName);
			if(mf.getSize() == 0) continue;
			
			//실제 저장할 경로
			File uploadFile = new File(root + "\\" + fileName);
			System.out.println(uploadFile.getAbsolutePath());
			try {
				mf.transferTo(uploadFile);
				list.add(fileName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			request.setAttribute("writer", writer);
			request.setAttribute("list",list);
		}
		
		return "file_result";
	}
	
	@RequestMapping("/fileDown.do")
	public void fileDown(String file, HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachement;fileName="+file);
		response.setHeader("Content-Transfer-Encoding", "binary");
		String root = "c:\\fileUpload\\";
		File f = new File(root + file);
		response.setContentLength((int)f.length());
		
		try {
			FileInputStream fis = new FileInputStream(f);
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[1024];
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
}