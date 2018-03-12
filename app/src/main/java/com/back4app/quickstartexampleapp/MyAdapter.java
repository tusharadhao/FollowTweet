package com.back4app.quickstartexampleapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dell on 01/03/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> mDataset= new ArrayList<String>();
    private  ArrayList<String>  datatat= new ArrayList<String>();



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
       // public TextView mTextView;
        TextView txt;
        TextView txt2;
        public ViewHolder(View itemView) {

            super(itemView);
            txt= (TextView) itemView.findViewById(R.id.textView1);
            txt2= (TextView) itemView.findViewById(R.id.textView2);


         //   mTextView = v;


        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String> myDataset, ArrayList<String> datat) {
        mDataset = myDataset;
        datatat= datat;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.recycler_data_set, parent, false);


    //    TextView v = (TextView) LayoutInflater.from(parent.getContext())
              //  .inflate(R.layout.recycler_data_set, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       // holder.mTextView.setText(mDataset[position]);


        String title= mDataset.get(position);
        String tit= datatat.get(position);
        holder.txt.setText(title);
        holder.txt2.setText(tit);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
      //  return datatat.length;
    }
}
