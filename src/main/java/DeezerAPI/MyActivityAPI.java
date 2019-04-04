package DeezerAPI;

import Utils.PropertyLoader;
import io.qameta.allure.Step;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MyActivityAPI {

    private final String userId = PropertyLoader.loadProperty("USER_ID");
    private final String accessToken = PropertyLoader.loadProperty("ACCESS_TOKEN");
    private DeezerClient deezerClient = RetrofitService.createService(DeezerClient.class, accessToken);

    @Step("Get playlist ID by name")
    public String getPlaylistIDByName(String playlistName) {
        Response<WrapperPlaylist> responsePlaylistIs = null;
        String playlistId = null;

        try {
            responsePlaylistIs = deezerClient.getListPlaylist(userId).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (responsePlaylistIs.isSuccessful()) {
            playlistId = responsePlaylistIs.body().getData().stream()
                    .filter(elem -> elem.getTitle().equals(playlistName))
                    .map(Playlist::getId)
                    .findFirst().get().toString();
        }
        return playlistId;
    }

    @Step("Delete playlist by ID")
    public void deletePlaylistById(String idPlaylist) {
        Response<Boolean> deletePlayListByName = null;

        try {
            deletePlayListByName = deezerClient.deletePlaylist(idPlaylist).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Get all playlists names")
    public List<String> getAllPlaylist() {
        Response<WrapperPlaylist> getPlaylistList = null;
        List<String> listOfNames = null;

        try {
            getPlaylistList = deezerClient.getListPlaylist(userId).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (getPlaylistList.isSuccessful()) {
            listOfNames = getPlaylistList.body()
                    .getData().stream()
                    .map(Playlist::getTitle)
                    .collect(Collectors.toList());
        }
        return listOfNames;
    }

}