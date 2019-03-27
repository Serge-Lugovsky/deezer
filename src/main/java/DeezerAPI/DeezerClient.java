package DeezerAPI;

import retrofit2.Call;
import retrofit2.http.*;

public interface DeezerClient {

    @GET("user/{userId}/playlists")
    Call <DataPlaylistModel> getListPlaylist(
            @Path("userId") String userId
    );

    @DELETE("playlist/{playlistId}")
    Call <Boolean> deletePlaylist(
            @Path("playlistId") String playlistId
    );

}
