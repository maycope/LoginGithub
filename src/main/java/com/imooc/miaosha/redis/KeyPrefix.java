package com.imooc.miaosha.redis;

public interface KeyPrefix {
		
	 int expireSeconds();
	
	 String getPrefix();
	
}
