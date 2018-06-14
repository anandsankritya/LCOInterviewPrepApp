package com.interviewprepapp.lco.lcointerviewprepapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private ArrayList<QuestionsAndAnswers> questionsList;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionsList = new ArrayList<>();

        new FetchJsonTask().execute("https://learncodeonline.in/api/android/datastructure.json");

        RecyclerView questionsListRv = findViewById(R.id.questions_list_rv);
        questionsListRv.setHasFixedSize(true);
        questionsListRv.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, questionsList);
        questionsListRv.setAdapter(myRecyclerViewAdapter);

        ImageView bannerImage = findViewById(R.id.banner_image_view);
        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://courses.learncodeonline.in/");
                Intent learnCodeOnlineIntent = new Intent(Intent.ACTION_VIEW, webpage);
                if (learnCodeOnlineIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(learnCodeOnlineIntent);
                }
            }
        });


    }

    private class FetchJsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder responseStrBuilder = new StringBuilder();

                String line = "";

                while ((line = reader.readLine()) != null) {
                    responseStrBuilder.append(line);
                }

                return responseStrBuilder.toString();


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("questions");
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String question = object.getString("question");
                    String answer = object.getString("Answer");
                    questionsList.add(new QuestionsAndAnswers(question, answer));
                    myRecyclerViewAdapter.notifyDataSetChanged();
                    Log.d("Items", ""+myRecyclerViewAdapter.getItemCount());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
