package com.oracle.oBootMybatis01.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {
	private int currentPage=1;	private int rowPage=10;
	private int pageBlock=10;
	private int start;			private int end;
	private int startPage;		private int endPage;
	private int total;			private int totalPage;
					//42
	public Paging(int total, String currentPage1) {
		this.total = total;
		if(currentPage1 !=null) {
			this.currentPage = Integer.parseInt(currentPage1);
		}
		start=(currentPage -1) * rowPage +1; //시작시 1
		end = start+rowPage -1;              //시작시 10
							//올림ceil 4.2-> 5
		totalPage = (int)Math.ceil((double)total/rowPage); //시작시5페이지
		startPage = currentPage - (currentPage-1) % pageBlock; //시작시1 페이지
		endPage = startPage + pageBlock -1;
		if(endPage>totalPage) {//토탈페이지(5)보다 엔드페이지(10)이크면 엔드페이지에 토탈페이지값넣기
			endPage=totalPage;
		}
	}
}
