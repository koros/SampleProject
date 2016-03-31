package abc.sampleapp.com.sampleapp.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import abc.sampleapp.com.sampleapp.R;
import abc.sampleapp.com.sampleapp.util.SysUtils;


/**
 * A {@link android.preference.PreferenceActivity} that presents a set of application settings. On handset devices, settings are presented as a single list. On tablets, settings
 * are split by category, with category headers shown to the left of the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html"> Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends PreferenceActivity {

    private static final String TAG = "SettingsActivity";

    PackageInfo packageInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @SuppressLint("NewApi")
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupSimplePreferencesScreen();

    }

    /**
     * Shows the simplified settings UI if the device configuration if the device configuration dictates that a simplified, single-pane UI should be shown.
     */
    private void setupSimplePreferencesScreen() {

        try {
            addPreferencesFromResource(R.xml.pref_general);

            Preference pref = findPreference("app_version_key");

            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

            if (pref != null) {
                pref.setSummary(packageInfo.versionName);
            }

            ListView lv = getListView();

            /** Create a footer view and disable footer divider **/

            TextView view = new TextView(this);
            view.setLines(0);
            lv.addFooterView(view, null, false);
            lv.setFooterDividersEnabled(false);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();// the other method is currently not preserving state correctly
	        return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}

