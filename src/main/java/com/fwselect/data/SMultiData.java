package com.fwselect.data;

import java.util.ArrayList;

//ArrayList 기반 다건 데이터 구조
public class SMultiData extends ArrayList{

	private static final long serialVersionUID = 1L;

	public SMultiData() {
	}
	
	public SMultiData(Object obj) {
		super((ArrayList)obj);
	}
	
	public SMultiData(ArrayList arraylist) {
		super(arraylist);
	}
	
	public SData getDataSet(int index) {
		SData sData = new SData(super.get(index));
				
		return sData;	
	}
	
}
