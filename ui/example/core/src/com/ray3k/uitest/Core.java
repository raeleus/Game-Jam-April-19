package com.ray3k.uitest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Core extends ApplicationAdapter {
	private Skin skin;
	private Stage stage;
	private final static String LONG_TEXT = "I am the perpetrator of lies and deceit and scandal and hate. My favorite color is pooh. And I love you too. Sucker for the babes. You joker. You loser. You game over wannabe has been over the hill across the woods. Prepare the bread by.";
	
	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("ultimate-pillow-fighters.json"));
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		
		Table root = new Table();
		root.setFillParent(true);
		stage.addActor(root);
		
		root.defaults().space(8);
		Label label = new Label("Ultimate\nPillow Fighters", skin, "title");
		label.setAlignment(Align.center);
		root.add(label);
		
		root.row();
		TextButton textButton = new TextButton("Start", skin);
		root.add(textButton);
		textButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Dialog dialog = new Dialog("Window", skin);
				dialog.getTitleLabel().setAlignment(Align.center);
				
				Label label = new Label(LONG_TEXT, skin);
				label.setWrap(true);
				
				ScrollPane scrollPane = new ScrollPane(label, skin);
				dialog.getContentTable().add(scrollPane).size(200);
				
				dialog.show(stage);
			}
		});
		
		root.row();
		textButton = new TextButton("Options", skin);
		root.add(textButton);
		
		root.row();
		Table table = new Table();
		root.add(table);
		
		label = new Label("Name:", skin, "black");
		table.add(label);
		
		TextField textField = new TextField("", skin);
		table.add(textField);
		
		root.row();
		SelectBox<String> selectBox = new SelectBox<String>(skin);
		selectBox.setItems(new Array<String>(new String[]{"Hello", "Goodbye"}));
		root.add(selectBox);
		
		root.row();
		Touchpad touchpad = new Touchpad(0, skin);
		root.add(touchpad);
		
		root.row();
		final ProgressBar progressBar = new ProgressBar(0, 100, 1, false, skin);
		root.add(progressBar).size(236, 66).uniformX();
		
		root.row();
		Slider slider = new Slider(0, 100, 1, false, skin);
		root.add(slider).uniformX().fill();
		slider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				progressBar.setValue(((Slider) actor).getValue());
				
			}
		});
		
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
			dispose();
			create();
		}
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void dispose () {
		stage.dispose();
		skin.dispose();
	}
}
