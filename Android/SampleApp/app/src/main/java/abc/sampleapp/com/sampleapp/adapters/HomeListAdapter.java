package abc.sampleapp.com.sampleapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import abc.sampleapp.com.sampleapp.R;
import abc.sampleapp.com.sampleapp.models.NavigationDrawerMenuItem;

/**
 * Created by Geoffrey Koros on 5/22/2015.
 */
public class HomeListAdapter extends ArrayAdapter<NavigationDrawerMenuItem> {

    private final Context context;
    public static int selectedIndex;

    static class ViewHolder {
        TextView text;
        public ImageView image;
        public ImageView accessor;
    }

    public HomeListAdapter(Context context, ArrayList<NavigationDrawerMenuItem> menuItems) {
        super(context, R.layout.drawer_list_item, menuItems);
        this.context = context;

    }

    @SuppressWarnings("deprecation")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //NavigationDrawerMenuItem item = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_row, null);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.text1);

            Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"fonts/roboto/Roboto-Regular.ttf");
            viewHolder.text.setTypeface(typeface);

            //viewHolder.image = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //viewHolder.text.setText(Html.fromHtml(item.Name));
        //viewHolder.image.setImageDrawable(item.Drawable);
        //viewHolder.image.setVisibility(View.GONE);

        //viewHolder.accessor.setBackgroundDrawable(getContext().getResources().getDrawable(selectedIndex == position ? R.drawable.blue_background : R.drawable.white_background));
        //convertView.setBackgroundDrawable(getContext().getResources().getDrawable(selectedIndex == position ? R.drawable.nav_highlighted : R.drawable.white_background));

        return convertView;
    }

    @Override
    public int getCount() {
        return 5;
    }
}