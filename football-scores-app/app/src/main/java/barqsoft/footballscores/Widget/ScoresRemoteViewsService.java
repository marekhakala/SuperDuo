package barqsoft.footballscores.Widget;

import android.content.Intent;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import barqsoft.footballscores.Database.Model.Score;
import barqsoft.footballscores.FootballScoresApplication;
import barqsoft.footballscores.R;
import barqsoft.footballscores.Utilities.UtilityHelper;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import timber.log.Timber;

public class ScoresRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            protected List<ScoreItem> scoresList = null;

            @Override
            public void onCreate() {
                Timber.d("onCreate");
            }

            @Override
            public void onDataSetChanged() {
                Timber.d("onDataSetChanged");
                Realm realm = FootballScoresApplication.getDatabaseInstance(getApplication());

                final long identityToken = Binder.clearCallingIdentity();

                String formatString = "yyyy-MM-dd";
                SimpleDateFormat format = new SimpleDateFormat(formatString);
                String todayDate = format.format(new Date());

                RealmResults<Score> results = realm.where(Score.class)
                        .equalTo("date", todayDate)
                        .findAllSorted("id", Sort.ASCENDING);

                if(scoresList != null)
                    scoresList.clear();
                else
                    scoresList = new ArrayList<ScoreItem>();

                for(Score item : results)
                    scoresList.add(new ScoreItem(item));

                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
            }

            @Override
            public int getCount() {
                return scoresList.size();
            }

            @Override
            public RemoteViews getViewAt(int position) {


                if (position == AdapterView.INVALID_POSITION ||
                        scoresList == null || position < 0 ||
                        position >= scoresList.size())
                    return null;

                RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_item_today_scores);
                ScoreItem scoreObject = scoresList.get(position);
                return getRemoteView(views, scoreObject);
            }

            public RemoteViews getRemoteView(RemoteViews views, ScoreItem scoreObject) {
                String league = UtilityHelper.getLeague(getResources(), Integer.parseInt(scoreObject.getLeague()));
                Timber.d("League: " + league);
                String matchDay = "Match day: " + scoreObject.getMatchDay();

                String homeTeamName = scoreObject.getHome();
                String awayTeamName = scoreObject.getAway();

                String score = UtilityHelper.getScores(scoreObject.getHomeGoals(), scoreObject.getAwayGoals());

                views.setTextViewText(R.id.widget_item_league, league);
                views.setTextViewText(R.id.widget_item_home_name, homeTeamName);
                views.setTextViewText(R.id.widget_item_away_name, awayTeamName);
                views.setTextViewText(R.id.widget_item_score, score);
                views.setTextViewText(R.id.widget_item_match_day, matchDay);

                views.setImageViewResource(R.id.widget_item_home_crest, UtilityHelper.getTeamCrestByTeamName(homeTeamName));
                views.setImageViewResource(R.id.widget_item_away_crest, UtilityHelper.getTeamCrestByTeamName(awayTeamName));

                views.setContentDescription(R.id.widget_item_home_crest, homeTeamName);
                views.setContentDescription(R.id.widget_item_away_crest, awayTeamName);
                views.setContentDescription(R.id.widget_item_home_name, homeTeamName);
                views.setContentDescription(R.id.widget_item_away_name, awayTeamName);
                views.setContentDescription(R.id.widget_item_league, league);
                views.setContentDescription(R.id.widget_item_match_day, matchDay);
                views.setContentDescription(R.id.widget_item_score, score);

                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.widget_item_today_scores);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if(position >= 0 && position < scoresList.size())
                    return scoresList.get(position).getMatchDay();
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
