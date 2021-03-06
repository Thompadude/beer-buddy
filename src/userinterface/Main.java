package userinterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));

        Scene mainScene = new Scene(root);

        this.primaryStage.setTitle("BeerBuddy");
        this.primaryStage.setScene(mainScene);
        this.primaryStage.show();
    }

}