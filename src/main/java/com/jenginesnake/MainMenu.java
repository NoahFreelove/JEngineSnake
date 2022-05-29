package com.jenginesnake;

import com.JEngine.Game.Visual.Scenes.GameScene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MainMenu extends GameScene {
    public MainMenu() {
        super("Main Menu");
        Text titleText = new Text("Snake");
        titleText.setX(250);
        titleText.setY(100);
        titleText.setScaleX(2);
        titleText.setScaleY(2);
        titleText.setFill(Color.WHITE);
        titleText.setStyle("-fx-font-weight: bold;");
        addUI(titleText);

        Text pressText = new Text("Press 'R' to start...");
        pressText.setX(230);
        pressText.setY(150);
        pressText.setScaleX(2);
        pressText.setScaleY(2);
        pressText.setFill(Color.WHITE);
        addUI(pressText);

    }
}
