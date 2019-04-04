package DeezerAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Playlist {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("title")
    @Expose
    private String title;

    long getId() {
        return id;
    }

    String getTitle() {
        return title;
    }

}
