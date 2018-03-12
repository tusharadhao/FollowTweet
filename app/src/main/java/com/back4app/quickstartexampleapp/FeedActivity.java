package com.back4app.quickstartexampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
   // private RecyclerView.Adapter mAdapter;
  //  private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
    //    mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
     //   mLayoutManager = new LinearLayoutManager(this);
   //     mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
   //     mAdapter = new MyAdapter(myDataset);
   //     mRecyclerView.setAdapter(mAdapter);



        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        ParseQuery<ParseObject> query= ParseQuery.getQuery("Tweet");
        query.whereContainedIn("username", ParseUser.getCurrentUser().getList("isFollowing"));
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        ArrayList<String> lan= new ArrayList<String>();
                        ArrayList<String> man= new ArrayList<String>();
                      //  List<Map<String, String>> tweetdata= new ArrayList<Map<String, String>>();
                        for(ParseObject tweet: objects){
                            Map<String, String> tweetInfo= new HashMap<String, String>();
                            lan.add( tweet.getString("tweet"));
                            man.add( tweet.getString("username"));

                        }


                        mRecyclerView.setAdapter(new MyAdapter(lan, man));
                    }
                }

            }
        });



    }
}
