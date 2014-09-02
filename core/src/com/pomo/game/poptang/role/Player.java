package com.pomo.game.poptang.role;

import org.pomo.fast.game.libgdx.constant.enums.TYPE_DIRECTION;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
	
	public Player(float x, float y){
		setX(x);
		setY(y);
	}
	
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
			move();
		}

		batch.draw(currentFrame, getX(), getY());
	}
	
	private void move(int index){
		
		if(this.direction[index]==0){
			
		}else if(this.direction[index]==-1){
			this.direction[index] = 0;
			this.currentFrame = this.hero[index][0];
		}else{
			this.currentFrame = behave[index].getKeyFrame(time, true);
			int sign = (index==1 || index==2)?-1:1;
			if(index>1){
				setX(getX()+this.speed*sign);
			}else{
				setY(getY()+this.speed*sign);
			}
		}
		
	}
	
	private void move(){

		move(TYPE_DIRECTION.UP.ordinal());
		move(TYPE_DIRECTION.DOWN.ordinal());
		move(TYPE_DIRECTION.LEFT.ordinal());
		move(TYPE_DIRECTION.RIGHT.ordinal());
		
	}
	
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
