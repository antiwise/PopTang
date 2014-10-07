package com.pomo.game.poptang.role;

import org.pomo.fast.game.libgdx.util.DirectionMoveUtil;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Living extends Actor{
	
	//能够移动的方向(0:停止,1:前进,-1:撤销)
	protected int[] direction={0,0,0,0};
	
	//速度
	protected float speed = 5;

	//方向移动判断工具
	DirectionMoveUtil directionMove = new DirectionMoveUtil(this);
}
