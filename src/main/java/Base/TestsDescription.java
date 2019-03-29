package Base;

public abstract class TestsDescription {

    public static final String CHECK_CHANNEL_LINK_TEST_DESCRIPTION =
        "Login to account, go to 'All Channels' page, save expected channel link from 'All Channels' page," +
        " go to 'Chanel' page, save actual channel link from 'Chanel' page URL," +
        " compare actual channel link with expected channel link";

    public static final String ADD_TRACKS_TO_FAVORITE_TEST_DESCRIPTION =
        "Login to account, search music, get search confirm text and compare with expected search text," +
        " add tracks to 'Favorite tracks' and save name of tracks in expected 'Favorite tracks' list," +
        " go to 'Favorite tracks' page," +
        " get actual list of 'Favorite tracks' names and compare with expected list of 'Favorite tracks' names";

    public static final String DELETE_TRACKS_FROM_FAVORITE_TEST_DESCRIPTION =
        "Login to account, go to 'Favorite tracks' page, select all tracks for delete and delete," +
        " get list of 'Favorite tracks' names and check not equals with expected list of 'Favorite tracks' names";

    public static final String CREATE_PLAYLIST_TEST_DESCRIPTION =
        "Login to account, go to 'My Music' playlist menu, create playlist," +
        " get name created playlist from page and compare with expected playlist name," +
        " go to 'My music' playlist menu, check list of all playlist names contains expected playlist name";

    public static final String ADD_TRACKS_TO_PLAYLIST_TEST_DESCRIPTION =
        "Login to account, search music, get confirm search text from page and compare with expected name of tracks," +
        " add tracks to playlist and save tracks names to expected list of tracks names," +
        " go to 'My music' playlist menu, open playlist where was added tracks," +
        " get actual tracks names from playlist and compare with saved expected tracks names.";

    public static final String CHECK_PLAYLIST_TOTAL_DURATION_TEST_DESCRIPTION =
        "Login to account, go to 'My music' playlist menu, open playlist, get actual playlist duration from tracks" +
        " and compare with expected duration from top playlist info block";

    public static final String CHECK_TRACK_NAME_IN_CONTEXT_MENU_TEST_DESCRIPTION =
        "Login to account, go to 'My Music' playlist menu, open playlist, save expected track name from page," +
        " open track context menu, get track name from context menu and compare with expected track name from page";

    public static final String DELETE_PLAYLIST_TEST_DESCRIPTION =
        "Get playlist ID by playlist name, delete playlist by ID," +
        " check deleted playlist not contains in current list of playlist by name";

    public static final String SEARCH_TEST_DESCRIPTION =
        "Login to account, search artist, compare confirm text from page with search text," +
        " go to artist search menu, check first artist name from page contains expected artist name";

    public static final String USER_INFO_TEST_DESCRIPTION =
        "Login to account, go to 'My information menu', get actual user personal data from page" +
        "  get expected user personal data, compare actual user personal data with expected user personal data";

}
