package Base;

public abstract class TestsDescription {

    public static final String CHECK_CHANNEL_LINK_TEST_DESCRIPTION =
        "Login to account, go to channel page, save expected channel link, save actual channel link," +
        "compare  actual channel link with expected channel link";

    public static final String ADD_TRACKS_TO_FAVORITE_TEST_DESCRIPTION =
        "Login to account, search music, get search confirm text and compare with actual search text," +
        "add tracks to favorite tracks and save name of tracks, go to Favorite tracks page," +
        "get list of favorite tracks names and compare with expected favorite tracks list";

    public static final String DELETE_TRACKS_FROM_FAVORITE_TEST_DESCRIPTION =
        "Login to account, go to Favorite page , select all tracks for delete and delete, " +
        "get list of favorite tracks names and check not equals with expected favorite tracks list";

    public static final String CREATE_PLAYLIST_TEST_DESCRIPTION =
        "Login to account, go to My music page, create playlist," +
        " get name created playlist from page and compare with expected name, " +
        "go to My music playlist menu, check list of playlist contains expected playlist";

    public static final String ADD_TRACKS_TO_PLAYLIST_TEST_DESCRIPTION =
        "Login to account, search music, get confirm search text and compare with expected name of tracks," +
        "add track to playlist, go to My music playlist menu, open playlist where was added tracks, " +
        "get actual tracks in playlist and compare with saved expected tracks.";

    public static final String CHECK_PLAYLIST_TOTAL_DURATION_TEST_DESCRIPTION =
        "Login to account, go to My music playlist menu, open playlist, get actual playlist duration and" +
        "compare with expected duration";

    public static final String DELETE_PLAYLIST_TEST_DESCRIPTION =
        "Get playlist ID by playlist name, delete playlist by ID," +
        "check deleted playlist not contains in current list of playlist by name.";

    public static final String SEARCH_TEST_DESCRIPTION =
        "Login to account, entry search text, compare confirm text with search text, go to artist search menu," +
        "check artist actual name contains expected name";

    public static final String USER_INFO_TEST_DESCRIPTION =
        "Login to account, go to My information menu, get actual user personal data and compare with" +
        "expected user personal data";

}
