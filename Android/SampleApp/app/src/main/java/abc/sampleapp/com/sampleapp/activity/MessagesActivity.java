package abc.sampleapp.com.sampleapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import abc.sampleapp.com.sampleapp.R;
import abc.sampleapp.com.sampleapp.adapters.ActionableMessageAdapter;
import abc.sampleapp.com.sampleapp.models.Action;
import abc.sampleapp.com.sampleapp.models.ActionableMessage;
import abc.sampleapp.com.sampleapp.service.ActionsService;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MessagesActivity extends ActionBarActivity {

    @Bind(R.id.options_list)
    ListView listView;

    ActionableMessageAdapter adapter;
    ArrayList<Action> items = new ArrayList<Action>();
    // 10.0.2.2 - localhost address when running an emulators
    String serviceEndPoint = "http://10.0.2.2:8080/sampleapp/api/update";

    TextView headerText;
    ActionableMessage message;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.messages_list);
        ButterKnife.bind(this);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.action_options_header, listView, false);
        headerText = (TextView)header.findViewById(R.id.headerText);
        listView.addHeaderView(header);

        adapter = new ActionableMessageAdapter(this, items);
        listView.setAdapter(adapter);
        reloadListView(message);
        new FetchActionsTask().execute(serviceEndPoint);
    }

    private class FetchActionsTask extends AsyncTask<String, Void, ActionableMessage> {

        @Override
        protected ActionableMessage doInBackground(String... params) {
            ActionsService actionsService = new ActionsService(getApplicationContext());
            String param = params[0];
            return actionsService.getActionableMessage(param);
        }

        @Override
        protected void onPostExecute(ActionableMessage result) {
            message = result;
            items.clear();
            if (result != null){
                for (Action action : result.getPosibleActions()){
                    items.add(action);
                }
            }
            reloadListView(result);
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    public void reloadListView(final ActionableMessage result){
        if (result != null){
            listView.post(new Runnable() {
                @Override
                public void run() {
                    message = result;
                    String itemHeader = result.getMessage().getText();
                    headerText.setText(Html.fromHtml(itemHeader));
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    @OnItemClick(R.id.options_list) void onItemClick(int position) {
        try{
            Action action = adapter.getItem(position -1);
            if (action != null){
                if (!action.getType().equalsIgnoreCase("end"))
                    getResponseForAction(action);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getResponseForAction(Action action){
        new FetchActionsTask().execute(serviceEndPoint.concat("?id=").concat(action.getActionId().toString()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.messages_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action buttons
        switch(item.getItemId()) {
            case R.id.action_done:

                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
