package abc.sampleapp.com.sampleapp.fragments;

import java.util.ArrayList;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.manuelpeinado.fadingactionbar.extras.actionbarcompat.FadingActionBarHelper;

import abc.sampleapp.com.sampleapp.R;
import abc.sampleapp.com.sampleapp.adapters.HomeListAdapter;
import abc.sampleapp.com.sampleapp.models.NavigationDrawerMenuItem;


public class HomeActivityFragment extends HomeActivityBaseFragment implements OnItemClickListener {

	private static final String TAG = "HomeActivityFragment";

	private ListView list;
	private HomeListAdapter listAdapter;

    private ProgressBar progress;
    private FadingActionBarHelper mFadingHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        listAdapter = new HomeListAdapter(getActivity(), new ArrayList<NavigationDrawerMenuItem>());
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.list, container, false);
        
        list = (ListView) rootView.findViewById(R.id.list);
        
        LayoutInflater infalter = getActivity().getLayoutInflater();
        ViewGroup header = (ViewGroup) infalter.inflate(R.layout.list_header, list, false);
        list.addHeaderView(header);
        
     	list.setAdapter(listAdapter);
        progress = (ProgressBar) rootView.findViewById(R.id.progress);
        list.setOnItemClickListener(this);
        progress.setVisibility(View.GONE);
     	list.setOnItemClickListener(this);
     	
		return rootView;
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
        Log.d(TAG, "News Item Clicked");
        doSomething();
	}

	private void doSomething() {

	}

}
