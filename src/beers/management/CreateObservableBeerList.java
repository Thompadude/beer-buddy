package beers.management;

import beers.Beer;
import beers.BrooklynIPA;
import beers.BrooklynLager;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class CreateObservableBeerList {

    /**
     * Adds all hard coded beers in a observable list.
     *
     * @param beers is a list of all hardcoded beers.
     */
    public void addAllHardcodedToObservableList(ObservableList<Beer> beers) {
        beers.add(new BrooklynIPA());
        beers.add(new BrooklynLager());
    }

    public void addAllFavoritesToObservableList(
            ObservableList<Beer> favoriteBeersObsList,
            List<Beer> favoriteBeers
    ) {
        if (!(favoriteBeers == null)) {
            favoriteBeersObsList.setAll(favoriteBeers.stream().collect(Collectors.toList()));
        }
    }

}