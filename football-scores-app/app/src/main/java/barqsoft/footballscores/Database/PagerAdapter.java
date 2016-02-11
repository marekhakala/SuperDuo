package barqsoft.footballscores.Database;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import barqsoft.footballscores.R;
import barqsoft.footballscores.Ui.Fragment.ScoresListFragment;

public final class PagerAdapter extends FragmentStatePagerAdapter {
    public static int TODAY_POSITION = 2;

    protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    protected static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("EEEE", Locale.US);

    protected Context mContext = null;
    protected Map<Integer, ScoresListFragment> mPagesMap = new HashMap<Integer, ScoresListFragment>();

    public PagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Calendar calendar = Calendar.getInstance(Locale.US);
        calendar.roll(Calendar.DAY_OF_MONTH, position - TODAY_POSITION);
        ScoresListFragment fragment = ScoresListFragment.newInstance(DATE_FORMAT.format(calendar.getTime()));
        mPagesMap.put(position, fragment);
        return fragment;
    }

    public static int getPageSize() {
        return 5;
    }

    @Override
    public int getCount() {
        return getPageSize();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Calendar calendar = Calendar.getInstance(Locale.US);
        calendar.roll(Calendar.DAY_OF_WEEK, position - TODAY_POSITION);

        return getDayName(mContext, calendar.getTimeInMillis());
    }

    public ScoresListFragment getFragment(int key) {
        return mPagesMap.get(key);
    }

    public void onDestroy() {
        mPagesMap.clear();
    }

    public String getDayName(Context context, long dateInMillis) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(dateInMillis);

        int julianDay = cal.get(Calendar.DAY_OF_YEAR);
        int currentJulianDay = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);

        if (julianDay == currentJulianDay)
            return context.getString(R.string.today);
        else if (julianDay == currentJulianDay - 1)
            return context.getString(R.string.yesterday);
        else if (julianDay == currentJulianDay + 1)
            return context.getString(R.string.tomorrow);
        else
            return DAY_FORMAT.format(dateInMillis);
    }
}
