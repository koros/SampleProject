package abc.sampleapp.com.sampleapp.activity;

import android.os.Bundle;

import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import abc.sampleapp.com.sampleapp.R;
import abc.sampleapp.com.sampleapp.fragments.adapters.HomePagerAdapter;

public class HomeActivity extends AbstractNavigationDrawerActivity{

    private static final String TAG = "HomeActivity";

    private HomePagerAdapter adapter;
    private String[] tabs = {"Home", "Recent","Favorites"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        adapter = new HomePagerAdapter(getSupportFragmentManager(), tabs);
        setupScrollViewHeaderStripForHeadlines();
        mPager.setAdapter(adapter);

        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setSubtitle("Online");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    @Override
    protected void onSyncComplete(boolean success, String error) {
        supportInvalidateOptionsMenu();
    }

    @Override
    public void onTabSelected(int position) {
        try {
            mPager.setCurrentItem(position, true);
            for (int i = 0; i < tabs.length; i++) {
                View tab = mHorizontalScrollView.findViewWithTag(i);

                tab.findViewById(R.id.underline).setBackgroundDrawable(getResources().getDrawable(i == position ? R.drawable.white_background : R.drawable.blue_background));
            }

            View prevView = mHorizontalScrollView.findViewWithTag(position > 0 ? position - 1 : position);
            int scrollX = (prevView.getLeft() + (prevView.getWidth() * 1 / 3));
            mHorizontalScrollView.smoothScrollTo(position > 0 ? scrollX : 0, 0);

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    private void setupScrollViewHeaderStripForHeadlines() {
        mHorizontalScrollView.setVisibility(View.VISIBLE);

        LinearLayout tabsContainer = (LinearLayout) mHorizontalScrollView.findViewById(R.id.tabsLayout);
        tabsContainer.removeAllViews();

        for (int i = 0; i < tabs.length; i++) {
            View tab = inflater.inflate(R.layout.tab, null);
            tabsContainer.addView(tab);
            tab.setTag(i);

            TextView tv = (TextView) tab.findViewById(R.id.text);
            Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/roboto/Roboto-Regular.ttf");
            tv.setTypeface(typeface);
            tv.setText(i < tabs.length ? tabs[i] : "");
            tab.setOnClickListener(mOnClickListener);
        }

        onTabSelected(0);//select the first tab by default
    }
}
