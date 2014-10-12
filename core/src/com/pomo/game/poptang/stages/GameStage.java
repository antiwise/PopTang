package com.pomo.game.poptang.stages;

import org.pomo.fast.game.libgdx.listener.CollideListener;
import org.pomo.fast.game.libgdx.util.DirectionMoveEntity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pomo.game.poptang.PopTangGame;
import com.pomo.game.poptang.role.Player;

/**
 * 游戏舞台
 * 
 * @author Mr.Po
 *
 */
public abstract class GameStage extends Stage implements CollideListener {

	// 主游戏进程
	PopTangGame game;

	// 背景
	TiledMapTileLayer bg;

	// 障碍物
	TiledMapTileLayer obstacle;

	// 地图
	private TiledMap map;

	// 当前地图的像素宽度，像素高度
	private float mapWidth, mapHeight;

	// 地图绘制工具
	OrthogonalTiledMapRenderer render;

	// 玩家
	Player player;

	public GameStage(PopTangGame game) {

		this.game = game;

		initMap(readMapPath());

		initPlayer();

		Gdx.input.setInputProcessor(this);

	}

	/**
	 * 初始化地图
	 */
	protected void initMap(String path) {

		// 加载地图
		map = new TmxMapLoader().load(path);

		// 得到可以摧毁障碍物图层
		obstacle = (TiledMapTileLayer) this.map.getLayers().get(
				"obstacle");

		bg = (TiledMapTileLayer) this.map.getLayers().get("down-decoration");

		// 创建地图渲染器，并设置比例
		render = new OrthogonalTiledMapRenderer(map, 1);

		// 初始化当前地图的宽、高 总像素
		TiledMapTileLayer layer = (TiledMapTileLayer) this.map.getLayers().get(
				0);
		this.mapWidth = layer.getTileWidth() * layer.getWidth();
		this.mapHeight = layer.getTileHeight() * layer.getHeight();
	}

	/**
	 * 初始化玩家
	 */
	protected void initPlayer() {

		this.player = new Player(this);
		this.player.init("image/slice/P1.png", 6, 4, "P1");

		this.addActor(this.player);

	}

	public boolean collide(DirectionMoveEntity dme) {

		// 目标角色坐标
		float newX = dme.getNewX();
		float newY = dme.getNewY();

		boolean direction = false;

		//目标坐标在地图边界内
		if(newX>0 && newX<this.getMapWidth() - dme.getActor().getWidth() && newY>0 && newY<this.getMapHeight()-dme.getActor().getHeight()){
			
			if(dme.getOffsetX()>0){
				
				newX += dme.getActor().getWidth();
			}
			
			if(dme.getOffsetY()>0){
				newY += dme.getActor().getHeight();
			}
			
			Cell cellDestroible = obstacle.getCell(
					(int) (newX / bg.getTileWidth() ), (int) (newY
							/ bg.getTileHeight() ));
			
			
			
			if(cellDestroible!=null){
				
				Rectangle r1 = new Rectangle((int) (newX / bg.getTileWidth() )*bg.getTileWidth(),(int) (newY
						/ bg.getTileHeight() )* bg.getTileHeight(), bg.getTileWidth(),  bg.getTileHeight());
				
				Rectangle r2 = new Rectangle(newX,newY,dme.getActor().getWidth(),dme.getActor().getHeight());
				
				if(!r1.overlaps(r2)){
					direction = true;
				}
				
			}else
				direction = true;
			
		}

		return direction;

	}

	/**
	 * 读取地图路径(可通过本地数据库，本地文件，服务器等方式读取)
	 * 
	 * @return
	 */
	protected abstract String readMapPath();

	public TiledMap getMap() {
		return map;
	}

	public float getMapWidth() {
		return mapWidth;
	}

	public float getMapHeight() {
		return mapHeight;
	}

}
