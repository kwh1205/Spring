package com.oralce.oMVCBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oralce.oMVCBoard.dto.BDto;

import scala.inline;

public class BDao {
	DataSource dataSource;
	public BDao() {
		try {
			//JNDI - 컨텍스트의 데이터를찾아서 데이터소스에 넣어줌 - 데이터소스로만 연결가능
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");//context에 키,벨류형식으로 - dataSource에 넣어줌 JNDI라고불림
		} catch (Exception e) {
			System.out.println("생성자 dataSource->"+e.getMessage());
			//e.printStackTrace();
		}
		
	}
	public ArrayList<BDto> list() {
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		
		
		try {
			connection = dataSource.getConnection();
			//mvc_board list 조회
		String query = "select bId,bName,bTitle,bContent,bDate,bHit,"
				+ " bGroup,bStep,bIndent "
				+ " FROM mvc_board order by bGroup desc, bStep asc";
		preparedStatement=connection.prepareStatement(query);
		resultSet=preparedStatement.executeQuery();
		while(resultSet.next()) {
			int bId= resultSet.getInt("bId");
			String bName = resultSet.getString("bName");
			String bTitle = resultSet.getString("bTitle");
			String bContent = resultSet.getString("bContent");
			Timestamp bDate = resultSet.getTimestamp("bDate");
			int bHit = resultSet.getInt("bHit");
			int bGroup = resultSet.getInt("bGroup");
			int bStep = resultSet.getInt("bStep");
			int bIndent = resultSet.getInt("bIndent");
			//BDto dto= new BDto();//초기화 x 인스턴스만 가지는 생성자
			//dto.setbName(bName);//세터로 집어넣는법
			//dto.setbId(bId);
			BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);//생성자 인스턴스만들면서 초기화 같이진행
			
			dtos.add(dto);
		}
		} catch (Exception e) {
			System.out.println("list dataSource SQLException-->"+e.getMessage());
		}finally {
			try {
				if(resultSet !=null)resultSet.close();
				if(preparedStatement !=null)preparedStatement.close();
				if(connection !=null)connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	
		return dtos;
		
		
	}
	public BDto contentView(String strId) {
		//조회수 up
		upHit(strId);
		BDto dto= new BDto();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			//J자바 N네이밍 D디렉토리 I
			connection = dataSource.getConnection();
			//mvc_board bid를 가지고 Bto dto를 담아서 return
			String query ="select * from mvc_board where bId=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strId));
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				dto= new BDto(
						resultSet.getInt("bId"),
						resultSet.getString("bName"),
						resultSet.getString("bTitle"),
						resultSet.getString("bContent"),
						resultSet.getTimestamp("bDate"),
						resultSet.getInt("bHit"),
						resultSet.getInt("bGroup"),
						resultSet.getInt("bStep"),
						resultSet.getInt("bIndent")
				);
			
				
			
			
			}	
		}catch (SQLException e) {
			System.out.println("contentView dataSource-->"+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(resultSet !=null)resultSet.close();
				if(preparedStatement !=null)preparedStatement.close();
				if(connection !=null)connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return dto;
	}
	private void upHit(String strId) {
		BDto dto = new BDto();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = dataSource.getConnection();
			String query = "update mvc_board set bhit=bhit+1 where bid=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(strId));
			
			int rn = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("upHit dataSource-->"+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement !=null)preparedStatement.close();
				if(connection !=null)connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	
		
	}
	public void modify(int bId, String bName, String bTitle, String bContent) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update mvc_board set bName= ?, bTitle = ?, bcontent = ? where bid=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4,bId);	
			int rn = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("update dataSource-->"+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement !=null)preparedStatement.close();
				if(connection !=null)connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		
	}
	
	}
	public void write(String bName, String bTitle, String bContent) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into mvc_board(bId,bName,bTitle,bContent,bHit,bGroup,bStep,bIndent,bDate) values(mvc_board_seq.nextval,?,?,?,0,mvc_board_seq.currval,0,0,sysdate)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			int rn = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("write dataSource-->"+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement !=null)preparedStatement.close();
				if(connection !=null)connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public BDto reply_view(String bId) {
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		BDto dto = null;
		try {
			connection = dataSource.getConnection();
			String query = "select * from mvc_board where bId=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(bId));
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				dto = new BDto(resultSet.getInt("bId"),
						resultSet.getString("bName"),
						resultSet.getString("bTitle"),
						resultSet.getString("bContent"),
						resultSet.getTimestamp("bDate"),
						resultSet.getInt("bHit"),
						resultSet.getInt("bGroup"),
						resultSet.getInt("bStep"),
						resultSet.getInt("bIndent"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement !=null)preparedStatement.close();
				if(connection !=null)connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep,
			String bIndent) {
		//bGroup이 같고 = and bStep> ===>bStep 하나증가
		//홍해 역할 스텝사이를 치고들어가주게함
		replyShape(bGroup,bStep);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query="insert into mvc_board(bId,bName,bTitle,bContent,bGroup,bStep,bIndent) values(mvc_board_seq.nextval,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			//답글에 답글달때는 bindent+1된상태로 들어감 
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bGroup));
			preparedStatement.setInt(5, Integer.parseInt(bStep)+1);//원글에대해서 답글 한수+1 원글에선 0 -- 원본에 또 다른사람이 댓글달면 2 여긴 게속증가
			preparedStatement.setInt(6, Integer.parseInt(bIndent)+1);//원글에 들여쓰기한 수+1 원글에선 0 -- 원본에 또 다른사람이 댓글달아도 1임
			
			int rn=preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement !=null)preparedStatement.close();
				if(connection !=null)connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	private void replyShape(String bGroup, String bStep) {//답글에대한 모양을 어떻게할것인가 , 같은글의 댓글달때는 스탭만늘어나게
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection=dataSource.getConnection();
			//			bgroup =48 		bstep=0       bstep만 매개변수로가져와서 원글에 댓글을 달때마다 +1씩늘어남
			String query ="update mvc_board set bStep=bStep+1"
					+ "where bGroup=? and bStep> ?"; //이건 다른 답변들에게 전달하는거임 - 내가 댓글을 달때 - 다른애들 스탭+1씩하게함
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(bGroup));
			preparedStatement.setInt(2, Integer.parseInt(bStep));
			
			int rn = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement !=null)preparedStatement.close();
				if(connection !=null)connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	public void delete(String bId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "delete from mvc_board where bId=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(bId));
			
			int rn = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement !=null)preparedStatement.close();
				if(connection !=null)connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
