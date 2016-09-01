package com.mysqltest.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class MysqlConnectionUtil {
	private static String url = "jdbc:mysql://127.0.0.1:3306/mytestsql";
	private static String user = "root";
	private static String password = "15810771623";
	
	static
	{
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 单例模式
	 */
	private MysqlConnectionUtil(){}

	/**
	 * 创建连接
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getJDBCConnnection() throws ClassNotFoundException, SQLException {
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	
	/**
	 * 关闭资源
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void closeRes(ResultSet rs, Statement st, Connection conn)
	{
		try 
		{
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally
			{
				try {
					if(conn != null)
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Integer getQueryCount(Connection connection, String sql) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			Integer count = 0;
			
			while(rs.next()){
				count = rs.getInt(1);
			}
					
			return count;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		finally
		{
			closeRes(rs, ps, connection);
		}
	}
	
	public static Integer getModifyCount(Connection connection, String sql) {
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(sql);
			Integer count = ps.executeUpdate();
			ps.close();
			
			return count;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 对数据库进行相关操作,无返回结果
	 * @param sql
	 * @param objs
	 */
	public static void operateSql(String sql, Object[] objs) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			conn = getJDBCConnnection();
			ps = conn.prepareStatement(sql);
			for(int i=0; i<objs.length; i++)
			{
				ps.setObject(i+1, objs[i]);
			}
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("操作失败！");
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("操作失败！");
		}
		finally
		{
			closeRes(null, ps, conn);
		}
		
	}
	
	
}
