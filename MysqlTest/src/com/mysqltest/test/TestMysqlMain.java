package com.mysqltest.test;

import java.util.ArrayList;
import java.util.List;

import com.mysqltest.dao.CollectDao;
import com.mysqltest.dao.impl.CollectDaoImpl;
import com.mysqltest.pojo.Collect;
import com.mysqltest.utils.ByteFileUtil;

public class TestMysqlMain {
	public static void main(String[] args) throws Exception{
//		CollectDao dao = new CollectDaoImpl();
//		Collect collect = new Collect("title","info",1);
//		dao.addObj(collect);
		/*String title = "测试的风格title是否能够读取所有的title呢，大家一起来看看";
		String info = ByteFileUtil.readFile("E:\\WWJ_COM_WINchannel\\news.txt");
		Integer vtype = new Integer(1);
		List<Collect> list = new ArrayList<Collect>();
		for(int i=0;i<100001;i++)
		{
			Collect collect = new Collect(title,info,vtype);
			list.add(collect);
		}
		dao.addObjs(list);*/
		Collect collect = new Collect("title","info",1);
		new Thread(new Runnable(
				) {
					@Override
					public void run() {
						changeMss(collect);
					}
				}).start();
//		Thread.currentThread().sleep(2000);
		System.out.println(collect.getTitle());
	}
	
	public static void changeMss(Collect collect)
	{
		collect.setTitle("你好");
	}
	
	/*public static void testAdd()
	{
		
	}*/
}

