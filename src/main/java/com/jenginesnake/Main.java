package com.jenginesnake;

import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.GameCamera;
import com.JEngine.Game.Visual.GameWindow;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public static PlayScene gameScene = new PlayScene();
    public static GameWindow window;
    @Override
    public void start(Stage stage) {
        window = new GameWindow(gameScene, 1f, "Snake", stage);
        new GameCamera(Vector3.emptyVector(), window, gameScene, new Identity("Camera"));
        window.setBackgroundColor(Color.web("#009A17"));
        stage.setWidth(PlayScene.width*PlayScene.snakeSize);
        stage.setHeight(PlayScene.height*PlayScene.snakeSize);
    }

    public static void main(String[] args) {
        launch();
    }
}