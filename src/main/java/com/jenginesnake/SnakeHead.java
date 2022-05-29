package com.jenginesnake;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Position.SimpleDirection;
import com.JEngine.Utility.ImageProcessing.GenerateSolidTexture;
import javafx.scene.input.KeyCode;

public class SnakeHead extends SnakeBody {
    private boolean hasEaten;
    private SimpleDirection dir =  SimpleDirection.RIGHT;

    public SnakeHead(int x, int y) {
        super(x, y, new GameImage(GenerateSolidTexture.generateImage(PlayScene.snakeSize, PlayScene.snakeSize, 0xFF90EE90)));
        setParentNode(this);
    }

    public void eat(){
        hasEaten = true;
    }
    public void addChild()
    {
        hasEaten = false;
        SnakeBody lastNode = PlayScene.children.get(PlayScene.children.size()-1);

        System.out.println(lastNode);
        System.out.println(PlayScene.children.indexOf(lastNode));

        SnakeBody newChild = new SnakeBody(lastNode.getPrevX(), lastNode.getPrevY(), new GameImage(GenerateSolidTexture.generateImage(PlayScene.snakeSize, PlayScene.snakeSize, 0xFF90EE90)), lastNode);
        PlayScene.children.add(newChild);
        Main.gameScene.add(newChild);
    }

    public void moveAllChildren(){

        moveSelf();
        for (Object obj: PlayScene.children.toArray()){
            if(obj == null)
                continue;
            if(obj instanceof SnakeBody body)
            {
                if(obj instanceof  SnakeHead)
                    continue;
                System.out.println(body.getParentNode().getX() + " : " + body.getParentNode().getPrevY());
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
    }

    @Override
    public void onKeyPressed(KeyCode keyCode){
        switch (keyCode)
        {
            case A,LEFT -> {
                if(dir != SimpleDirection.RIGHT)
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
                if(dir != SimpleDirection.LEFT)
                {
                    dir = SimpleDirection.RIGHT;
                }
            }
            case W,UP -> {
                if(dir != SimpleDirection.DOWN)
                {
                    dir = SimpleDirection.UP;
                }
            }
            case C -> eat();
        }
    }
}
