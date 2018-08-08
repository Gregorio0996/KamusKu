package com.example.gregorio.kamusku;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class IndonesiaAdapter extends RecyclerView.Adapter<IndonesiaAdapter.IndonesiaHolder> {
    private ArrayList<IndoModel> mData = new ArrayList<>();
    private Activity activity;
    private LayoutInflater mInflater;

    public IndonesiaAdapter(Activity activity){
        mInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.activity = activity;
    }

    @Override
    public IndonesiaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_kata,parent, false);
        return new IndonesiaHolder(view);
    }

    public void addItem(ArrayList<IndoModel> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(IndonesiaHolder holder, int position) {
    holder.textViewKata.setText(mData.get(position).getKata());
    holder.textViewKata.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
        @Override
        public void onItemClicked(View view, int position) {
            Intent intent = new Intent(activity, DetailActivity.class);
            //intent.putExtra(EXTRA_KATA, IndoModel.get(position).ge)
        }
    }));
    }

    @Override
    public int getItemViewType(int position){
        return 0;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class IndonesiaHolder extends RecyclerView.ViewHolder {
        private TextView textViewKata;

        public IndonesiaHolder(View view) {
            super(view);
            textViewKata = (TextView)view.findViewById(R.id.textKata);

        }
    }
}
