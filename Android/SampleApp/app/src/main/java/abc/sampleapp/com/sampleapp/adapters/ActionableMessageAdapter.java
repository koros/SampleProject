package abc.sampleapp.com.sampleapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import abc.sampleapp.com.sampleapp.R;
import abc.sampleapp.com.sampleapp.models.Action;
import abc.sampleapp.com.sampleapp.models.NavigationDrawerMenuItem;

/**
 * Created by koros on 4/3/16.
 */
public class ActionableMessageAdapter extends ArrayAdapter<Action> {

    private final Context context;

    static class ViewHolder {
        TextView text;
        EditText editText;
        public ImageView image;
        public ImageView accessor;
    }

    public ActionableMessageAdapter(Context context, ArrayList<Action> items) {
        super(context, R.layout.list_item_row, items);
        this.context = context;
    }

    @SuppressWarnings("deprecation")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Action item = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_row2, null);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.text1);
            viewHolder.editText = (EditText) convertView.findViewById(R.id.response_input);

            Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"fonts/roboto/Roboto-Regular.ttf");
            viewHolder.text.setTypeface(typeface);

            viewHolder.image = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.accessor = (ImageView) convertView.findViewById(R.id.accessor);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text.setText(Html.fromHtml(item.getName()));
        viewHolder.image.setVisibility(View.GONE);

        if (item.getType().equalsIgnoreCase("input"))
            viewHolder.editText.setVisibility(View.VISIBLE);
        else
            viewHolder.editText.setVisibility(View.GONE);

        viewHolder.accessor.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.blue_background));

        return convertView;
    }
}
