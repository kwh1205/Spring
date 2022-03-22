package com.oracle.oBootDBConnect.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.oracle.oBootDBConnect.domain.Member1;

//@Repository
public class JdbcMemberRepository implements MemberRepository {
	private final DataSource dataSource;
	Connection conn=null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public JdbcMemberRepository(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	private Connection getConnection() {//스프링에서 자동으로 커넥션 해줌 - datasourceutils사용하면됌
		return DataSourceUtils.getConnection(dataSource);
	}
	
	
	
	
	@Override
	public Member1 save(Member1 member1) {
		String sql = "insert into member(id,name) values(member_seq.nextval,?)";
		System.out.println("JdbcMemberRepository sql->"+sql);
		try {
		conn=getConnection();
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, member1.getName());
		pstmt.executeUpdate();
		System.out.println("dbcMemberRepository pstmt.executeupdate ");
		//rs=pstmt.getGeneratedKeys();
		//if(rs.next()) {
		//	member1.setId(rs.getLong(1));
		//}else {
		//	throw new SQLException("id조회 실패");
		//}
		return member1;
		
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}finally {
		close(conn,pstmt,rs);
		}
		}


	@Override
	public List<Member1> findAll() {
		String sql = "select * from member";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			List<Member1> members=new ArrayList<>();
			
			while(rs.next()) {
				Member1 member= new Member1();
				//member = new Member1(rs.getLong("id"),rs.getString("name"))
				member.setId(rs.getLong("id"));
				member.setName(rs.getString("name"));
				members.add(member);
			}
			return members;
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}finally {
			close(conn,pstmt,rs);
		}
		
	
	}

	
	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(pstmt!=null) {
				pstmt.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null) {
				conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void close(Connection conn) throws SQLException {
		DataSourceUtils.releaseConnection(conn, dataSource);
	}
	
	
}
