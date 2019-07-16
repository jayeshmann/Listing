package com.xtremus.listing.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xtremus.listing.R;
import com.xtremus.listing.model.GetListing;

import java.util.List;

/**
 * Created by Jayesh Mann on 15,July,2019.
 * TSA Pvt. Ltd.
 * jayeshmann06@gmail.com
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private List<GetListing> getListingList;


    public MainAdapter(Context context1,List<GetListing> list)
    {
        this.context = context1;
        this.getListingList = list;

    }
    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.single_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {

        GetListing getListing = getListingList.get(position);


        holder.textEnrollment.setText((CharSequence) getListing.getCadetDetails().subList(0,1));
        holder.textCadetName.setText((CharSequence) getListing.getCadetDetails().subList(1,2));

    }

    @Override
    public int getItemCount() {
        return getListingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textCadetName, textEnrollment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textCadetName = itemView.findViewById(R.id.textView2);
            textEnrollment = itemView.findViewById(R.id.textView);
        }
    }
}
