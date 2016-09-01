package com.mysqltest.pojo;

import java.io.Serializable;

public class Collect implements Comparable<Collect>,Serializable{
	private Long id;
	private String title;
	private String info;
	private Integer vtype;
	
	public Collect(){}
	
	public Collect(String title,String info,Integer vtype)
	{
		this.title = title;
		this.info = info;
		this.vtype = vtype;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getVtype() {
		return vtype;
	}
	public void setVtype(Integer vtype) {
		this.vtype = vtype;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Collect))
		{
			return false;
		}
		Collect c = (Collect)obj;
		if(this.vtype == c.vtype && this.title.equals(c.title))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return (this.title.hashCode() + this.vtype.hashCode());
	}
	
	@Override
	public String toString()
	{
		return "title:"+this.title+";\nvtype:"+this.vtype;
	}
	@Override
	public int compareTo(Collect arg0) {
		return this.vtype.compareTo(arg0.vtype);
	}
	
}
