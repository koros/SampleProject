package abc.sampleapp.com.sampleapp.models;

import android.graphics.drawable.Drawable;

public class NavigationDrawerMenuItem {
	public int Id;
    public Drawable Drawable;
    public String Name;

    public NavigationDrawerMenuItem(int id, Drawable drawable, String name) {

        Id = id;
        Drawable = drawable;
        Name = name;

    }

}
