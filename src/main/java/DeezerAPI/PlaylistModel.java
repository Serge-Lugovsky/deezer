package DeezerAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class Creator {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("name")
    @Expose
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

class Playlist {

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

public class PlaylistModel {

    @SerializedName("data")
    @Expose
    private List<Playlist> data = null;

    @SerializedName("total")
    @Expose
    private long total;

    public List<Playlist> getData() {
        return data;
    }

    public long getTotal() {
        return total;
    }

}