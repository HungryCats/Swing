package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author HungryCats
 * @date 2023-10-23 08:44:00 
*/
public class JDBCUtil {

	private static final String URL = "jdbc:mysql://localhost:3306/school";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * 对外提供连接的方法
     * @return 返回连接
     */
    public static Connection getConnection() {

        // 线程本地变量中是否存在
        Connection connection = threadLocal.get();

        // 第一次没有
        if (connection == null) {
            // 线程本地变量没有,连接池获取
            try {
				connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
            threadLocal.set(connection);
        }
        return connection;
    }

    public static void freeConnection() {
        Connection connection = threadLocal.get();
        if (connection != null){
            threadLocal.remove(); // 清空线程本地变量
            try {
                connection.setAutoCommit(true); // 事务状态回归 false
                connection.close(); // 回收到连接池即可
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }
}
