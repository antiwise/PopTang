package com.pomo.game.poptang;

import java.util.LinkedList;

import org.pomo.fast.game.libgdx.constant.enums.TYPE_GAME_STATE;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	
	/**
	 * 当前舞台
	 */
	Stage stage;
	
	//屏幕的宽、高
	int width,height;

	BitmapFont font;
	SpriteBatch batch;
	
	@Override
	public void create () {
		
		font = new BitmapFont();
		batch = new SpriteBatch();
		
		STATE = TYPE_GAME_STATE.LOAD;
		stages = new LinkedList<Stage>();
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		//添加一个舞台
		this.stage = new LaunchStage(this);
		addStage(this.stage);
	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		this.stage.act();
		this.stage.draw();
		
		batch.begin();
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
		batch.end();
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	public void addStage(Stage stage) {
		this.stages.add(stage);
	}
	
	public void removeStage(){
		this.stages.remove().dispose();
		this.stage = this.stages.getFirst();
		Gdx.input.setInputProcessor(this.stage);
	}
	
}
