package com.pomo.game.poptang.role;

import org.pomo.fast.game.libgdx.constant.enums.TYPE_DIRECTION;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pomo.game.poptang.stages.IArrive;

/**
 * 玩家
 * @author Mr.Po
 *
 */
public class Player extends Biont{
	
	Animation[] behave;
	TextureRegion[][] hero;
	
	TextureRegion currentFrame;
	float time;
	
	IArrive iArrive;
	
	public Player(IArrive iArrive){
		this.iArrive = iArrive;
	}
	
	/**
	 * 初始化玩家
	 * @param path
	 * @param numWidth
	 * @param numHeight
	 */
	public void init(String path,int numWidth,int numHeight){
		

		Texture texture = new Texture(path);

		this.hero = TextureRegion.split(texture,
				texture.getWidth() / numWidth, texture.getHeight() / numHeight);

		behave = new Animation[4];
		behave[0]= new Animation(0.1f, this.hero[3]);
		behave[1]= new Animation(0.1f, this.hero[0]);
		behave[2]= new Animation(0.1f, this.hero[1]);
		behave[3]= new Animation(0.1f, this.hero[2]);
		

		this.currentFrame = this.hero[0][0];
		this.direction[1] = -1;
	}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {

		time += Gdx.graphics.getDeltaTime();

		if(this.isBehave()){//是否采取行动
			move(TYPE_DIRECTION.UP.ordinal());
			move(TYPE_DIRECTION.DOWN.ordinal());
			move(TYPE_DIRECTION.LEFT.ordinal());
			move(TYPE_DIRECTION.RIGHT.ordinal());
		}

		batch.draw(currentFrame, getX(), getY());
	}
	
	/**
	 * 移动
	 * @param index	方向下标
	 */
	private void move(int index){
		
		if(this.direction[index]==0){
			
		}else if(this.direction[index]==-1){
			this.direction[index] = 0;
			this.currentFrame = this.hero[index][0];
		}else{
			this.currentFrame = behave[index].getKeyFrame(time, true);
			
			//判断正向移动还是逆向移动
			int sign = (index==1 || index==2)?-1:1;
			
			float x=getX(),y=getY();
			
			//判断横纵向
			if(index>1){
				x = getX()+this.speed*sign;
			}else{
				y = getY()+this.speed*sign;
			}
			
			//判断该方格是否能够到达
			if(iArrive.isArrive(x, y)){
				setX(x);
				setY(y);
			}
			
		}
		
	}
	
	/**
	 * 行动
	 * @param keycode 行动指令
	 */
	public void action(int keycode) {

		switch (Math.abs(keycode)) {

			case Input.Keys.UP:
				this.direction[0] = Input.Keys.UP/keycode;
				break;
			case Input.Keys.DOWN:
				this.direction[1] = Input.Keys.DOWN/keycode;
				break;
			case Input.Keys.LEFT:
				this.direction[2] = Input.Keys.LEFT/keycode;
				break;
			case Input.Keys.RIGHT:
				this.direction[3] = Input.Keys.RIGHT/keycode;
				break;
		}
		
	}
	
}
