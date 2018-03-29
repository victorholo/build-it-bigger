package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jokesappmodule.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Victor Holotescu on 30-03-2018.
 */
public class JokesAsyncTask extends AsyncTask<Void, Void, String> {
    private MyApi mApiService = null;
    ProgressBar mProgressBar;
    Activity mActivity;

    public JokesAsyncTask(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressBar = mActivity.findViewById(R.id.progressbar);
        if(mProgressBar != null){
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        if(mApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            mApiService = builder.build();
        }

        try {
            return mApiService.getJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(mProgressBar != null){
            mProgressBar.setVisibility(View.INVISIBLE);
        }
        if(result == null) return;

        Intent intent = new Intent(mActivity, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_EXTRA, result);
        mActivity.startActivity(intent);
    }
}