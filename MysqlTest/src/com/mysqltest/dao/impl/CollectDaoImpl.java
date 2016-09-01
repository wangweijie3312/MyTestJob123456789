package com.mysqltest.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.mysqltest.dao.CollectDao;
import com.mysqltest.pojo.Collect;
import com.mysqltest.utils.ByteFileUtil;
import com.mysqltest.utils.MysqlConnectionUtil;

public class CollectDaoImpl implements CollectDao{

	@Override
	public void getObjById(Long id) {
	}

	@Override
	public List getObjsByObj(Collect collect) {
		return null;
	}

	@Override
	public void updateObj(Collect collect) {
		
	}

	@Override
	public void removeObj(Long id) {
		
	}

	@Override
	public void addObj(Collect collect) {
		String sql = "insert into collect(title,info,vtype) values(?,?,?)";
		Object[] objs = new Object[]{collect.getTitle(),collect.getInfo(),collect.getVtype()};
		MysqlConnectionUtil.operateSql(sql, objs);
	}

	@Override
	public void addObjs(List<Collect> list) {

			Connection conn = null;
			PreparedStatement ps = null;
			
			try
			{
				conn = MysqlConnectionUtil.getJDBCConnnection();
				conn.setAutoCommit(false);
				
				String sql = "insert into collect(title,info,vtype) values(?,?,?)";
				ps = conn.prepareStatement(sql);
				
				Integer listSize = list.size();
				for(int i=0; i<listSize; i++) {
					Collect collect = list.get(i);
					ps.setString(1, collect.getTitle());
					ps.setString(2, collect.getInfo());
					ps.setInt(3, collect.getVtype());
					/*ps.setDate(1,  new Date(bill.getAccountDate().getTime()));
					ps.setString(2, bill.getAccountType());
					
					
					ps.setDouble(3, bill.getAccountMoney());
					ps.setString(4, bill.getAccountPeople());
					ps.setInt(5, BillStatusEnum.Entered.toValue());
					ps.setString(6, bill.getRemarks());
					ps.setString(7, SystemUtility.createUUID());*/
					ps.addBatch();
					
					if(i%5000 == 0) {
						ps.executeBatch();
						conn.commit();
					} else {
						if(i == listSize-1) {
							ps.executeBatch();
							conn.commit();
						}
					}
				}
				
				
			}
			catch(SQLException e)
			{
				throw new RuntimeException("操作失败！原因："+e.getMessage());
			} 
			catch (ClassNotFoundException e) 
			{
				throw new RuntimeException("操作失败！原因："+e.getMessage());
			}
			finally
			{
				MysqlConnectionUtil.closeRes(null, ps, conn);
			}
		
	}

}
