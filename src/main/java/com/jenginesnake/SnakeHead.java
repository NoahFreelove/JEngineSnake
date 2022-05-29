package com.jenginesnake;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.SimpleDirection;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import javafx.scene.input.KeyCode;


/**
 * SnakeHead - The main controller of the snake. It is guided by the player and doesn't follow a parent node
 */
public class SnakeHead extends SnakeBody {
    private boolean hasEaten;
    private SimpleDirection dir =  SimpleDirection.UP;
    private SimpleDirection prevDir = dir;

    public SnakeHead(int x, int y) {
        super(x, y, new GameImage("bin/snakeHead.png"));
        setParentNode(this);
    }

    public void eat(){
        hasEaten = true;
        PlayScene.score++;
    }

    // add a new SnakeBody node at the previous position of the last part of the snake
    public void addChild()
    {
        hasEaten = false;
        // get last snake node and create a child at it's previous position
        SnakeBody lastNode = PlayScene.children.get(PlayScene.children.size()-1);

        SnakeBody newChild = new SnakeBody(lastNode.getPrevX(), lastNode.getPrevY(), new GameImage("bin/snakeBody.png"), lastNode);

        PlayScene.children.add(newChild);
        Main.gameScene.add(newChild);
    }

    // Called every game tick, moves all the snake parts to their parent's previous position.
    // The parent node will move in the direction its facing.
    public void moveAllChildren(){
        if(PlayScene.isPaused)
            return;

        moveSelf();

        // Foreach snake-body in the arraylist, move
        for (Object obj: PlayScene.children.toArray()){
            if(obj == null)
                continue;
            if(obj instanceof SnakeBody body)
            {
                if(obj instanceof  SnakeHead)
                    continue;
                body.setX(body.getParentNode().getPrevX());
                body.setY(body.getParentNode().getPrevY());
            }
        }
        // if the snake has eaten since the last game tick, add a new child
        if(hasEaten)
        {
            addChild();
        }
    }

    // move in the direction the snake is facing
    private void moveSelf(){
        int deltaX = 0, deltaY = 0;
        switch (dir)
        {
            case UP -> deltaY = -1;
            case DOWN -> deltaY = 1;
            case LEFT -> deltaX = -1;
            case RIGHT -> deltaX = 1;
        }

        setX(getX() + deltaX);
        setY(getY() + deltaY);
        prevDir = dir;


        // if hit own body, lose the game
        for (Object obj: PlayScene.children.toArray()){
            if(obj == null)
                continue;
            if(obj instanceof SnakeBody body)
            {
                if(obj instanceof SnakeHead)
                    continue;
                if(body.getX() == getX() && body.getY() == getY())
                {
                    Main.gameScene.endGame();
                }
            }
        }

        // if hit outer border, lose the game
        if(getX() < 0 || getX() >= PlayScene.width || getY() < 0 || getY() >= PlayScene.height)
        {
            Main.gameScene.endGame();
        }

        // if ate apple, set hasEaten and destroy the apple
        if(PlayScene.activeApple.getX() == getX() && PlayScene.activeApple.getY() == getY())
        {
            PlayScene.activeApple.eat();
            eat();
            PlayScene.generateNewApple();
        }
    }

    // Logic for turning the snake
    @Override
    public void onKeyPressed(KeyCode keyCode){
        switch (keyCode)
        {
            case A,LEFT -> {
                if(prevDir != SimpleDirection.RIGHT)
                {
                    dir = SimpleDirection.LEFT;
                }
            }
            case S,DOWN -> {
                if(dir != SimpleDirection.UP)
                {
                    dir = SimpleDirection.DOWN;
                }
            }
            case D,RIGHT -> {
                if(prevDir != SimpleDirection.LEFT)
                {
                    dir = SimpleDirection.RIGHT;
                }
            }
            case W,UP -> {
                if(prevDir != SimpleDirection.DOWN)
                {
                    dir = SimpleDirection.UP;
                }
            }
        }

        // Change rotation of head sprite to face the new direction
        switch (dir)
        {
            case UP -> setRotation(new Vector3(0,0,0));
            case DOWN -> setRotation(new Vector3(180,0,0));
            case LEFT -> setRotation(new Vector3(270,0,0));
            case RIGHT -> setRotation(new Vector3(90,0,0));
        }
    }
    //
    @Override
    public void Update(){
        // call super function so the snake's position is updated
        super.Update();

        // if the player manages to fill the grid, let them win.
        if(PlayScene.score == PlayScene.width*PlayScene.height)
        {
            SceneManager.switchScene(new MainMenu());
        }
    }
}
