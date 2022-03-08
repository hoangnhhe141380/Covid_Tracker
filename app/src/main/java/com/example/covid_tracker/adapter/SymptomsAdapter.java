package com.example.covid_tracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_tracker.R;
import com.example.covid_tracker.model.Symptom;

import java.util.List;

public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.SymptomsViewHolder>{

    private List<Symptom> listSymptoms;


    public SymptomsAdapter(List<Symptom> listSymptoms) {
        this.listSymptoms = listSymptoms;
    }

    @NonNull
    @Override
    public SymptomsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layout_symptoms_data, parent, false);
        return new SymptomsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SymptomsViewHolder holder, int position) {
        Symptom symptom = listSymptoms.get(position);
        if (symptom == null) return;


        holder.i_symptom.setImageResource(symptom.getImage());
        holder.tv_title.setText(symptom.getTitle());
        holder.tv_description.setText(symptom.getDescription());
    }

    @Override
    public int getItemCount() {
        return listSymptoms != null ? listSymptoms.size() : 0;
    }

    public class SymptomsViewHolder extends RecyclerView.ViewHolder{

        private ImageView i_symptom;
        private TextView tv_title;
        private TextView tv_description;

        public SymptomsViewHolder(@NonNull View itemView) {
            super(itemView);

            i_symptom = itemView.findViewById(R.id.i_symptom);
            tv_title = itemView.findViewById(R.id.tv_symptom_title);
            tv_description = itemView.findViewById(R.id.tv_symptom_description);


        }
    }
}
