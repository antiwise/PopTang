package com.pomo.game.poptang.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pomo.game.poptang.PopTangGame;
import com.pomo.game.poptang.role.Player;

/**
 * 游戏舞台
 * 
 * @author Mr.Po
 *
 */
public abstract class GameStage extends Stage{

	// 主游戏进程
	PopTangGame game;

	//背景
	TiledMapTileLayer bg;
	
	// 可摧毁的障碍物
	TiledMapTileLayer barrierDestroible;

	// 不可摧毁的障碍物
	TiledMapTileLayer barrierUnDestroible;

	//地图
	private TiledMap map;
	
	//当前地图的像素宽度，像素高度
	private float mapWidth,mapHeight;
	
	//地图绘制工具
	OrthogonalTiledMapRenderer render;

	//玩家
	Player player;
	
	public GameStage(PopTangGame game){
		
		this.game = game;
		
		initMap(readMapPath());
		
		initPlayer();
		
		Gdx.input.setInputProcessor(this);
		
	}
	
	/**
	 * 初始化地图
	 */
	protected void initMap(String path) {

		//加载地图
		map = new TmxMapLoader().load(path);

		//得到可以摧毁障碍物图层
		barrierDestroible = (TiledMapTileLayer) this.map.getLayers().get(
				"barrier-destroible");

		//得到不可摧毁障碍物图层
		barrierUnDestroible = (TiledMapTileLayer) this.map.getLayers().get(
				"barrier-un-destroible");

		bg = (TiledMapTileLayer) this.map.getLayers().get(
				"ground");
		
		//创建地图渲染器，并设置比例
		render = new OrthogonalTiledMapRenderer(map, 1);

		//初始化当前地图的宽、高 总像素
		TiledMapTileLayer layer = (TiledMapTileLayer)this.map.getLayers().get(0);
		this.mapWidth = layer.getTileWidth()*layer.getWidth();
		this.mapHeight = layer.getTileHeight()*layer.getHeight();
	}
	
	
	/**
	 * 初始化玩家
	 */
	protected void initPlayer() {
		
		this.player = new Player(this);
		this.player.init("image/slice/P1.png", 6, 4,"P1");

		this.addActor(this.player);
		
	}
	/**
	 * 是否允许到达(碰撞检测)
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isArrive(float x,float y){
		
		//System.out.println("玩家当前坐标：("+this.player.getX()+","+this.player.getY()+")");
		//System.out.println("玩家目的坐标：("+x+","+y+")");
		
		if(x<0 || x> this.getMapWidth()-this.player.getWidth() || y<0 || y>this.getMapHeight()-this.player.getWidth())	return false;
		
		Cell cellDestroibleLeft = barrierDestroible.getCell((int)(x/bg.getTileWidth()+0.5), (int)(y/bg.getTileHeight()+0.5));
		Cell cellUnDestroibleLeft = barrierUnDestroible.getCell((int)(x/bg.getTileWidth()+0.5), (int)(y/bg.getTileHeight()+0.5));
		
		x+=this.player.getWidth();
		y+=this.player.getHeight();
		
		Cell cellDestroibleRight = barrierDestroible.getCell((int)(x/bg.getTileWidth()+0.5), (int)(y/bg.getTileHeight()+0.5));
		Cell cellUnDestroibleRight = barrierUnDestroible.getCell((int)(x/bg.getTileWidth()+0.5), (int)(y/bg.getTileHeight()+0.5));
		
		return cellDestroibleLeft==null && cellUnDestroibleLeft==null && cellDestroibleRight==null && cellUnDestroibleRight==null;
		
	}
	
	/**
	 * 读取地图路径(可通过本地数据库，本地文件，服务器等方式读取)
	 * @return
	 */
	protected abstract String readMapPath();
	
	public TiledMap getMap() {return map;}

	public float getMapWidth() {return mapWidth;}

	public float getMapHeight() {return mapHeight;}
	
}
