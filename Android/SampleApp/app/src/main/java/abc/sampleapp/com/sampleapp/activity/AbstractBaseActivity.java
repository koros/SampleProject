package abc.sampleapp.com.sampleapp.activity;

import java.util.ArrayList;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import abc.sampleapp.com.sampleapp.R;
import abc.sampleapp.com.sampleapp.models.NavigationDrawerMenuItem;


public class AbstractBaseActivity extends ActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	public void doSync(){

	}

	protected void onSyncComplete(boolean success, String error) {

	}

	public ArrayList<NavigationDrawerMenuItem> buildMenuItems(Context context) {
		
		ArrayList<NavigationDrawerMenuItem> menuItems = new ArrayList<NavigationDrawerMenuItem>();
		
		NavigationDrawerMenuItem home = new NavigationDrawerMenuItem(0, context.getResources().getDrawable(R.drawable.navigation_next_item), "Home");
		menuItems.add(home);
		NavigationDrawerMenuItem headlines = new NavigationDrawerMenuItem(0, context.getResources().getDrawable(R.drawable.navigation_next_item), "Messages");
		menuItems.add(headlines);
		NavigationDrawerMenuItem settings = new NavigationDrawerMenuItem(0, context.getResources().getDrawable(R.drawable.navigation_next_item), "Settings");
		menuItems.add(settings);
		
		return menuItems;
	}
}
