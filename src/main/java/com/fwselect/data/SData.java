package com.fwselect.data;

import java.util.*;

//HashMap 기반 단건 데이터 구조
public class SData extends HashMap{
	
	private static final long serialVersionUID = 1L;

	public SData()	{
	}
	
	public SData(Object obj) {
		super((HashMap)obj);
	}
	
	public SData(HashMap hashmap) {
		super(hashmap);
	}

	public Object get(Object key) {
		Object obj = super.get(key);
		if(obj == null) {
			return "";
		}
		return obj;
	}
	
	public void setString(Object key, String value) {
		super.put(key, value);
	}
	

}
