package com.jenginesnake;

import com.JEngine.Core.Identity;
import com.JEngine.Core.Position.Vector3;
import com.JEngine.Game.Visual.GameCamera;
import com.JEngine.Game.Visual.GameWindow;
import com.JEngine.Game.Visual.Scenes.SceneManager;
import com.JEngine.Utility.About.GameInfo;
import com.JEngine.Utility.Misc.GameUtility;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    // Init our scenes so we can switch to them later
    public static PlayScene gameScene;
    public static MainMenu mainMenu = new MainMenu();
    public static GameWindow window;

    @Override
    public void start(Stage stage) {

        // Create the game window
        window = new GameWindow(mainMenu, 1f, GameInfo.getAppName(), stage);
        window.setBackgroundColor(Color.web("#009A17"));

        // Camera doesn't move, but we need to create one to do the rendering process
        new GameCamera(Vector3.emptyVector(), window, mainMenu, new Identity("Camera"));

        // setup size and keybinds for the window
        setStageDetails(stage);
    }

    public static void main(String[] args) {
        GameInfo.setAppName("Snake");
        GameInfo.setAppVersionMajor(1);
        GameInfo.setAuthors(new String[]{"Noah Freelove"});
        GameInfo.setYear(2022);
        GameInfo.setBuildID("1.0.0");
        launch();
    }

    private void setStageDetails(Stage stage){
        // set the window size based on the width and height of the play scene
        stage.setWidth(PlayScene.width*PlayScene.snakeSize+16);
        stage.setHeight(PlayScene.height*PlayScene.snakeSize+40);

        // add our basic key-binds, Escape - quit, R - Play/Restart
        stage.addEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED, (e) -> {
            if (e.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
                GameUtility.exitApp();
            }

            if (e.getCode() == javafx.scene.input.KeyCode.R) {
                if(PlayScene.moveTimer != null)
                {
                    PlayScene.moveTimer.stop();
                }
                PlayScene.isPaused = false;
                gameScene = new PlayScene();
                SceneManager.switchScene(gameScene);
            }
        });
    }
}