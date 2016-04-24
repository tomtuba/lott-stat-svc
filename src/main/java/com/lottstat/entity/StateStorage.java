package com.lottstat.entity;

import java.time.LocalDateTime;

public class StateStorage {
	private State state;
	private LocalDateTime lastAccessed;
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public LocalDateTime getLastAccessed() {
		return lastAccessed;
	}
	public void setLastAccessed(LocalDateTime lastAccessed) {
		this.lastAccessed = lastAccessed;
	}
}
