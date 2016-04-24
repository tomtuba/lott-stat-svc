package com.lottstat.cache.impl;

import java.util.HashMap;

import com.lottstat.cache.StateCache;
import com.lottstat.entity.State;
import com.lottstat.entity.StateEnum;
import com.lottstat.entity.StateStorage;

// Singleton
public class DefaultStateCache implements StateCache {
	private static DefaultStateCache cache;
	
	private HashMap<StateEnum, StateStorage> storageBin = new HashMap<StateEnum, StateStorage>();
	
	private DefaultStateCache() {
		
	}
	
	public DefaultStateCache getInstance() {
		if (cache == null) {
			cache = new DefaultStateCache();
		}
		return cache;
	}

	@Override
	public State getState(StateEnum stateEnum) {
		StateStorage stateStorage = storageBin.get(stateEnum);
		
		if (stateStorage == null) {
			// we need to get the state in the storage bin
		}
		return null;
	}

}
