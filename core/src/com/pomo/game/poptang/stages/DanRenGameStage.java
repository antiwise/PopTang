package com.pomo.game.poptang.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.pomo.game.poptang.PopTangGame;

/**
 * 单人模式舞台
 * 
 * @author Mr.Po
 *
 */
public class DanRenGameStage extends GameStage{

	public DanRenGameStage(PopTangGame game) {
		super(game);
		
	}

	@Override
	protected String readMapPath() {
		return "map/XQ-1.tmx";
	}
	
	public void draw() {
		
		Gdx.gl.glClearColor(0.55f, 0.55f, 0.55f, 1f);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.render.setView((OrthographicCamera) getCamera());
		this.render.render();
		this.getCamera().update();
		
		super.draw();
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
