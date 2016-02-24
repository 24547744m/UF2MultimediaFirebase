package firebase.uf2multimediadiegoz;

/**
 * Created by damuser on 8/02/16.
 */
public class Note {

    private String title;
    private String description;
    private String latitude;
    private String longitude;
    private String urlImage;

    public Note(String title, String description, String latitude, String longitude, String urlImage) {
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.urlImage = urlImage;
    }
    public Note(){ }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUrlImage() {
        return urlImage;
    }
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
