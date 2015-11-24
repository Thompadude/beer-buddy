package userinterface;

import beers.Beer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditWindowController {

    Beer beer;
    Beer newBeer;
    Stage editStage;
    TextArea beerInfoTextArea;
    ObservableList<Beer> beers;
    boolean createNewBeer = false;

    @FXML
    private Button cancelButton;
    @FXML
    private Button okButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField typeTextField;
    @FXML
    private TextField originTextField;
    @FXML
    private TextField infoTextField;
    @FXML
    private TextField alcTextField;

    @FXML
    private void cancelButtonClicked(ActionEvent event) {
        this.editStage.close();
    }

    @FXML
    private void okButtonClicked(ActionEvent event) {
        saveChanges();
    }

    public void setEditStage(
            Stage editStage,
            Beer beer,
            boolean editClicked,
            TextArea beerInfoTextArea,
            ObservableList<Beer> beers
    ) {
        this.beer = beer;
        this.editStage = editStage;
        this.beerInfoTextArea = beerInfoTextArea;
        this.beers = beers;

        this.editStage.show();

        // If user did not click "edit" he/she clicked "add beer".
        if (editClicked) {
            this.editStage.setTitle("Edit Beer");
            editBeer();
        } else {
            this.editStage.setTitle("Create New Beer");
            createNewBeer();
        }
    }

    private void editBeer() {
        nameTextField.setText(this.beer.getName());
        typeTextField.setText(this.beer.getType());
        originTextField.setText(this.beer.getOrigin());
        infoTextField.setText(this.beer.getInfo());
        alcTextField.setText(String.valueOf(this.beer.getAlcoholVolume()));
    }

    private void createNewBeer() {
        nameTextField.setText("");
        typeTextField.setText("");
        originTextField.setText("");
        infoTextField.setText("");
        alcTextField.setText("Please use format \"0.0\"");
        this.createNewBeer = true;
        saveChanges();
    }

    private void saveChanges() {
        boolean invalidAlcFormat = false;

        this.beer.setName(nameTextField.getText());
        this.beer.setType(typeTextField.getText());
        this.beer.setOrigin(originTextField.getText());
        this.beer.setInfo(infoTextField.getText());

        try {
            Double.parseDouble(alcTextField.getText());
        } catch (NumberFormatException e) {
            alcTextField.setText("Please use format \"0.0\"");
            invalidAlcFormat = true;
        }

        // Edit a beer
        if (!invalidAlcFormat && !this.createNewBeer) {
            this.beer.setAlcoholVolume(Double.parseDouble(alcTextField.getText()));
            beerInfoTextArea.setText(this.beer.getInfo());
            this.editStage.close();
            // Create a new beer
        } else if (!invalidAlcFormat && this.createNewBeer) {
            this.newBeer = new Beer(
                    nameTextField.getText(),
                    typeTextField.getText(),
                    originTextField.getText(),
                    infoTextField.getText(),
                    Double.parseDouble(alcTextField.getText()));
            this.beers.add(newBeer);
            this.editStage.close();
        }
    }

}