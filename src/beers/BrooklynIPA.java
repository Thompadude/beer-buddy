package beers;

public class BrooklynIPA extends Beer {

    public BrooklynIPA() {
        super(
                "Brooklyn East India Pale Ale",
                "U.S.A.",
                "Ale",
                "East IPA is a clean, "
                        + "drinkable IPA that's packed with flavor and offers a bold balance.",
                6.9
        );
    }

    @Override
    public String getPhotoURL() {
        return "/assets/photos/brooklynipa.jpg";
    }

}