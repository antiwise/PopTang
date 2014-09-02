package com.pomo.game.poptang.listener;

import org.pomo.fast.game.libgdx.listener.OnClickListener;

import com.pomo.game.poptang.PopTangGame;
import com.pomo.game.poptang.stages.DanRenGameStage;

public class MainEnumsClickListener extends OnClickListener{

	PopTangGame game;

	public MainEnumsClickListener(float width, float height,PopTangGame game){
		super(width,height);
		this.game = game;
	}

	@Override
	protected void onClick(float x, float y,String name) {
		
		if("drms".equals(name)){
			drms();
		}
		
	}
	
	private void drms(){
		
		DanRenGameStage stage = new DanRenGameStage(this.game);
		
		this.game.addStage(stage);
		this.game.removeStage();
	}

}
