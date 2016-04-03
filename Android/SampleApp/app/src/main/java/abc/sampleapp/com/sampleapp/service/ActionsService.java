package abc.sampleapp.com.sampleapp.service;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import abc.sampleapp.com.sampleapp.models.ActionableMessage;
import abc.sampleapp.com.sampleapp.models.Response;

/**
 * Created by koros on 4/3/16.
 */
public class ActionsService {

    Context context = null;

    public ActionsService(Context context){
        this.context = context;
    }

    public ActionableMessage getActionableMessage(String serviceEndpoint) {
        try {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(serviceEndpoint);
            HttpResponse response;
            BufferedReader bufferedReader;
            Response syncResponse;

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

            try {
                // Execute HTTP Post Request
                response = httpclient.execute(httppost);

                Log.d("", "\n----------------------------------------------------------------\n");
                Log.d("", httppost.getURI().toString());
                Log.d("", "\n----------------------------------------------------------------\n");

                InputStream stream = response.getEntity().getContent();
                bufferedReader = new BufferedReader( new InputStreamReader(stream));

                StringBuilder sb = new StringBuilder();
                String line = null;
                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                    Log.d("", line);
                }


                syncResponse = mapper.readValue(sb.toString(), Response.class);
                ActionableMessage actionableMessage = syncResponse.getActionableMessage();

                return actionableMessage;

            }catch (Exception e) {
                Log.e("", e.getMessage(), e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
