package barqsoft.footballscores.Ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import barqsoft.footballscores.AppComponent;
import barqsoft.footballscores.DataSource.FetchService;
import barqsoft.footballscores.Database.Model.Score;
import barqsoft.footballscores.Database.ScoresRecyclerViewAdapter;
import barqsoft.footballscores.R;
import barqsoft.footballscores.Ui.DataUpdatedReceiver;
import butterknife.Bind;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ScoresListFragment extends AbstractBaseFragment implements
        ScoresRecyclerViewAdapter.OnScoreItemClickListener {

    @Inject Realm realm;
    @Bind(R.id.scores_recycler_view) RealmRecyclerView mRecyclerView;

    public static final int RESULT_OK = 100;
    public DataUpdatedReceiver mDataUpdatedReceiver;

    protected ScoresRecyclerViewAdapter mScoresRecyclerViewAdapter;
    protected String mScoresDate;

    public static String EXTRA_DATE = "date_argument";

    public static ScoresListFragment newInstance(String format) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_DATE, format);
        Fragment fragment = new ScoresListFragment();
        fragment.setArguments(bundle);
        return (ScoresListFragment) fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_scores_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(R.id.action_refresh == item.getItemId()) {
            updateScores();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);

        mScoresDate = getArguments().getString(EXTRA_DATE);
        setupServiceReceiver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_scores_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);

        RealmResults<Score> scoreModels = realm.where(Score.class).equalTo("date", mScoresDate)
                .findAllSorted("id", Sort.ASCENDING);

        mScoresRecyclerViewAdapter = new ScoresRecyclerViewAdapter(getActivity(),
                scoreModels, true, true, null);
        mScoresRecyclerViewAdapter.setOnScoreItemClickListener(this);
        mRecyclerView.setAdapter(mScoresRecyclerViewAdapter);

        mRecyclerView.setOnRefreshListener(
                new RealmRecyclerView.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        updateScores();
                    }
                });
    }

    @Override
    public void onScoreItemClicked(int position, View view) {
    }

    @Override
    public void onShareScoreItemClicked(String text) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(shareIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    protected void updateScores() {
        Intent intent = new Intent(getActivity(), FetchService.class);
        intent.putExtra("receiver", mDataUpdatedReceiver);
        getActivity().startService(intent);
    }

    public void setupServiceReceiver() {
        mDataUpdatedReceiver = new DataUpdatedReceiver(new Handler());
        mDataUpdatedReceiver.setReceiver(new DataUpdatedReceiver.Receiver() {
            @Override
            public void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == RESULT_OK)
                    mRecyclerView.setRefreshing(false);
            }
        });
    }

    @Override
    protected void setupComponent(AppComponent component) {
        component.inject(this);
    }
}
