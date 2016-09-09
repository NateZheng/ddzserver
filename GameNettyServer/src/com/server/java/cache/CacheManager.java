package com.server.java.cache;

import com.server.java.cache.redis.CacheRedisImp;

public class CacheManager {

	public static ICache getCache() {
		return CacheRedisImp.getInstance();
	}

	public static void releaseCache(ICache cache) {
		CacheRedisImp.releaseCache((CacheRedisImp) cache);
	}
}
