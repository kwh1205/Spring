package com.oralce.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oralce.oMVCBoard.dao.BDao;
import com.oralce.oMVCBoard.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {//모델에 리퀘스트포함
		Map<String, Object> map = model.asMap();//모델에있는거 맵형식으로 뽑음 asMap()
		HttpServletRequest request=(HttpServletRequest) map.get("request");//키로된 request를 뽑으면 값인 request사용가능 - 컨트롤러에서 집어넣어줌
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		BDto dto = dao.contentView(bId);
		
		model.addAttribute("mvc_board",dto);
		System.out.println("BContentCommand 실행");
	}

}
