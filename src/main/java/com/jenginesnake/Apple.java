package com.jenginesnake;

import com.JEngine.Core.GameImage;
import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Transform;
import com.JEngine.Core.Position.Vector2;
import com.JEngine.Game.PlayersAndPawns.Sprite;

/**
 * Apple is a simple object that can't move and feed's the snake
 */
public class Apple extends Sprite {
    private final int x,y;
    public Apple(int x, int y) {
        super(Transform.exSimpleTransform(new Vector2(PlayScene.snakeSize*x, PlayScene.snakeSize*y)), new GameImage("bin/apple.png"), new Identity("apple"));
        this.x = x;
        this.y = y;
    }

    public void eat(){
        setActive(false);
        Main.gameScene.remove(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
