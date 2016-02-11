package barqsoft.footballscores.Utilities;

import barqsoft.footballscores.BuildConfig;

public class ConstantValues {
    // REST API
    public static final String ENDPOINT_URL = "http://api.football-data.org/alpha";
    public static final String MATCH_URL = ENDPOINT_URL + "/fixtures/";
    public static final String SEASON_URL = ENDPOINT_URL + "/soccerseasons/";

    public static final String API_KEY = BuildConfig.DATA_API_KEY;

    // Realm DB
    public static final int DB_SCHEMA_VERSION = 1;
    public static final String REALM_DB_FILE = "footballscores.realm";

    // Share link
    public static final String HASH_TAG_FOOTBALL = "#FootballScores";
}
