package com.jenginesnake;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.SimpleDirection;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Utility.ImageProcessing.GenerateSolidTexture;
import javafx.scene.input.KeyCode;

public class SnakeHead extends SnakeBody {
    private boolean hasEaten;
    private SimpleDirection dir =  SimpleDirection.RIGHT;
    private SimpleDirection prevDir = SimpleDirection.RIGHT;
    public SnakeHead(int x, int y) {
        super(x, y, new GameImage("bin/snakeHead.png"));
        setParentNode(this);
    }

    public void eat(){
        hasEaten = true;
        PlayScene.score++;
    }
    public void addChild()
    {
        hasEaten = false;
        SnakeBody lastNode = PlayScene.children.get(PlayScene.children.size()-1);

        /*System.out.println(lastNode);
        System.out.println(PlayScene.children.indexOf(lastNode));*/

        SnakeBody newChild = new SnakeBody(lastNode.getPrevX(), lastNode.getPrevY(), new GameImage("bin/snakeBody.png"), lastNode);
        PlayScene.children.add(newChild);
        if(PlayScene.children.size() == PlayScene.width*PlayScene.height)
        {
            System.out.println("Win!");
        }
        Main.gameScene.add(newChild);
    }

    public void moveAllChildren(){
        if(PlayScene.isPaused)
            return;

        moveSelf();
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


        /*for (Object obj: PlayScene.children.toArray()) {
            if (obj == null)
                continue;
            if (obj instanceof SnakeBody body) {
                System.out.println(body);
            }
        }

        System.out.println("\n\n\n");*/
        if(hasEaten)
        {
            addChild();
        }
    }
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


        // if hit own body
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
        // if hit outer border
        if(getX() < 0 || getX() >= PlayScene.width || getY() < 0 || getY() >= PlayScene.height)
        {
            Main.gameScene.endGame();
        }
        // if ate apple
        if(PlayScene.activeApple.getX() == getX() && PlayScene.activeApple.getY() == getY())
        {
            PlayScene.activeApple.eat();
            eat();
            PlayScene.generateNewApple();
        }
    }

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
    }
    @Override
    public void Update(){
        super.Update();
        switch (dir)
        {
            case UP -> setRotation(new Vector3(0,0,0));
            case DOWN -> setRotation(new Vector3(180,0,0));
            case LEFT -> setRotation(new Vector3(270,0,0));
            case RIGHT -> setRotation(new Vector3(90,0,0));
        }
    }
}
