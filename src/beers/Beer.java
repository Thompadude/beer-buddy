package beers;

import javafx.beans.property.*;

public class Beer {

    private StringProperty name;
    private StringProperty type;
    private StringProperty origin;
    private ObjectProperty<Grade> grade;
    private String photoURL;
    private StringProperty info;
    private DoubleProperty alcoholVolume;
    private boolean Favorite;

    public Beer(
            String name,
            String type,
            String origin,
            String info,
            Double alcoholVolume
    ) {
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.origin = new SimpleStringProperty(origin);
        this.info = new SimpleStringProperty(info);
        this.alcoholVolume = new SimpleDoubleProperty(alcoholVolume);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getOrigin() {
        return origin.get();
    }

    public void setOrigin(String origin) {
        this.origin.set(origin);
    }

    public StringProperty originProperty() {
        return origin;
    }

    public Grade getGrade() {
        return grade.get();
    }

    public void setGrade(Grade grade) {
        this.grade.set(grade);
    }

    public ObjectProperty<Grade> gradeProperty() {
        return grade;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getInfo() {
        return info.get();
    }

    public void setInfo(String info) {
        this.info.set(info);
    }

    public StringProperty infoProperty() {
        return info;
    }

    public double getAlcoholVolume() {
        return alcoholVolume.get();
    }

    public void setAlcoholVolume(double alcoholVolume) {
        this.alcoholVolume.set(alcoholVolume);
    }

    public DoubleProperty alcoholVolumeProperty() {
        return alcoholVolume;
    }

    public boolean isFavorite() {
        return Favorite;
    }

    public void setFavorite(boolean favorite) {
        Favorite = favorite;
    }

}