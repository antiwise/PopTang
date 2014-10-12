package com.pomo.game.poptang.role;

import org.pomo.fast.game.libgdx.listener.CollideListener;
import org.pomo.fast.game.libgdx.util.DirectionMoveEntity;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 活物(可以移动)
 * @author Mr.Po
 *
 */
public class Living extends Actor{
	
	//能够移动的方向(0:停止,1:前进,-1:撤销)
	protected int[] direction={0,0,0,0};
	
	//速度
	protected float speed = 5;

	//方向移动判断工具
	DirectionMoveEntity dme;
	
	public Living(CollideListener listener){
		this.dme = new DirectionMoveEntity(this,listener);
	}
	
}
