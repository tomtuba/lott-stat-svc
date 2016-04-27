package com.lottstat.entity;

import java.time.LocalDateTime;

public class StateStorage {
	private State state;
	private LocalDateTime whenLastAccessed;
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public LocalDateTime getWhenLastAccessed() {
		return whenLastAccessed;
	}
	public void setWhenLastAccessed(LocalDateTime whenLastAccessed) {
		this.whenLastAccessed = whenLastAccessed;
	}
}
