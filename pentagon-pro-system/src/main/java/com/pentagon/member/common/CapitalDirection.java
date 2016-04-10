package com.pentagon.member.common;

public enum CapitalDirection {
	
	IN(1,"转入"),OUT(2,"转入"),FREEZE(3,"冻结"),THAW(4,"解冻"),THAW_OUT(5,"解冻并转出");
	
	private int code;
	private String desc;
	
	private CapitalDirection(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}
	
}
