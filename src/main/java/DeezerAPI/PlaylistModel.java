package DeezerAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaylistModel {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("duration")
    @Expose
    private long duration;

    @SerializedName("creation_date")
    @Expose
    private String creationDate;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDuration() {
        return duration;
    }

    public String getCreationDate() {
        return creationDate;
    }

}
