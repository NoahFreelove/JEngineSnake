package com.jenginesnake;

import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Utility.Misc.GameTimer;
import com.JEngine.Utility.Misc.GenericMethod;

import java.util.ArrayList;

public class PlayScene extends GameScene {
    public static int width = 16;
    public static int height = 16;
    public static int snakeSize = 32;
    public static SnakeHead snakeParent;
    public static ArrayList<SnakeBody> children = new ArrayList<>();

    public PlayScene() {
        super("Play Scene");
        snakeParent = new SnakeHead(width/2, height/2);
        add(snakeParent);
        new GameTimer(300, args -> snakeParent.moveAllChildren()).start();
        children.add(snakeParent);
    }


    public void endGame(){

    }
}
