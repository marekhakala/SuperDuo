package barqsoft.footballscores.Ui.Activity;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import barqsoft.footballscores.R;
import barqsoft.footballscores.Ui.Fragment.PagerFragment;

public class MainActivity extends AbstractBaseActivity {

    public static String MAIN_FRAGMENT_TAG = "fragment_main";
    protected PagerFragment mPagerFragment;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        mPagerFragment = new PagerFragment();
        replaceFragment(mPagerFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent startAbout = new Intent(this, AboutActivity.class);
            startActivity(startAbout);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void replaceFragment(Fragment fragment) {
        if(fragment instanceof PagerFragment) {
            mPagerFragment = (PagerFragment) fragment;

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, fragment, MAIN_FRAGMENT_TAG)
                    .commit();
        }
    }
}
