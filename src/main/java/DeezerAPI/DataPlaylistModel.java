package DeezerAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DataPlaylistModel {

    @SerializedName("data")
    @Expose
    private List<PlaylistModel> data = null;

    @SerializedName("total")
    @Expose
    private long total;

    public List<PlaylistModel> getData() {
        return data;
    }

    public long getTotal() {
        return total;
    }

}