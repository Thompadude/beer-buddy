package userinterface.management;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

public class AlertWindows {

    public void noBeerSelected() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("No beer selected!");
        alert.setTitle("Beer Missing");

        /*
        * Main content in an alert window is a DialogPane.
        * An alert window is actually a new scene,
        * that is why we have to refer it to the .css again.
        */
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/assets/css/root.css").toExternalForm());
        dialogPane.getStyleClass().add("root");

        alert.showAndWait();
    }

    public void beerAddedToFavories(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Beer added to favorites!");
        alert.setTitle("Favorite added");

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/assets/css/root.css").toExternalForm());
        dialogPane.getStylesheets().add("root");

        alert.showAndWait();
    }

}