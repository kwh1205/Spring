package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;


public class BModifyCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// 1. Map 정의
		Map<String, Object> map = model.asMap();
		// 2. map으로부터 String bId,bName,bTitle, bContent 
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		// 3. DAO선언 , 
		BDao  dao = new BDao();
		// 4. dao.modify(bId, bName, bTitle, bContent);
		dao.modify(bId, bName, bTitle, bContent); 

	}

}
