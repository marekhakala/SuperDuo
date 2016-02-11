package barqsoft.footballscores.DataSource;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.inject.Inject;

import barqsoft.footballscores.AppComponent;
import barqsoft.footballscores.DataSource.Model.FixtureEntity;
import barqsoft.footballscores.DataSource.Model.FixturesResultEntity;
import barqsoft.footballscores.DataSource.Model.HrefEntity;
import barqsoft.footballscores.Database.Model.Score;
import barqsoft.footballscores.FootballScoresApplication;
import barqsoft.footballscores.Ui.Fragment.ScoresListFragment;
import barqsoft.footballscores.Utilities.ConstantValues;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import timber.log.Timber;

public class FetchService extends IntentService {
    public static String DATA_UPDATED_CODE = "barqsoft.footballscores.footballscores.DATA_UPDATED_CODE";

    Realm mRealm;
    @Inject FootballAPI mFootballApi;

    public FetchService() {
        super("FetchService");
    }

    @Override
    public void onCreate() {
        Context context = getApplicationContext();
        setupComponent((AppComponent) FootballScoresApplication.get(context).getComponent());
        super.onCreate();
    }

    public interface Leagues {
        String BUNDESLIGA1 = "394";
        String BUNDESLIGA2 = "395";
        String BUNDESLIGA3 = "403";
        String LIGUE1 = "396";
        String LIGUE2 = "397";
        String PREMIER_LEAGUE = "398";
        String PRIMERA_DIVISION = "399";
        String SEGUNDA_DIVISION = "400";
        String SERIE_A = "401";
        String PRIMEIRA_LIGA = "402";
        String EREDIVISIE = "404";
        String CHAMPIONS_LEAGUE = "405";
        String LEAGUE_ONE = "425";
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mRealm = FootballScoresApplication.getDatabaseInstance(getApplication());
        long size = getData("n2", "p2");

        ResultReceiver rec = intent.getParcelableExtra("receiver");
        Bundle bundle = new Bundle();
        bundle.putString("size", String.valueOf(size));

        rec.send(ScoresListFragment.RESULT_OK, bundle);
    }

    private void clearDatabase() {
        mRealm.beginTransaction();
        RealmResults<Score> results = mRealm.where(Score.class).findAll();
        results.clear();
        mRealm.commitTransaction();
    }

    private long getData(String... timeFrames) {
        List<FixtureEntity> fixturesList = null;

        try {
            for(String timeFrame : timeFrames) {
                FixturesResultEntity result = mFootballApi.fixtures(timeFrame);

                if(fixturesList == null)
                    fixturesList = result.getFixtures();
                else
                    fixturesList.addAll(result.getFixtures());
            }
        } catch (Exception e) {
            Timber.e("Exception: " + e.getMessage());
        }

        if(fixturesList != null) {
            clearDatabase();
            return parseFixtures(fixturesList);
        }

        return 0;
    }

    private static boolean isLeagueValid(String id) {
        return (id.equals(Leagues.BUNDESLIGA1) ||
                id.equals(Leagues.BUNDESLIGA2) ||
                id.equals(Leagues.BUNDESLIGA3) ||
                id.equals(Leagues.LIGUE1) ||
                id.equals(Leagues.LIGUE2) ||
                id.equals(Leagues.PREMIER_LEAGUE) ||
                id.equals(Leagues.PRIMERA_DIVISION) ||
                id.equals(Leagues.SEGUNDA_DIVISION) ||
                id.equals(Leagues.SERIE_A) ||
                id.equals(Leagues.PRIMEIRA_LIGA) ||
                id.equals(Leagues.EREDIVISIE) ||
                id.equals(Leagues.CHAMPIONS_LEAGUE) ||
                id.equals(Leagues.LEAGUE_ONE));
    }

    private long parseFixtures(List<FixtureEntity> fixturesList) {
        final Map<String, Integer> matchDateMap = new HashMap<String, Integer>();

        // Match data
        String leagueId = null;

        String matchDate = null;
        String matchTime = null;

        String homeTeamName = null;
        String awayTeamName = null;

        Long homeGoals = null;
        Long awayGoals = null;

        String matchId = null;
        Long matchDay = null;

        for (FixtureEntity fixture : fixturesList) {
            matchId = getId(fixture.getLinks().getSelf(), ConstantValues.MATCH_URL);
            leagueId = getId(fixture.getLinks().getSoccerSeason(), ConstantValues.SEASON_URL);

            if (isLeagueValid(leagueId)) {
                matchDate = fixture.getDate();
                matchTime = matchDate.substring(matchDate.indexOf("T") + 1, matchDate.indexOf("Z"));
                matchDate = matchDate.substring(0, matchDate.indexOf("T"));
                SimpleDateFormat matchDateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss", Locale.US);
                matchDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                try {
                    Date parsedDate = matchDateFormat.parse(matchDate + matchTime);
                    SimpleDateFormat newDate = new SimpleDateFormat("yyyy-MM-dd:HH:mm", Locale.US);
                    newDate.setTimeZone(TimeZone.getDefault());
                    matchDate = newDate.format(parsedDate);
                    matchTime = matchDate.substring(matchDate.indexOf(":") + 1);
                    matchDate = matchDate.substring(0, matchDate.indexOf(":"));
                } catch (Exception e) {
                    Timber.e("Error: " + e.getMessage());
                }

                homeTeamName = fixture.getHomeTeamName();
                awayTeamName = fixture.getAwayTeamName();

                homeGoals = fixture.getResult().getGoalsHomeTeam();
                awayGoals = fixture.getResult().getGoalsAwayTeam();
                matchDay = fixture.getMatchDay();

                if (!matchDateMap.containsKey(matchDate))
                    matchDateMap.put(matchDate, 1);
                else
                    matchDateMap.put(matchDate, matchDateMap.get(matchDate) + 1);

                mRealm.beginTransaction();

                Score score = mRealm.createObject(Score.class);
                int nextID = (int)(mRealm.where(Score.class).max("id").intValue() + 1);

                score.setId(nextID);
                score.setDate(matchDate);
                score.setTime(matchTime);

                score.setHome(homeTeamName);
                score.setAway(awayTeamName);

                score.setLeague(leagueId);

                score.setHomeGoals(homeGoals);
                score.setAwayGoals(awayGoals);

                score.setMatchId(matchId);
                score.setMatchDay(matchDay);

                mRealm.commitTransaction();

            } else {
                Timber.w("Invalid league id " + leagueId);
            }
        }

        RealmResults<Score> scoreModels = mRealm.where(Score.class).findAllSorted("id", Sort.DESCENDING);
        Timber.d("Scores list size: " + scoreModels.size());
        sendBroadcast(new Intent(DATA_UPDATED_CODE).setPackage(getPackageName()));

        return scoreModels.size();
    }

    private static String getId(HrefEntity hrefWrapper, String link) {
        String id = hrefWrapper.getHref();
        return id.replace(link, "");
    }

    protected void setupComponent(AppComponent component) {
        component.inject(this);
    }
}
