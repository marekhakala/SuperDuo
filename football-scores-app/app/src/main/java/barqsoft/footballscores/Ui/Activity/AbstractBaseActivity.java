package barqsoft.footballscores.Ui.Activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import barqsoft.footballscores.FootballScoresApplication;
import butterknife.ButterKnife;

public abstract class AbstractBaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setTitle(int id) {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setTitle(id);
        else
            super.setTitle(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FootballScoresApplication.get(this).getRefWatcher().watch(this);
    }
}
