package com.pomo.game.poptang.stages;

import org.pomo.fast.game.libgdx.util.ListenerUtil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.pomo.game.poptang.PopTangGame;
import com.pomo.game.poptang.listener.MainEnumsClickListener;

/**
 * 主菜单舞台
 * @author Mr.Po
 *
 */
public class MainMenusStage extends Stage{

	PopTangGame game;
	Music bg;
	
	//左边距
	private final float paddingLeft = 100f;
	//上边距
	private float paddingTop = 120;
	
	public MainMenusStage(PopTangGame game){
		this.game = game;
		
		//初始化音乐
		bg = Gdx.audio.newMusic(Gdx.files.internal("music/bg/music-1.ogg"));
		bg.setLooping(true);
		bg.play();
		
		//初始化背景
		Image bg = new Image(new Texture("image/bg/menu.jpg"));
		bg.setSize(getWidth(),getHeight());
		this.addActor(bg);
		
		initButton();
	}
	
	/**
	 * 初始化按钮
	 */
	private void initButton(){
		
		Button drms = addButton("drms");
		Button srms = addButton("srms");
		Button yxsd = addButton("yxsd");
		Button yxsz = addButton("yxsz");
		Button tcyx = addButton("tcyx");
		
		MainEnumsClickListener listener = new MainEnumsClickListener(drms.getWidth(),drms.getHeight(),this.game);
		
		ListenerUtil.binds(listener, drms,srms,tcyx,yxsd,yxsz);
	}
	
	/**
	 * 新增按钮
	 * @param name	按钮名称
	 */
	private Button addButton(String name){
		
		Button btn = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("image/btn/normal/"+name+".png"))), new TextureRegionDrawable(new TextureRegion(new Texture("image/btn/pressed/"+name+".png"))));
		btn.setPosition(this.paddingLeft,getHeight()-this.paddingTop);
		btn.setName(name);
		this.addActor(btn);
		
		this.paddingTop+=70;
		return btn;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		bg.stop();
		bg.dispose();
	}
}
