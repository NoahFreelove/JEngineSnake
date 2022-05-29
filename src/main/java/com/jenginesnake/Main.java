package com.jenginesnake;

import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.GameCamera;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.GameScene;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.About.GameInfo;
import com.JEngine.Utility.Misc.GameUtility;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public static PlayScene gameScene;
    public static MainMenu mainMenu = new MainMenu();
    public static GameWindow window;
    @Override
    public void start(Stage stage) {


        window = new GameWindow(mainMenu, 1f, GameInfo.getAppName(), stage);
        new GameCamera(Vector3.emptyVector(), window, mainMenu, new Identity("Camera"));
        window.setBackgroundColor(Color.web("#009A17"));
        stage.setWidth(PlayScene.width*PlayScene.snakeSize+16);
        stage.setHeight(PlayScene.height*PlayScene.snakeSize+40);
        stage.addEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED, (e) -> {
            if (e.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
                GameUtility.exitApp();
            }
            if (e.getCode() == javafx.scene.input.KeyCode.R) {
                if(PlayScene.gt != null)
                {
                    PlayScene.gt.stop();
                }
                PlayScene.isPaused = false;
                gameScene = new PlayScene();
                SceneManager.switchScene(gameScene);
            }
        });
    }

    public static void main(String[] args) {
        GameInfo.setAppName("Snake");
        GameInfo.setAppVersionMajor(1);
        GameInfo.setAuthors(new String[]{"Noah Freelove"});
        GameInfo.setYear(2022);
        GameInfo.setBuildID("1.0.0");
        launch();
    }
}