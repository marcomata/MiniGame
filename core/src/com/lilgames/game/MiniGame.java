package com.lilgames.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.lilgames.game.screens.PlayScreen;

public class MiniGame extends Game {
	public static final int V_WIDTH = 600;
	public static final int V_HEIGHT = 600;
	public static final String TITLE = "Bob's Adventures";
	SpriteBatch batch;

	private static final int FRAME_COLS = 1, FRAME_ROWS = 10;

	// Objects used
	Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
	Texture walkSheet;
	SpriteBatch spriteBatch;

	// A variable for tracking elapsed time for the animation
	float stateTime;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
		//music
		Music music = Gdx.audio.newMusic(Gdx.files.local("music.mp3"));
		music.setLooping(true);

		//music.play();
		ourAnimation("bob_sprites_back_stand.png");
	}

	public void handleInput() {

	}

	public void ourAnimation(String direction) {
		walkSheet = new Texture(Gdx.files.internal(direction));

		// Use the split utility method to create a 2D array of TextureRegions. This is
		// possible because this sprite sheet contains frames of equal size and they are
		// all aligned.
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);

		// Place the regions into a 1D array in the correct order, starting from the top
		// left, going across first. The Animation constructor requires a 1D array.
		TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}

		// Initialize the Animation with the frame interval and array of frames
		walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);

		// Instantiate a SpriteBatch for drawing and reset the elapsed animation
		// time to 0
		spriteBatch = new SpriteBatch();
		stateTime = 0f;
        System.out.println("---------- Animation started of " + direction + "-----------");
	}

	@Override
	public void render () {
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		super.render();

//		handleInput();
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
//		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
//
//		// Get current frame of animation for the current stateTime
//		TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
//		spriteBatch.begin();
//		spriteBatch.draw(currentFrame, bob.getPosX(), bob.getPosY()); // Draw current frame at (50, 50)
//		spriteBatch.end();

	}

	@Override
	public void dispose () {
		spriteBatch.dispose();
		walkSheet.dispose();
	}

	@Override
	public void resume() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resize(int x, int y) {

	}

	public Animation<TextureRegion> getWalkAnimation() {
		return walkAnimation;
	}

	public Texture getWalkSheet() {
		return walkSheet;
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public float getStateTime() {
		return stateTime;
	}
}
