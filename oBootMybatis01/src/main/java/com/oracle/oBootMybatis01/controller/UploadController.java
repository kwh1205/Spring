package com.oracle.oBootMybatis01.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@GetMapping(value = "upLoadFormStart")
	public String upLoadFormStart(Model model) {
		System.out.println("UploadController upLoadFormStart start");
		return "upLoadFormStart";
	}
	
	@RequestMapping(value = "uploadForm", method = RequestMethod.GET)
	public void uploadForm() {
		System.out.println("uploadForm Get Start");
		System.out.println();
	}
	
	@RequestMapping(value = "uploadForm", method = RequestMethod.POST)
	public String uploadForm(HttpServletRequest request, MultipartFile file1, Model model) throws Exception {//파일은 dto형식이아닌 form화면에서 file로 지정된걸 받기위해서 request형식으로 multipartFile로 받기위해 매개변수에 넣어줌
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");

		System.out.println("uploadForm Post Start");
		logger.info("originalName: "+file1.getOriginalFilename());
		logger.info("size: "+file1.getSize());
		logger.info("contentType : "+file1.getContentType());
		logger.info("uploadPath :" +uploadPath );
		String savedName = uploadFile(file1.getOriginalFilename(),file1.getBytes(),uploadPath);
		logger.info("savedName :"+savedName);
		model.addAttribute("savedName",savedName);
		return "uploadResult";
	}

	private String uploadFile(String originalname, byte[] fileData, String uploadPath) throws Exception{
		UUID uid = UUID.randomUUID();//파일명이 충돌안되게 uuid- 유일한아이디값 을가져옴
		//requestPath = requestPath+"/resources/image";
		System.out.println("uploadPath->"+uploadPath);
		//Directory 생성 - 폴더
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdirs();//실제 폴더가 경로에없으면 만들어줌
			System.out.println("업로드용 폴더 생성: "+uploadPath);
		}
		
		String savedName = uid.toString()+"_"+originalname;//만든폴더에다가 - 이이름 카피
		logger.info("savedName :"+savedName);
		File target = new File(uploadPath,savedName);
		FileCopyUtils.copy(fileData, target);//org.springframework.util.FileCopyUtils -- 톰캣서버에 설치? 몰루
		
		return savedName;
	}
	
	@RequestMapping(value = "uploadFileDelete", method = RequestMethod.GET)//검색시 인스턴스 3개를 컨트롤러에서만들어서 -3개값을 모델로뿌려줌
	public String uploadFileDelete(HttpServletRequest request,Model model) throws Exception{
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		String deleteFile = uploadPath+"5200b18f-8004-4e21-a5e1-1a45e9235d73_고양이21.png";
		logger.info("deleteFile :"+deleteFile);
		System.out.println("uploadFileDelete get start..");
		int delResult = upFileDelete(deleteFile);
		logger.info("deleteFile result->"+delResult);
		model.addAttribute("deleteFile",deleteFile);
		model.addAttribute("delResult",delResult);

		return "uploadResult";
		
	}

	private int upFileDelete(String deleteFile) throws Exception{
		int result = 0;
		logger.info("upFileDelete result->"+deleteFile);
		File file = new File(deleteFile);
		if(file.exists()) {
		  if(file.delete()) {
			 System.out.println("파일삭제 성공");
			 result=1;
		  }else {
			  System.out.println("파일삭제실패");
			  result=0;
		  }
		}else {
			System.out.println("파일이 존재하지않습니다");
			result= -1;
		}
		return result;
	}
}
