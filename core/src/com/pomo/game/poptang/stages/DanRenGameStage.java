package com.pomo.game.poptang.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pomo.game.poptang.PopTangGame;
import com.pomo.game.poptang.role.Player;

/**
 * 单人模式舞台
 * 
 * @author Mr.Po
 *
 */
public class DanRenGameStage extends Stage implements IArrive{

	//主游戏进程
	PopTangGame game;
	
	//玩家
	Player player;

	//可摧毁的障碍物
	TiledMapTileLayer barrierDestroible;
	
	//不可摧毁的障碍物
	TiledMapTileLayer barrierUnDestroible;
	
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
	private void initMap() {
		
		map = new TmxMapLoader().load("/map/drms-1.tmx");
		
		barrierDestroible = (TiledMapTileLayer)this.map.getLayers().get("barrier-destroible");
		
		barrierUnDestroible = (TiledMapTileLayer)this.map.getLayers().get("barrier-un-destroible");
		
		render = new OrthogonalTiledMapRenderer(map, 1 / 16f);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, getWidth(), getHeight());

		render.setView(camera);
	}

	/**
	 * 初始化玩家
	 */
	private void initPlayer() {
		
		this.player = new Player(this);
		this.player.init("image/slice/P1.png", 4, 4);

		this.addActor(this.player);
		
		RectangleMapObject mapObject = (RectangleMapObject)this.map.getLayers().get("player").getObjects().get("P1");
		
		//设置玩家的初始化位置
		this.player.setX(mapObject.getRectangle().getX());
		this.player.setY(mapObject.getRectangle().getY());
	}

	public void draw() {
		Gdx.gl.glClearColor(0.55f, 0.55f, 0.55f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		render.render();
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

	public boolean isArrive(float x,float y){
		
		Cell cellDestroible = barrierDestroible.getCell((int)(x/barrierDestroible.getTileWidth()), (int)(y/barrierDestroible.getTileHeight()));
		Cell cellUnDestroible = barrierUnDestroible.getCell((int)(x/barrierDestroible.getTileWidth()), (int)(y/barrierDestroible.getTileHeight()));
		
		return cellDestroible==null && cellUnDestroible==null;
		
	}
}
