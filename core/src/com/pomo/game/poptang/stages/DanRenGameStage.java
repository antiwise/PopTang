package com.pomo.game.poptang.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pomo.game.poptang.PopTangGame;
import com.pomo.game.poptang.role.Player;

/**
 * 单人模式舞台
 * @author Mr.Po
 *
 */
public class DanRenGameStage extends Stage{

	PopTangGame game;
	Player player;
	
	TiledMap map;
	OrthogonalTiledMapRenderer render;
	OrthographicCamera camera;
	
	public DanRenGameStage(PopTangGame game) {
		this.game = game;
		
		initMap();
		
		initPlayer();
		
		Gdx.input.setInputProcessor(this);
	}
	
	/**
	 * 初始化地图
	 */
	private void initMap(){
		map = new TmxMapLoader().load("/map/drms-1.tmx");
		render = new OrthogonalTiledMapRenderer(map, 1/16f);
		camera = new OrthographicCamera();
	    camera.setToOrtho(false, getWidth()/getHeight()*20, 20);
	}
	
	/**
	 * 初始化玩家
	 */
	private void initPlayer(){
		this.player = new Player(this.getWidth()/2,this.getHeight()/2);
		this.player.init("image/slice/P1.png", 4, 4);
		
		this.addActor(this.player);
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
