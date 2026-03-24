package dk.sdu.imada.oop26;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameController gamecontroller = new GameController();
        // Scene scene = new Scene(new Group(gamecontroller.getCanvas()));
        primaryStage.setScene(gamecontroller.getScene());
        primaryStage.setTitle("Pac-Man");
        gamecontroller.start();
        primaryStage.show();
    }
}
