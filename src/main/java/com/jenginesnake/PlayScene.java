package com.jenginesnake;

import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Utility.GameMath;
import com.JEngine.Utility.Misc.GameTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * The PlayScene is the scene which has the actual game
 * Constructing a new one will reset the game as the values are static.
 */
public class PlayScene extends GameScene {

    public static int width = 16;
    public static int height = 16;
    public static int snakeSize = 32;

    public static SnakeHead snakeParent;
    public static Apple activeApple;

    public static ArrayList<SnakeBody> children = new ArrayList<>();

    public static boolean isPaused;
    public static GameTimer moveTimer;

    public static int score = 1;

    public PlayScene() {
        super("Play Scene");

        // Start resetting variables
        activeApple = null;
        score = 1;
        children = new ArrayList<>();
        snakeParent = new SnakeHead(width/2, height/2);
        moveTimer = new GameTimer(150, args -> snakeParent.moveAllChildren());
        // Finished resetting variables

        // Add the snake to the scene
        add(snakeParent);
        moveTimer.start();
        children.add(snakeParent);
    }

    // Try to find a random available position. Have limited attempts so there's no infinite loop
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
        // Add the apple at the found x and y positions
        activeApple = new Apple(xPos, yPos);
        Main.gameScene.add(activeApple);
    }

    // Create a new apple as soon as the game starts
    @Override
    public void OnSceneActive(){
        generateNewApple();
    }

    // Pause game and Show game over text when the snake dies
    public void endGame(){
        Text gameOverText = new Text("Game Over!\nLength: " + score + " squares\nPress R to restart");
        gameOverText.setTranslateX(width*snakeSize/2 - gameOverText.getLayoutBounds().getWidth()/2);
        gameOverText.setTranslateY(height*snakeSize/2 - gameOverText.getLayoutBounds().getHeight()/2);
        gameOverText.setFill(Color.WHITE);
        gameOverText.setScaleX(2);
        gameOverText.setScaleY(2);
        addUI(gameOverText);
        isPaused = true;
    }
}
