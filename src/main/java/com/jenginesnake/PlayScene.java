package com.jenginesnake;

import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Utility.GameMath;
import com.JEngine.Utility.Misc.GameTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class PlayScene extends GameScene {
    public static int width = 16;
    public static int height = 16;
    public static int snakeSize = 32;
    public static SnakeHead snakeParent;
    public static ArrayList<SnakeBody> children = new ArrayList<>();
    public static boolean isPaused;
    public static GameTimer gt;
    public static Apple activeApple;
    public static int score = 1;
    public PlayScene() {
        super("Play Scene");

        // Start resetting variables
        activeApple = null;
        score = 1;
        children = new ArrayList<>();
        snakeParent = new SnakeHead(width/2, height/2);
        gt = new GameTimer(150, args -> snakeParent.moveAllChildren());
        // Finished resetting variables

        add(snakeParent);
        gt.start();
        children.add(snakeParent);
    }

    public static void generateNewApple(){
        int xPos = 0;
        int yPos = 0;

        int tries = width*height;
        boolean allClear = false;
        while (!allClear || tries>0)
        {
            xPos = GameMath.randRangeInclusive(0, width-1);
            yPos = GameMath.randRangeInclusive(0, height-1);
            for (SnakeBody child : children) {
                if (child.getX() != xPos && child.getY() != yPos) {
                    allClear = true;
                }
            }
            tries--;
        }
        activeApple = new Apple(xPos, yPos);
        Main.gameScene.add(activeApple);

    }

    @Override
    public void OnSceneActive(){
        generateNewApple();
    }

    public void endGame(){
        Text gameOverText = new Text("Game Over!\nLength: " + score + "\nPress R to restart");
        gameOverText.setTranslateX(width*snakeSize/2 - gameOverText.getLayoutBounds().getWidth()/2);
        gameOverText.setTranslateY(height*snakeSize/2 - gameOverText.getLayoutBounds().getHeight()/2);
        gameOverText.setFill(Color.WHITE);
        gameOverText.setScaleX(3);
        gameOverText.setScaleY(3);
        addUI(gameOverText);
        isPaused = true;
    }
}
