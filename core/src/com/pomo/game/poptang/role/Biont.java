package com.pomo.game.poptang.role;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Biont extends Actor{

	//能够移动的方向(0:停止,1:前进,-1:撤销)
	protected int[] direction={0,-1,0,0};
	
	//速度
	protected float speed = 5;
	
	protected boolean isBehave(){
		return this.direction[0]+this.direction[1]+this.direction[2]+this.direction[3]!=0;
	}
}
