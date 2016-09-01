package com.mysqltest.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class ByteFileUtil {

	/**
	 * 创建文件
	 * @param content
	 * @param fileName
	 */
	public static void writeFile(String content, String fileName)
	{	     
		File file = new File(fileName);
		if(file.exists())
			return;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try
		{
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(content.getBytes("ISO-8859-1"));
			bos.flush();
		}
		catch(Exception e)
		{
			throw new RuntimeException("创建文件失败");
		}
		finally
		{
			try{
				if(bos != null)
					bos.close();
			}catch(Exception e)
			{
				throw new RuntimeException("创建文件失败");
			}
			finally
			{
				try{
					if(fos != null)
						fos.close();
				}catch(Exception e)
				{
					throw new RuntimeException("创建文件失败");
				}
			}
		}
	}
	
	/**
	 * 读取文件
	 * @param fileName
	 * @return
	 */
	public static String readFile(String fileName)
	{
		File file = new File(fileName);
		if(!(file.exists()))
			throw new RuntimeException("文件不存在");
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		StringBuilder sb = new StringBuilder();
		try{
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			byte[] b = new byte[1024];
			int length;
			while((length=bis.read(b))!= -1)
			{
//				String s = new String(b,0,length,"ISO-8859-1");
				String s = new String(b,0,length,"UTF-8");
				sb.append(s);
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException("读取文件失败！");
		}
		finally
		{
			try{
				if(bis != null)
					bis.close();
			}
			catch(Exception e)
			{
				throw new RuntimeException("读取文件失败！");
			}
			finally
			{
				try{
					if(fis != null)
						fis.close();
				}
				catch(Exception e)
				{
					throw new RuntimeException("读取文件失败！");
				}
			}
		}
		return sb.toString();
	}
	/**
	 * 获得/**content保存路径 
	 * @param fileName
	 * @return
	 */
	public static String ContentFilePath(){
		String contentFilePath="";
		Properties prop = new Properties();
		InputStream is = ByteFileUtil.class.getClassLoader().getResourceAsStream("FilePath.properties");  
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}  		
		contentFilePath=prop.getProperty("transferFilePath");
		return contentFilePath;
	}
	/**
	 * 获得备份文件路径
	 */
	public static String ContentFilePathBakUp(){
		String contentFilePath="";
		Properties prop = new Properties();
		InputStream is = ByteFileUtil.class.getClassLoader().getResourceAsStream("FilePath.properties");  
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}  		
		contentFilePath=prop.getProperty("transferFileRPathBackUp");
		return contentFilePath;
	}
	/**
	 * 获得当前系统分割符
	 * @param args
	 * @throws Exception
	 * 包含系统属性 file.separator 值的第一个字符。在 UNIX 系统上，此字段的值为 '/'；在 Microsoft Windows 系统上，它为 '\'。
	 */
	public static String getSeparatorpath(){
		
		return java.io.File.separator;
	}
	
	/**
	 * 创建文件目录
	 * @param destDirName
	 * @return
	 */
	 public static boolean createDir(String destDirName) {
	        File dir = new File(destDirName);
	        if (dir.exists()) {
	            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
	            return false;
	        }
	        if (!destDirName.endsWith(File.separator)) {
	            destDirName = destDirName + File.separator;
	        }
	        //创建目录
	        if (dir.mkdirs()) {
	            System.out.println("创建目录" + destDirName + "成功！");
	            return true;
	        } else {
	            System.out.println("创建目录" + destDirName + "失败！");
	            return false;
	        }
	    }
	 /**
	  * 文件删除 
	  * @param filePath
	  * @return
	  */
	 public static boolean deleteFile(String  filePath){
		 File file =new File(filePath);
		 if(!file.exists()){
			 System.out.println("文件不存在"+filePath);			 
			 return false;
		 }
		 if(file.exists()){
			 file.delete();
			 System.out.println("文件已经删除");
		 }
		 return true;
	 }
	public static void main(String[] args) throws Exception {
//		String writeStr="aaaaaaaaaaaaaaaaaaaa21.12asdmASL;DL123>ASDAKLSDM;NKN";
//		writeFile(writeStr,"d:/2012.txt");
//		System.out.println(MD5Utility.getMD5(writeStr));		
		writeFile("aaaaaaaaaaaaaaaaa","d:/test/2012.txt");
//		System.out.println(MD5Utility.getMD5(readFile("d:/2012.txt")));
//		System.out.println(TransferDataTypeEnum.LawPosition.toString());
		System.out.println(deleteFile("d:/133419c34e0794995c18a8dd69d98f1d.txt"));	
		
	}
}
