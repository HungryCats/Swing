package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.utils.JDBCUtil;

/**
 * @author HungryCats
 * @date 2023-10-24
 */
public class CoverageData {

	public Integer selectPie(Integer value) {
		Connection connection = JDBCUtil.getConnection();
		String sql = "select count(*) from status_exception_manage where state =" + value;
		Integer num = 0;
		PreparedStatement prepareStatement;
		try {
			prepareStatement = connection.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			if (result.next()) {
				num = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.freeConnection();
		}
		return num;
	}
	
	public Map<String,List> selectLine() {
		Connection connection = JDBCUtil.getConnection();
		String sql = "select teacher_name,(duty_time + overtime_hours) from attendance_view" ;
		List<String> names = new ArrayList<String>();
		List<Integer> times = new ArrayList<Integer>();
		Map<String,List> map = new HashMap<String,List>();
		PreparedStatement prepareStatement;
		try {
			prepareStatement = connection.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			while (result.next()) {
				names.add(result.getString(1));
				times.add(result.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.freeConnection();
		}
		map.put("names", names);
		map.put("times", times);
		return map;
	}
}
