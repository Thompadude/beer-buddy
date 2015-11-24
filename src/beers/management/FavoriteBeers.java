package beers.management;

import beers.Beer;
import javafx.scene.text.Text;
import userinterface.management.FeedBack;

import java.util.ArrayList;
import java.util.List;

public class FavoriteBeers {

    List<Beer> favoriteBeers;
    FeedBack feedBack = new FeedBack();

    public List<Beer> getFavoriteBeers() {
        return favoriteBeers;
    }

    public void addBeerToFavorites(Beer beer, Text feedBackText) {
        boolean alreadyAdded = false;

        if (this.favoriteBeers == null) {

            this.favoriteBeers = new ArrayList<>();
            this.favoriteBeers.add(beer);

            feedBack.favoriteAddedConfirmation(feedBackText);
        } else {
            String alreadyAddedBeer;
            String beerToAdd = beer.getName().toLowerCase();

            for (int i = 0; i < this.favoriteBeers.size(); i++) {
                alreadyAddedBeer = this.favoriteBeers.get(i).getName().toLowerCase();

                for (int j = 0; j < this.favoriteBeers.size(); j++) {
                    if (beerToAdd.equals(alreadyAddedBeer)) {
                        alreadyAdded = true;
                    }
                }
            }

            if (!alreadyAdded) {
                this.favoriteBeers.add(beer);
                feedBack.favoriteAddedConfirmation(feedBackText);
            } else {
                feedBack.favoriteAlreadyAddedWarning(feedBackText);
            }
        }
    }

    public void removeBeerFromFavorites(Beer beer, Text feedBackText){
        if (!(this.favoriteBeers == null)){

            for (int i = 0; i<this.favoriteBeers.size(); i++){
                if (beer.equals(this.favoriteBeers.get(i))){
                    this.favoriteBeers.remove(i);
                    feedBack.favoriteRemovedConfirmation(feedBackText);
                }
            }


        }
    }

}