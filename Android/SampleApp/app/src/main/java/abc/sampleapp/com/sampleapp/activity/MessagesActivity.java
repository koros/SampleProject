package abc.sampleapp.com.sampleapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import abc.sampleapp.com.sampleapp.R;

public class MessagesActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list);


    }
}
