package barqsoft.footballscores.Ui.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import barqsoft.footballscores.R;

public class AboutActivity extends AbstractBaseActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.action_settings == item.getItemId())
            return true;

        return super.onOptionsItemSelected(item);
    }
}
