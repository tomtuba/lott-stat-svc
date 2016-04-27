package com.lottstat.cache.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

import com.lottstat.cache.StateCache;
import com.lottstat.converter.Converter;
import com.lottstat.dao.StateDataDAO;
import com.lottstat.dao.impl.DefaultStateDataDAO;
import com.lottstat.entity.State;
import com.lottstat.entity.StateEnum;
import com.lottstat.entity.StateStorage;

// Singleton
public class DefaultStateCache implements StateCache {
	private static DefaultStateCache cache;
	private static Duration expireTime = Duration.ZERO.plusMinutes(15);

	private HashMap<StateEnum, StateStorage> storageBin = new HashMap<StateEnum, StateStorage>();
	private StateDataDAO dao = new DefaultStateDataDAO();

	private DefaultStateCache() {

	}

	public static DefaultStateCache getInstance() {
		if (cache == null) {
			cache = new DefaultStateCache();
		}
		return cache;
	}

	@Override
	public State getState(StateEnum stateEnum) {
		StateStorage stateStorage = storageBin.get(stateEnum);

		LocalDateTime compareTime = LocalDateTime.now().minus(expireTime);

		if (stateStorage == null) {
			// we need to get the state in the storage bin
			stateStorage = buildNewStateStorage(stateEnum);
		} else if (stateStorage.getWhenLastAccessed().isBefore(compareTime)) {
			// we need to dump it -- too old
			stateStorage = buildNewStateStorage(stateEnum);
		}
		return stateStorage.getState();
	}

	private StateStorage buildNewStateStorage(StateEnum stateEnum) {
		StateStorage stateStorage = new StateStorage();
		stateStorage.setWhenLastAccessed(LocalDateTime.now());
		Converter converter = Converter.getInstance(stateEnum);
		String html = dao.getStateData(converter.getURL());

		State state = converter.convertState(html);
		stateStorage.setState(state);
		storageBin.put(stateEnum, stateStorage);
		return stateStorage;
	}

}
