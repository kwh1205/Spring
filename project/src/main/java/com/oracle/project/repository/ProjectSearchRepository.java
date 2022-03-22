package com.oracle.project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.oracle.project.domain.Tour;

@Repository
public class ProjectSearchRepository implements ProjectRepository {
	private final DataSource dataSource;
	Connection conn=null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ProjectSearchRepository(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	private Connection getConnection() {//스프링에서 자동으로 커넥션 해줌 - datasourceutils사용하면됌
		return DataSourceUtils.getConnection(dataSource);
	}
	
	@Override
	public List<Tour> search() {
		String sql = "select tour_id,category,tour_do,tour_title,tour_content,tour_phone,si_name from tour where category=? and tour_do=? ";
		String sqls ="select tour_id,category,tour_do,tour_title,tour_content,tour_phone,si_name from tour where tour_do=? order by category";
		try {
			Tour tour= new Tour();
			conn=getConnection();
			if(tour.getTour_do() !=null&& tour.getCategory() !=null) {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,tour.getCategory());
			pstmt.setString(2, tour.getTour_do());
			rs=pstmt.executeQuery();
			}else if(tour.getTour_do() !=null && tour.getCategory() ==null) {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, tour.getTour_do());
			rs=pstmt.executeQuery();
			
			}
				List<Tour> tours=new ArrayList<>();
			
			while(rs.next()) {
				tour= new Tour();
				tour.setTour_id(rs.getLong(1));
				tour.setCategory(rs.getString(2));
				tour.setTour_do(rs.getString(3));
				tour.setTour_title(rs.getString(4));
				tour.setTour_content(rs.getString(5));
				tour.setTour_phone(rs.getString(6));
				tour.setSi_name(rs.getString(7));
				tours.add(tour);
			}
			return tours;
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

}
