package com.pomo.game.poptang.stages;

public interface IArrive {

	/**
	 * 是否允许到达(碰撞检测)
	 * @param x
	 * @param y
	 * @return
	 */
	boolean isArrive(float x,float y);
	
}
