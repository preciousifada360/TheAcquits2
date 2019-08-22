package com.skillseeds.theacquits.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skillseeds.theacquits.Models.trainingModel;
import com.skillseeds.theacquits.R;

import java.util.List;

public class trainingAdaptor extends RecyclerView.Adapter<trainingAdaptor.trainingViewHolder>{


    private Context mCtx;
    private List<trainingModel>trainingModelList;

    public trainingAdaptor(Context mCtx, List<trainingModel> trainingModelList) {
        this.mCtx = mCtx;
        this.trainingModelList = trainingModelList;
    }

    @NonNull
    @Override
    public trainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout_training, null);
        return new trainingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull trainingViewHolder holder, int position) {
        trainingModel training= trainingModelList.get(position);
        holder.training_title.setText(training.getTitle());
        holder.training_id.setText(training.getId());
        holder.training_description.setText(training.getDescription());
        holder.training_icon_url.setText(training.getIconUrl());

    }

    @Override
    public int getItemCount() {
        return trainingModelList.size();
    }

    class trainingViewHolder extends RecyclerView.ViewHolder {

        TextView training_id, training_title, training_description;
        TextView training_icon_url;

        public trainingViewHolder(@NonNull View itemView) {
            super(itemView);

            training_icon_url=itemView.findViewById(R.id.training_icon_url_list);
            training_id=itemView.findViewById(R.id.training_id_list);
            training_title=itemView.findViewById(R.id.training_title_list);
            training_description=itemView.findViewById(R.id.training_description_list);


        }
    }
}
