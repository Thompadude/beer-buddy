package userinterface.management;

import javafx.animation.FadeTransition;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class FeedBack {

    FadeTransition fadeTransition;

    public void deleteConfirmation(Text feedBackText) {
        feedBackText.setText("Beer Deleted");

        this.fadeTransition = setFeedBackFade(feedBackText);

        feedBackText.setVisible(true);
    }

    public void favoriteAddedConfirmation(Text feedBackText) {
        feedBackText.setText("Added to Favorites!");

        this.fadeTransition = setFeedBackFade(feedBackText);

        feedBackText.setVisible(true);
    }

    public void favoriteRemovedConfirmation(Text feedBackText) {
        feedBackText.setText("Removed from Favorites");

        this.fadeTransition = setFeedBackFade(feedBackText);

        feedBackText.setVisible(true);
    }

    public void favoriteAlreadyAddedWarning(Text feedBackText) {
        feedBackText.setText("Favorite Already Added!");

        this.fadeTransition = setFeedBackFade(feedBackText);

        feedBackText.setVisible(true);
    }


    private FadeTransition setFeedBackFade(Text feedBackText) {
        this.fadeTransition = new FadeTransition(Duration.millis(2000), feedBackText);
        this.fadeTransition.setFromValue(1);
        this.fadeTransition.setToValue(0);
        this.fadeTransition.play();

        return this.fadeTransition;
    }

}
