package com.lilgames.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lilgames.game.Char;
import com.lilgames.game.MiniGame;
import java.util.*;

/**
 * Created by marcomata on 09.04.18.
 */

public class PlayScreen implements Screen {

    private MiniGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    char keychar = ' ';
    boolean standingAni = false;

    Map<String,TiledMapTile> treeTiles;

    Char bob = new Char();

    public PlayScreen(MiniGame game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        // change this to switch up how the camera acts on resizing
        gamePort = new FitViewport(MiniGame.V_WIDTH, MiniGame.V_HEIGHT, gamecam);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Map_D.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(MiniGame.V_WIDTH * 2, MiniGame.V_HEIGHT * 2, 0);
        //DRAG MAP
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                float x = Gdx.input.getDeltaX();
                float y = Gdx.input.getDeltaY();
                gamecam.translate(-x, y);
                return true;
            }
        });
        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        MapLayer layer = map.getLayers().get(2);

        MapObjects objects = layer.getObjects();
        for(int i = 0; i < objects.getCount(); i++) {
            System.out.println("COUNT: "+i);
        }

        /*for (Iterator<Object> iter = objects.get(0).getProperties().getValues(); iter.hasNext();) {
            String name = (String) iter.next();
            System.out.println(name);
        }*/

        /*treeTiles = new HashMap<String,TiledMapTile>();

        for(TiledMapTile tile:tileSet){
            Object property = tile.getProperties().get("tree");
        }*/



        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;




    }

    /*// camera updating
    public void handleInput(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            //running = true;
            System.out.println("W PRESSED");
            if(running != 'w') {
                running = 'w';
                game.ourAnimation("bob_sprites_back.png");
            }
            bob.setPosY(bob.getPosY()+ 1);
           // running = ' ';
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if(running != 'a') {
                running = 'a';
                game.ourAnimation("bob_sprites_left.png");
            }
            bob.setPosX(bob.getPosX()-1);
           // running = ' ';
        } else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            if(running != 's') {
                running = 's';
                game.ourAnimation("bob_sprites_front.png");
            }
            System.out.println("S PRESSED");

            bob.setPosY(bob.getPosY()-1);
            //running = ' ';
        } else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            if(running != 'd') {
                running = 'd';
                game.ourAnimation("bob_sprites_right.png");
            }
            System.out.println("D PRESSED");
            bob.setPosX(bob.getPosX()+1);
            //running = ' ';
        }

    }*/

    public void handleInput(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            standingAni = true;
            if(keychar != 'w') {
                keychar =  'w';
                game.ourAnimation("bob_sprites_back.png");
            } else {

            }

            System.out.println("W PRESSED");

            bob.setPosY(bob.getPosY() + 1);

        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            standingAni = true;
            if(keychar != 'a') {
                keychar = 'a';
                game.ourAnimation("bob_sprites_left.png");
            } else {

            }
            bob.setPosX(bob.getPosX() - 1);

        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            standingAni = true;
            if(keychar != 's') {
                keychar = 's';
                game.ourAnimation("bob_sprites_front.png");
            } else {

            }

            System.out.println("S PRESSED");
            bob.setPosY(bob.getPosY() - 1);
            //running = ' ';
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            standingAni = true;
            if(keychar != 'd') {
                keychar =  'd';
                game.ourAnimation("bob_sprites_right.png");
            } else {

            }
            System.out.println("D PRESSED");
            bob.setPosX(bob.getPosX() + 1);
            //running = ' ';
        } else {
            if(keychar == 'w') {
                if(standingAni) {
                game.ourAnimation("bob_sprites_back_stand.png");
                standingAni = false;
                }
            } else if(keychar == 'a') {
                if(standingAni) {
                    game.ourAnimation("bob_sprites_left_stand.png");
                    standingAni = false;
                }
            } else if(keychar == 's') {
                if(standingAni) {
                    game.ourAnimation("bob_sprites_front_stand.png");
                    standingAni = false;
                }
            } else if(keychar == 'd') {
                if(standingAni) {
                    game.ourAnimation("bob_sprites_right_stand.png");
                    standingAni = false;
                }
            }
        }

    }

    public void update(float dt) {
        handleInput(dt);
        gamecam.update();
        renderer.setView(gamecam);





    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {
        update(dt);
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput(dt);
        // Get current frame of animation for the current stateTime
        TextureRegion currentFrame = game.getWalkAnimation().getKeyFrame(game.getStateTime(), true);
        renderer.render();
        game.getSpriteBatch().begin();
        game.getSpriteBatch().draw(currentFrame, bob.getPosX(), bob.getPosY(), 100, 100); // Draw current frame at (50, 50)
        game.getSpriteBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}