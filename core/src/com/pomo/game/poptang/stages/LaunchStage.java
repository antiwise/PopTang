package com.pomo.game.poptang.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pomo.game.poptang.PopTangGame;

public class LaunchStage extends Stage{
	
	public LaunchStage(PopTangGame game){
		Image bg = new Image(new Texture("data/bg/start.jpg"));
		bg.setSize(getWidth(),getHeight());
		this.addActor(bg);
	}
	
}
