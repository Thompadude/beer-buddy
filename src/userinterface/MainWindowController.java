package userinterface;

import beers.Beer;
import beers.management.CreateObservableBeerList;
import beers.management.FavoriteBeers;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import userinterface.management.AlertWindows;
import userinterface.management.FeedBack;

import java.io.IOException;

public class MainWindowController {

    Stage addAndEditStage;
    boolean editBeer = false;
    AlertWindows alertWindows;
    ObservableList<Beer> beers;
    ObservableList<Beer> favoriteBeersObsList;
    FavoriteBeers favoriteBeers;
    CreateObservableBeerList createObservableBeerList;
    EditWindowController editWindowController;

    @FXML
    private Button addToFavButton;
    @FXML
    private Button removeFromFavButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button addButton;
    @FXML
    private Text feedBackText;
    @FXML
    private TextArea aboutTextArea;
    @FXML
    private TextArea beerInfoTextArea;
    @FXML
    private TableView<Beer> beersTableView;
    @FXML
    private ImageView beerImage;
    @FXML
    private TableColumn<Beer, String> nameColumn;
    @FXML
    private TableColumn<Beer, String> typeColumn;
    @FXML
    private TableColumn<Beer, String> originColumn;
    @FXML
    private TableColumn<Beer, Double> alcColumn;
    @FXML
    private ToolBar addEditDeleteToolbar;

    @FXML
    private void exitSystem(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private void aboutButtonClicked(ActionEvent event) {
        beerImage.setVisible(false);
        beerInfoTextArea.setVisible(false);
        addToFavButton.setVisible(false);
        removeFromFavButton.setVisible(false);
        beersTableView.setVisible(false);
        addEditDeleteToolbar.setVisible(false);

        aboutTextArea.setText(aboutText());
        aboutTextArea.setVisible(true);
    }

    @FXML
    private void viewAllButtonClicked(ActionEvent event) {
        beersTableView.setItems(this.beers);
        beersTableView.setVisible(true);
        addEditDeleteToolbar.setVisible(true);
        addToFavButton.setVisible(true);
        addButton.setVisible(true);
        editButton.setVisible(true);
        deleteButton.setVisible(true);
        removeFromFavButton.setVisible(false);
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        originColumn.setCellValueFactory(cellData -> cellData.getValue().originProperty());
        alcColumn.setCellValueFactory(cellData -> cellData.getValue().alcoholVolumeProperty().asObject());

        this.createObservableBeerList = new CreateObservableBeerList();
        this.alertWindows = new AlertWindows();
        this.favoriteBeers = new FavoriteBeers();

        this.beers = FXCollections.observableArrayList();
        createObservableBeerList.addAllHardcodedToObservableList(this.beers);

        beersTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showBeer(newValue));
    }

    @FXML
    private void addToFavButtonClicked() {
        int selectedBeer = beersTableView.getSelectionModel().getSelectedIndex();

        if (selectedBeer >= 0) {
            this.favoriteBeers.addBeerToFavorites(beersTableView.getItems().get(selectedBeer), this.feedBackText);
        } else {
            this.alertWindows.noBeerSelected();
        }
    }

    @FXML
    private void removeFromFavButtonClicked() {
        int selectedBeer = beersTableView.getSelectionModel().getSelectedIndex();

        if (selectedBeer >= 0) {
            this.favoriteBeers.removeBeerFromFavorites(beersTableView.getItems().get(selectedBeer), this.feedBackText);
            beersTableView.getItems().remove(selectedBeer);
        } else {
            this.alertWindows.noBeerSelected();
        }
    }

    @FXML
    private void viewFavoriteButtonClicked() {
        addToFavButton.setVisible(false);
        addButton.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        beersTableView.setVisible(true);
        removeFromFavButton.setVisible(true);

        if (this.favoriteBeersObsList == null) {
            this.favoriteBeersObsList = FXCollections.observableArrayList();
        }

        this.createObservableBeerList.addAllFavoritesToObservableList(this.favoriteBeersObsList, this.favoriteBeers.getFavoriteBeers());

        beersTableView.setItems(this.favoriteBeersObsList);
    }

    @FXML
    private void showBeer(Beer beer) {
        if (!(beer == null)) {

            if (!(beer.getPhotoURL() == null)) {
                beerImage.setImage(new Image(beer.getPhotoURL()));
            } else {
                beerImage.setImage(new Image("/assets/icons/noimagefound.jpg"));
            }

            beerImage.setVisible(true);
            beerInfoTextArea.setVisible(true);

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), beerInfoTextArea);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

            // TODO move origin to beerInfoTextArea and replace with something else.
            beerInfoTextArea.setText(beer.getInfo());
            // beerInfoTextArea.setText("Origin: " + beer.getOrigin() + "\n\n" + beer.getInfo());
        } else {
            beerImage.setVisible(false);
            beerInfoTextArea.setVisible(false);
        }
    }

    @FXML
    private void deleteButtonClicked(ActionEvent event) {
        int selectedBeer = beersTableView.getSelectionModel().getSelectedIndex();

        if (selectedBeer >= 0) {
            Beer beerToDelete = beersTableView.getItems().get(selectedBeer);

            beersTableView.getItems().remove(selectedBeer);

            FeedBack feedBack = new FeedBack();
            feedBack.deleteConfirmation(feedBackText);

            if (!(this.favoriteBeers.getFavoriteBeers() == null)) {
                for (int i = 0; i < this.favoriteBeers.getFavoriteBeers().size(); i++) {
                    if (beerToDelete.equals(this.favoriteBeers.getFavoriteBeers().get(i))) {
                        this.favoriteBeers.getFavoriteBeers().remove(i);
                    }
                }
            }

        } else {
            this.alertWindows.noBeerSelected();
        }
    }

    @FXML
    private void addButtonClicked(ActionEvent event) throws IOException {
        createAddAndEditBeerWindow();
        this.editBeer = false;
        Beer newBeer = new Beer("", "", "", "", 0.0);
        this.editWindowController.setEditStage(
                this.addAndEditStage,
                newBeer,
                editBeer,
                beerInfoTextArea,
                this.beers
        );
    }

    @FXML
    private void editButtonClicked(ActionEvent event) throws IOException {
        createAddAndEditBeerWindow();

        int selectedBeer = beersTableView.getSelectionModel().getSelectedIndex();

        if (selectedBeer >= 0) {
            this.editBeer = true;
            Beer beerToEdit = beersTableView.getItems().get(selectedBeer);
            this.editWindowController.setEditStage(
                    this.addAndEditStage,
                    beerToEdit,
                    editBeer,
                    beerInfoTextArea,
                    this.beers
            );
        } else {
            this.alertWindows.noBeerSelected();
        }
    }

    private void createAddAndEditBeerWindow() throws IOException {
        // Loads the object hierarchy from EditWindow.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditWindow.fxml"));

        // Parent is the base class for all nodes in a new window.
        // load() loads the object hierarchy from the FXML document.
        Parent root = fxmlLoader.load();

        // Returns the controller associated with the root object.
        // This code must be executed after the root declaration.
        this.editWindowController = fxmlLoader.getController();

        // Creates a scene for root
        Scene scene = new Scene(root);

        this.addAndEditStage = new Stage();
        // Let the stage know we want to work with the scene containing the root.
        addAndEditStage.setScene(scene);
    }

    private String aboutText() {
        return "This application was created with loads of beer."
                + "\n\nContact: thompa.lansing@gmail.com";
    }

}