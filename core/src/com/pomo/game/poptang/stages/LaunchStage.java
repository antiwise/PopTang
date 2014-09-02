package com.pomo.game.poptang.stages;

import org.pomo.fast.game.libgdx.constant.enums.TYPE_GAME_STATE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pomo.game.poptang.PopTangGame;

/**
 * 初始化运舞台
 * @author Mr.Po
 *
 */
public class LaunchStage extends Stage{
	
	PopTangGame game;
	
	public LaunchStage(PopTangGame game){
		this.game = game;
		Image bg = new Image(new Texture("image/bg/start.jpg"));
		bg.setSize(getWidth(),getHeight());
		this.addActor(bg);
		
		new Thread(new Runnable() {
			
			public void run() {
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				Gdx.app.postRunnable(new Runnable(){

					@Override
					public void run() {
						
						Stage stage = initMainMenusStage();
						//切换游戏舞台，切换游戏状态	-->主菜单
						LaunchStage.this.game.addStage(stage);
						LaunchStage.this.game.removeStage();
						LaunchStage.this.game.STATE = TYPE_GAME_STATE.MENU;
					}
					
				});
				
				
			}
			
		}).start();
		
	}
	
	/**
	 * 初始化主菜单舞台
	 * @return
	 */
	private Stage initMainMenusStage(){
		
		MainMenusStage stage = new MainMenusStage(this.game);
		
		return stage;
	}
	
}
