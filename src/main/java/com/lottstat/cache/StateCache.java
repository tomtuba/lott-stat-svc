package com.lottstat.cache;

import com.lottstat.entity.State;
import com.lottstat.entity.StateEnum;

public interface StateCache {
	public State getState(StateEnum stateEnum);

}
