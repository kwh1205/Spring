package com.oralce.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oralce.oMVCBoard.dao.BDao;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request= (HttpServletRequest)map.get("request");
		int bId = Integer.parseInt(request.getParameter("bId"));//dao에서 contentView처럼 받을때 set할때 int타입으로 해도됌
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		BDao dao = new BDao();
		dao.modify(bId,bName,bTitle,bContent);
		

	}

}
