package barqsoft.footballscores.Ui.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import barqsoft.footballscores.AppComponent;
import barqsoft.footballscores.Database.PagerAdapter;
import barqsoft.footballscores.R;
import barqsoft.footballscores.Ui.FootballOnTabSelectedListener;
import butterknife.Bind;

public class PagerFragment extends AbstractBaseFragment implements AppBarLayout.OnOffsetChangedListener {
    @Bind(R.id.pager_view) ViewPager mViewPager;
    @Bind(R.id.pager_app_bar) AppBarLayout mAppBarLayout;
    @Bind(R.id.pager_tab_layout) TabLayout mTabLayout;

    protected PagerAdapter mPagerAdapter;
    protected int mAppBarOffset = 0;
    protected int mCurrentPage  = PagerAdapter.TODAY_POSITION;
    protected String CURRENT_PAGE_STATE = "current_page";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup parent,
                             Bundle bundle) {
        return inflater.inflate(R.layout.fragment_pager, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);

        if(bundle != null && bundle.containsKey(CURRENT_PAGE_STATE))
            mCurrentPage = bundle.getInt(CURRENT_PAGE_STATE);

        mPagerAdapter = new PagerAdapter((Context) getActivity(), getChildFragmentManager());
        mTabLayout.setTabsFromPagerAdapter(mPagerAdapter);

        TabLayout.OnTabSelectedListener listener = new FootballOnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
        };
        mTabLayout.setOnTabSelectedListener(listener);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setOffscreenPageLimit(PagerAdapter.getPageSize());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(mCurrentPage);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        mAppBarOffset = offset;
    }

    @Override
    public void onDestroy() {
        mPagerAdapter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void setupComponent(AppComponent component) {
        component.inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAppBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    public void onStop() {
        mAppBarLayout.removeOnOffsetChangedListener(this);
        super.onStop();
    }
}
