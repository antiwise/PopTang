package com.pomo.game.poptang;

import java.util.LinkedList;

import org.pomo.fast.game.libgdx.constant.enums.TYPE_GAME_STATE;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pomo.game.poptang.stages.LaunchStage;

public class PopTangGame extends ApplicationAdapter {
	
	/**
	 * 当前游戏的状态
	 */
	public TYPE_GAME_STATE STATE;
	
	/**
	 * 当前游戏舞台列表
	 */
	LinkedList<Stage> stages;
	
	int width,height;
	
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
		STATE = TYPE_GAME_STATE.LOAD;
		stages = new LinkedList<Stage>();
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		stages.add(new LaunchStage(this));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		
		
		
		stages.getLast().act();
		stages.getLast().draw();
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
}
