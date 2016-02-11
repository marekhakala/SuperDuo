package barqsoft.footballscores.Database;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import barqsoft.footballscores.AppComponent;
import barqsoft.footballscores.Database.Model.Score;
import barqsoft.footballscores.FootballScoresApplication;
import barqsoft.footballscores.R;
import barqsoft.footballscores.Utilities.ConstantValues;
import barqsoft.footballscores.Utilities.UtilityHelper;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

public class ScoresRecyclerViewAdapter extends RealmBasedRecyclerViewAdapter<Score, ScoresRecyclerViewAdapter.ScoreViewHolder> {
    protected Context mScoresContext = null;
    protected OnScoreItemClickListener mOnClickListener = OnScoreItemClickListener.EMPTY;

    public ScoresRecyclerViewAdapter(Context context, RealmResults<Score> realmResults,
                                     boolean automaticUpdate, boolean animateIdType,
                                     String animateExtraColumnName) {
        super(context, realmResults, automaticUpdate, animateIdType, animateExtraColumnName);
        mScoresContext = context;
        setupComponent((AppComponent) FootballScoresApplication.get(context).getComponent());
    }

    public interface OnScoreItemClickListener {
        void onScoreItemClicked(int position, View view);
        void onShareScoreItemClicked(String text);

        OnScoreItemClickListener EMPTY = new OnScoreItemClickListener() {
            @Override public void onScoreItemClicked(int position, View view) {}
            @Override public void onShareScoreItemClicked(String text) {}
        };
    }

    public void setOnScoreItemClickListener(OnScoreItemClickListener listener) {
        mOnClickListener = listener;
    }

    @Override
    public ScoreViewHolder onCreateRealmViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_scores_list, parent, false);
        return (new ScoreViewHolder((FrameLayout) view));
    }

    @Override
    public void onBindRealmViewHolder(ScoreViewHolder viewHolder, int position) {
        if(position < 0 || position >= realmResults.size())
            return;

        Score scoreObject = realmResults.get(position);

        viewHolder.mLeagueName.setText(UtilityHelper.getLeague(mScoresContext.getResources(),
                Integer.parseInt(scoreObject.getLeague())));

        viewHolder.mHomeName.setText(scoreObject.getHome());
        viewHolder.mAwayName.setText(scoreObject.getAway());

        viewHolder.mMatchDay.setText("Match day: " + scoreObject.getMatchDay());
        viewHolder.mScore.setText(UtilityHelper.getScores(scoreObject.getHomeGoals(), scoreObject.getAwayGoals()));

        viewHolder.mHomeCrest.setImageResource(UtilityHelper.getTeamCrestByTeamName(scoreObject.getHome()));
        viewHolder.mAwayCrest.setImageResource(UtilityHelper.getTeamCrestByTeamName(scoreObject.getAway()));
    }

    protected void setupComponent(AppComponent component) {
        component.inject(this);
    }

    public class ScoreViewHolder extends RealmViewHolder {
        @Bind(R.id.score_item_league) TextView mLeagueName;

        @Bind(R.id.score_item_home_name) TextView mHomeName;
        @Bind(R.id.score_item_away_name) TextView mAwayName;

        @Bind(R.id.score_item_match_day) TextView mMatchDay;
        @Bind(R.id.score_item_score) TextView mScore;

        @Bind(R.id.score_item_home_crest) ImageView mHomeCrest;
        @Bind(R.id.score_item_away_crest) ImageView mAwayCrest;

        public ScoreViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    mOnClickListener.onScoreItemClicked(getAdapterPosition(), view);
                }
            });
        }

        @OnClick(R.id.score_item_share_button) void onShare() {
            String messageText = mHomeName.getText()
                    + " " + mScore.getText() + " " + mAwayName.getText()
                    + " " + ConstantValues.HASH_TAG_FOOTBALL;
            mOnClickListener.onShareScoreItemClicked(messageText);
        }
    }
}
