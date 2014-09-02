package com.pomo.game.poptang.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pomo.game.poptang.PopTangGame;
import com.pomo.game.poptang.role.Player;

public class DanRenGameStage extends Stage{

	PopTangGame game;
	Player player;
	
	public DanRenGameStage(PopTangGame game) {
		this.game = game;
		
		this.player = new Player(this.getWidth()/2,this.getHeight()/2);
		this.player.init("image/slice/P1.png", 4, 4);
		
		this.addActor(this.player);
		
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		this.player.action(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		this.player.action(-keycode);
		return true;
	}
	
}
