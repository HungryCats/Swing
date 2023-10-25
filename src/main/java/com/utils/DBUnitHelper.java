package com.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class DBUnitHelper {

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	static String dbname = "school";
	static String username = "root";
	static String password = "123456";

	public static Connection getConn() {
		Connection conn = null;
		try {
			DbUtils.loadDriver("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname + "?characterEncoding=utf-8",
					username, password);
			// serverTimezone=UTC
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static String createDB() {
		Connection connect = null;
		try {
			DbUtils.loadDriver("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "?characterEncoding=utf-8", username,
					password);
			connect.createStatement().execute("CREATE DATABASE  if NOT EXISTS " + dbname + ";");
			return "执行成功";
		} catch (Exception e) {
			return "数据库用户名或密码错误";
		} finally {
			try {
				if (connect != null)
					connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void createTables() {
		File javafile = new File("src//entity");
		File[] files = javafile.listFiles();
		for (int i = 0; i < files.length; i++) {
			String clazzpath = "entity." + files[i].getName().replace(".java", "");
			String tablename = files[i].getName().replace(".java", "").toLowerCase();
			String droptable = "DROP TABLE IF EXISTS `" + tablename + "`;";
			try {
				getConn().createStatement().executeUpdate(droptable);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			StringBuffer sbf = new StringBuffer();
			sbf.append("CREATE TABLE if not exists `" + tablename + "` (");
			List<String> list = getAllColumns(clazzpath);
			for (int j = 0; j < list.size(); j++) {
				if (j == 0) {
					sbf.append("  `" + list.get(j) + "` int(11) NOT NULL auto_increment,");
				} else {
					sbf.append("  `" + list.get(j) + "` varchar(200) default NULL,");
				}
			}
			sbf.append("  PRIMARY KEY  (`" + list.get(0) + "`)");
			sbf.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
			try {
				getConn().createStatement().executeUpdate(sbf.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<String> getAllColumns(String classPath) {
		List<String> list = new ArrayList<>();
		try {
			Class clazz = Class.forName(classPath);
			for (Field field : clazz.getDeclaredFields()) {
				if (!field.getGenericType().getTypeName().equals("byte[]")) {
					list.add(field.getName());
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		DBUnitHelper.createTables();
	}

	public static Integer executeUpdate(String sql, Object... objects) {
		Connection conn = getConn();
		QueryRunner qr = new QueryRunner();
		Integer rtn = 0;
		try {
			if (objects == null) {
				rtn = qr.update(conn, sql);
			} else {
				rtn = qr.update(conn, sql, objects);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rtn;
	}

	public static Integer executeUpdate(String sql) {
		return executeUpdate(sql, null);
	}

	public static <T> List<T> executeQuery(String sql, Class<T> cls, Object... objects) {
		Connection conn = getConn();
		List<T> list = null;
		try {
			QueryRunner rq = new QueryRunner();
			if (objects == null) {
				list = rq.query(conn, sql, new BeanListHandler<T>(cls));
			} else {
				list = rq.query(conn, sql, new BeanListHandler<T>(cls), objects);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static <T> List<T> executeQuery(String sql, Class<T> cls) {
		return executeQuery(sql, cls, null);
	}
}
