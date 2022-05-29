package com.jenginesnake;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.PlayersAndPawns.Player;

/**
 *  SnakeBody - The SnakeBody class simply follows its parent's previous position.
 */
public class SnakeBody extends Player {
    private int x,y;
    private int prevX,prevY;

    public SnakeBody parentNode; // The node this SnakeBody will follow

    public SnakeBody(int x, int y, GameImage sprite) {
        super(Transform.exSimpleTransform(new Vector2(PlayScene.snakeSize *x, PlayScene.snakeSize *y)), sprite, new Identity("snake"));
        this.x = x;
        this.y = y;
        prevX = x;
        prevY = y;
    }
    public SnakeBody(int x, int y, GameImage sprite, SnakeBody parentNode) {
        super(Transform.exSimpleTransform(new Vector2(PlayScene.snakeSize *x, PlayScene.snakeSize *y)), sprite, new Identity("snake"));
        this.x = x;
        this.y = y;
        prevX = x;
        prevY = y;
        this.parentNode = parentNode;
    }

    // Update position on screen
    @Override
    public void Update(){
        setPosition(new Vector3(PlayScene.snakeSize *x, PlayScene.snakeSize *y));
    }

    @Override
    public String toString(){
        return String.format("Snake @%d,%d (%s)",getX(),getY(), getClass().getSimpleName());
    }

    // Getters and Setters:
    public int getPrevX() {
        return prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public void setParentNode(SnakeBody parentNode){
        this.parentNode = parentNode;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        prevX = this.x;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        prevY = this.y;
        this.y = y;
    }

    public SnakeBody getParentNode() {
        return parentNode;
    }
}
