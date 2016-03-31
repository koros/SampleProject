package abc.sampleapp.com.sampleapp.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Config;
import android.util.Log;
import android.view.View;

import abc.sampleapp.com.sampleapp.BuildConfig;

/**
 * An assortment of UI helpers.
 */
public class SysUtils {
	private static final String TAG = "SysUtils";

    public static final int ANIMATION_FADE_IN_TIME = 250;

    private static String mUserAgent = null;

    public static String getUserAgent(Context mContext) {
        if (mUserAgent == null) {
            mUserAgent = "SampleApp";
            try {
                String packageName = mContext.getPackageName();
                String version = mContext.getPackageManager().getPackageInfo(packageName, 0).versionName;
                mUserAgent = mUserAgent + " (" + packageName + "/" + version + ")";
                Log.e(TAG, "User agent set to: " + mUserAgent);
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Unable to find self by package name", e);
            }
        }
        return mUserAgent;
    }

    /**
     * A hashing method that changes a string (like a URL) into a hash suitable for using as a disk filename.
     */
    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        // http://stackoverflow.com/questions/332079
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static boolean isGreaterThanGingerBread() {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    public static boolean hasICS() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    public static boolean hasJellyBeanMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static boolean isHoneycombTablet(Context context) {
        return hasHoneycomb() && isTablet(context);
    }

    private static final long sAppLoadTime = System.currentTimeMillis();

    public static long getCurrentTime(final Context context) {
        if (BuildConfig.DEBUG) {
            return context.getSharedPreferences("mock_data", Context.MODE_PRIVATE).getLong("mock_current_time", System.currentTimeMillis())
                    + System.currentTimeMillis() - sAppLoadTime;
        } else {
            return System.currentTimeMillis();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void setActivatedCompat(View view, boolean activated) {
        if (hasHoneycomb()) {
            view.setActivated(activated);
        }
    }

    public static int getRandom(int min, int max) {
        try {
            if (min == max) {
                return min;
            }
            max = max + 1;// make the max inclusive. (min is already inclusive)
            Random rand = new Random();
            int value = rand.nextInt(max - min) + min;
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return min;
        }
    }

}
