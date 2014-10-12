package com.pomo.game.poptang.role;

import org.pomo.fast.game.libgdx.constant.enums.TYPE_DIRECTION;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.math.Vector3;
import com.pomo.game.poptang.stages.GameStage;

/**
 * 玩家
 * @author Mr.Po
 *
 */
public class Player extends Living{
	
	//角色四个方向的动画效果
	Animation[] behave;
	
	//角色静态效果
	TextureRegion[][] hero;
	
	//角色当前展示效果
	TextureRegion currentFrame;
	
	//运动时间
	float time;
	
	//游戏舞台
	GameStage stage;
	
	public Player(GameStage stage){
		super(stage);
		this.stage = stage;
	}
	
	/**
	 * 初始化玩家
	 * @param path
	 * @param numWidth
	 * @param numHeight
	 */
	public void init(String path,int numWidth,int numHeight,String name){
		
		//获取角色的二维图片
		Texture texture = new Texture(path);

		//设置角色的size
		setWidth(texture.getWidth() / numWidth);
		setHeight(texture.getHeight() / numHeight);
		
		//拆分二维图片
		this.hero = TextureRegion.split(texture,(int)this.getWidth()
				,(int)this.getHeight());

		//设置动画
		behave = new Animation[4];
		behave[0]= new Animation(0.1f, this.hero[0]);
		behave[1]= new Animation(0.1f, this.hero[1]);
		behave[2]= new Animation(0.1f, this.hero[2]);
		behave[3]= new Animation(0.1f, this.hero[3]);
		

		this.currentFrame = this.hero[0][0];
		this.direction[1] = -1;
		
		
		//得到玩家地图层
		EllipseMapObject mapObject = (EllipseMapObject)this.stage.getMap().getLayers().get("player").getObjects().get(name);
		
		
		//设置玩家的初始化位置
		this.setX(mapObject.getEllipse().x);
		this.setY(mapObject.getEllipse().y);
		
		this.setX(96);
		this.setY(96);
	}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {

		//尝试向四个方向移动
		move(TYPE_DIRECTION.UP);
		move(TYPE_DIRECTION.DOWN);
		move(TYPE_DIRECTION.LEFT);
		move(TYPE_DIRECTION.RIGHT);

		//判断该方格是否能够到达 && 移动(可能移动位移为0)
		if(this.dme.move()){
			camerafollow();//当产生真实物理位移后，矫正镜头
		}
		
		batch.draw(this.currentFrame, this.getX(), this.getY());
	}
	
	/**
	 * 移动
	 * @param direction	方向枚举
	 */
	private void move(TYPE_DIRECTION direction){
		
		//方向下标
		int index = direction.ordinal();
		
		if(this.direction[index]==0){//保持静止
			
		}else if(this.direction[index]==-1){//即将停止运动
			this.direction[index] = 0;
			this.currentFrame = this.hero[index][0];
		}else{
			
			//递增时间
			this.time += Gdx.graphics.getDeltaTime();
			
			//得到当前运动帧
			this.currentFrame = behave[index].getKeyFrame(time, true);
			
			//判断正向移动还是逆向移动
			int sign = (direction==TYPE_DIRECTION.UP || direction==TYPE_DIRECTION.RIGHT)?1:-1;
			
			float offsetX=0,offsetY=0;
			
			//判断横纵向
			if(direction == TYPE_DIRECTION.UP || direction == TYPE_DIRECTION.DOWN){
				offsetY = this.speed*sign;
			}else{
				offsetX = this.speed*sign;
			}
			
			this.dme.offset(offsetX, offsetY);
			
		}
		
	}
	
	/**
	 * 接收行动指令
	 * @param keycode 行动指令
	 */
	public void action(int keycode) {

		switch (Math.abs(keycode)) {

			case Input.Keys.W:
				this.direction[0] = Input.Keys.W/keycode;
				break;
			case Input.Keys.S:
				this.direction[1] = Input.Keys.S/keycode;
				break;
			case Input.Keys.A:
				this.direction[2] = Input.Keys.A/keycode;
				break;
			case Input.Keys.D:
				this.direction[3] = Input.Keys.D/keycode;
				break;
		}
		
		//System.out.println("direction:{"+direction[0]+","+direction[1]+","+direction[2]+","+direction[3]+"}");
		
	}
	
	/**
	 * 镜头跟随
	 */
	private void camerafollow(){
		
		
		//原始镜头中心点
		Vector3 oldPosition = this.stage.getCamera().position.cpy();
		
		//玩家中心点距离原镜头中心点的偏差量
		Vector3 offsetPosition = new Vector3(getX() - oldPosition.x,getY() - oldPosition.y, 0);
		
		
		//判断相机x边界
		if (oldPosition.x + offsetPosition.x > this.stage.getWidth() / 2
				&& oldPosition.x + offsetPosition.x + this.stage.getWidth() / 2 < this.stage.getMapWidth()) {
			
			this.stage.getCamera().position.x += offsetPosition.x;
			
		}
		
		//判断相机y边界
		if (oldPosition.y + offsetPosition.y > this.stage.getHeight() / 2
				&& oldPosition.y + offsetPosition.y + this.stage.getHeight() / 2 < this.stage.getMapHeight()) {
			
			this.stage.getCamera().position.y += offsetPosition.y;
			
		}
		
	}
	
}
