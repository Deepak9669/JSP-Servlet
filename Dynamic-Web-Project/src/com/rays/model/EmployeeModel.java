package com.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rays.bean.EmployeeBean;
import com.rays.bean.UserBean;
import com.rays.util.JDBCDataSource;

public class EmployeeModel {
	
//	<-------*generate primary key*--------------->
	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from employe1");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
			System.out.println("max id: " + pk);
		}

		conn.close();
		return pk + 1;

	}
	public void add(EmployeeBean bean) throws Exception {

		EmployeeBean existsBean = findByLogin(bean.getLogin());

		if (existsBean != null) {
			throw new Exception("login id already exist");
		}

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into employe1 values(?, ?, ?, ?,?,?)");

		int pk = nextPk();

		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getEmployee());
		pstmt.setString(3, bean.getSalary());
		pstmt.setString(4,bean.getStatus());
		pstmt.setString(5,bean.getLogin());
		pstmt.setString(6,bean.getPassword());
		
		int i = pstmt.executeUpdate();

		System.out.println("data inserted successfully: " + i);
		conn.close();
	}
	public void delete(int id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from employe1 where id = ?");

		pstmt.setInt(1, id);

		int i = pstmt.executeUpdate();
		System.out.println("data deleted successfully: " + i);
		conn.close();
	}
	public void update(EmployeeBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"update employe1 set employee = ?, salary = ?, status = ?,login=?,password=? where id = ?");

		pstmt.setString(1, bean.getEmployee());
		pstmt.setString(2,bean.getSalary());
		pstmt.setString(3,bean.getStatus());
		pstmt.setString(4, bean.getLogin());
		pstmt.setString(5, bean.getPassword());
		pstmt.setInt(6, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("data updated successfully: " + i);
		conn.close();

	}
	public EmployeeBean findByLogin(String login) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from employe1 where login = ?");

		pstmt.setString(1, login);

		ResultSet rs = pstmt.executeQuery();

		EmployeeBean bean = null;
		while (rs.next()) {
			bean = new EmployeeBean();
			bean.setId(rs.getInt(1));
			bean.setEmployee(rs.getString(2));
			bean.setSalary(rs.getString(3));
			bean.setStatus(rs.getString(4));
			bean.setLogin(rs.getString(5));
			bean.setPassword(rs.getString(6));
			

		}

		conn.close();
		return bean;

	}
	public List search(EmployeeBean bean, int pageNo, int pageSize) throws Exception {

		List list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from employe1 where 1 = 1");

		if (bean != null) {
			if (bean.getEmployee() != null && bean.getEmployee().length() > 0) {
				sql.append(" and employee like '" + bean.getEmployee() + "%'");
			}
			if (bean.getStatus() != null && bean.getStatus().length() > 0) {
				sql.append(" and status like '" + bean.getStatus() + "%'");
			}
			

			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" and login like '" + bean.getLogin() + "%'");

			}
			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" and password like '" + bean.getPassword() + "%'");

			}
			if (bean.getId() > 0 && bean.getId() < nextPk()) {
				sql.append(" and id like '" + bean.getId() + "%'");

			}
			

		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);

		}

		Connection conn = JDBCDataSource.getConnection();
		System.out.println("sql ----> " + sql.toString());
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			bean = new EmployeeBean();
			bean.setId(rs.getInt(1));
			bean.setEmployee(rs.getString(2));
			bean.setSalary(rs.getString(3));
			bean.setStatus(rs.getString(4));
			bean.setLogin(rs.getString(5));
			bean.setPassword(rs.getString(6));
		
			list.add(bean);

		}
		conn.close();
		return list;

	}
	public EmployeeBean findById(int id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from employe1 where id = ?");

		pstmt.setInt(1, id);

		ResultSet rs = pstmt.executeQuery();

		EmployeeBean bean = null;
		while (rs.next()) {
			bean = new EmployeeBean();
			bean.setId(rs.getInt(1));
			bean.setEmployee(rs.getString(2));
			bean.setSalary(rs.getString(3));
			bean.setStatus(rs.getString(4));
			bean.setLogin(rs.getString(5));
			bean.setPassword(rs.getString(6));
		

		}

		conn.close();
		return bean;

	}

	

		
}


