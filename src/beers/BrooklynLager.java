package beers;

public class BrooklynLager extends Beer {

    public BrooklynLager() {
        super(
                "Brooklyn Lager",
                "U.S.A.",
                "Lager",
                "A wonderfully flavorful beer, smooth, "
                        + "refreshing and very versatile with food.",
                5.2
        );
    }

    @Override
    public String getPhotoURL() {
        return "/assets/photos/brooklynlager.jpg";
    }

}