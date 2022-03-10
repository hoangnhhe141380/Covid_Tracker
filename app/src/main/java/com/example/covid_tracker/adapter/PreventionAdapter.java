package com.example.covid_tracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_tracker.R;
import com.example.covid_tracker.model.Prevention;

import java.util.List;

public class PreventionAdapter extends RecyclerView.Adapter<PreventionAdapter.PreventionViewHolder>{

    private List<Prevention> listSymptoms;

    public PreventionAdapter(List<Prevention> listSymptoms) {
        this.listSymptoms = listSymptoms;
    }

    @NonNull
    @Override
    public PreventionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layout_prevention_data, parent, false);
        return new PreventionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreventionViewHolder holder, int position) {
        Prevention symptom = listSymptoms.get(position);
        if (symptom == null) return;

        holder.i_prevention.setImageResource(symptom.getImage());
        holder.tv_title.setText(symptom.getTitle());
        holder.tv_description.setText(symptom.getDescription());
    }

    @Override
    public int getItemCount() {
        return listSymptoms != null ? listSymptoms.size() : 0;
    }

    public class PreventionViewHolder extends RecyclerView.ViewHolder{

        private ImageView i_prevention;
        private TextView tv_title;
        private TextView tv_description;

        public PreventionViewHolder(@NonNull View itemView) {
            super(itemView);

            i_prevention = itemView.findViewById(R.id.i_prevention);
            tv_title = itemView.findViewById(R.id.tv_prevention_title);
            tv_description = itemView.findViewById(R.id.tv_prevention_description);

        }
    }
}
