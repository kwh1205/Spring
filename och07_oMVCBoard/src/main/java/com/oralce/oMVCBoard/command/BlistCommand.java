package com.oralce.oMVCBoard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.oralce.oMVCBoard.dao.BDao;
import com.oralce.oMVCBoard.dto.BDto;

public class BlistCommand implements BCommand {

	@Override
	public void execute(Model model) {
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list(); 
		System.out.println("BlistCommand dtos.size()-->"+dtos.size());
		model.addAttribute("list",dtos);
		
	}

}
