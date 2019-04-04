package DeezerAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


class WrapperPlaylist {

    @SerializedName("data")
    @Expose
    private List<Playlist> data = null;

    List<Playlist> getData() {
        return data;
    }

}