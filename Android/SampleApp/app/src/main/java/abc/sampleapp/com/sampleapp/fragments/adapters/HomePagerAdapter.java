package abc.sampleapp.com.sampleapp.fragments.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import abc.sampleapp.com.sampleapp.fragments.HomeActivityBaseFragment;


public class HomePagerAdapter extends FragmentStatePagerAdapter{
	
	private String[] tabs;
	
	public HomePagerAdapter(FragmentManager fragmentManager, String[] tabs) {
		 super(fragmentManager);
		 this.tabs = tabs;
	}

	@Override
    public Fragment getItem(int position) {
        return HomeActivityBaseFragment.create(position, tabs[position]);
    }
	
    @Override
    public int getCount() {
        return tabs.length;
    }
    
}
