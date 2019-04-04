package DeezerAPI;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DeezerClient {

    @GET("user/{userId}/playlists")
    Call<WrapperPlaylist> getListPlaylist(
            @Path("userId") String userId
    );

    @DELETE("playlist/{playlistId}")
    Call<Boolean> deletePlaylist(
            @Path("playlistId") String playlistId
    );

}