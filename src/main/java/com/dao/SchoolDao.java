package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.School_information;
import com.utils.JDBCUtil;

/**
 * @author HungryCats
 * @date 2023-10-23 07:34:20
 */
public class SchoolDao {

	public List<School_information> selectAll() {
		Connection connection = JDBCUtil.getConnection();
		String sql = "select * from school_information fir join school_type sec on fir.school_type_id = sec.id";
		List<School_information> list = new ArrayList<School_information>();
		PreparedStatement prepareStatement;
		try {
			prepareStatement = connection.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			while (result.next()) {
				School_information school_information = new School_information();
				school_information.setId(result.getInt("fir.id"));
				school_information.setSchool_name(result.getString("fir.school_name"));
				school_information.setSchool_web_address(result.getString("fir.school_web_address"));
				school_information.setSchool_area(result.getString("fir.school_area"));
				school_information.setSchool_type_id(result.getInt("fir.school_type_id"));
				school_information.setSchool_type(result.getString("sec.school_type"));
				list.add(school_information);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.freeConnection();
		}
		return list;
	}

	public List<School_information> selectByKey(Object value) {
		Connection connection = JDBCUtil.getConnection();
		String sql = "select * from school_information fir join school_type sec on fir.school_type_id = sec.id where school_name like '%"
				+ value + "%'";
		List<School_information> list = new ArrayList<School_information>();
		PreparedStatement prepareStatement;
		try {
			prepareStatement = connection.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			while (result.next()) {
				School_information school_information = new School_information();
				school_information.setId(result.getInt("fir.id"));
				school_information.setSchool_name(result.getString("fir.school_name"));
				school_information.setSchool_web_address(result.getString("fir.school_web_address"));
				school_information.setSchool_area(result.getString("fir.school_area"));
				school_information.setSchool_type_id(result.getInt("fir.school_type_id"));
				school_information.setSchool_type(result.getString("sec.school_type"));
				list.add(school_information);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.freeConnection();
		}
		return list;
	}

	public void delByKey(Object value) {
		Connection connection = JDBCUtil.getConnection();
		String sql = "delete from school_information where id = " + value + "";
		PreparedStatement prepareStatement;
		try {
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.freeConnection();
		}
	}

	public void addData(School_information sInformation) {
		Connection connection = JDBCUtil.getConnection();
		String sql = "insert into school_information (school_name,school_web_address,school_area,school_type_id) values(?,?,?,?)";
		PreparedStatement pStatement;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, sInformation.getSchool_name());
			pStatement.setString(2, sInformation.getSchool_web_address());
			pStatement.setString(3, sInformation.getSchool_area());
			pStatement.setInt(4, sInformation.getSchool_type_id());
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.freeConnection();
		}
	}

	public void updateData(School_information sInformation) {
		Connection connection = JDBCUtil.getConnection();
		String sql = "update school_information set school_name = ?,school_web_address = ?,school_area = ?,school_type_id = ? where id = ? ";
		PreparedStatement pStatement;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, sInformation.getSchool_name());
			pStatement.setString(2, sInformation.getSchool_web_address());
			pStatement.setString(3, sInformation.getSchool_area());
			pStatement.setInt(4, sInformation.getSchool_type_id());
			pStatement.setInt(5, sInformation.getId());
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.freeConnection();
		}
	}

}
