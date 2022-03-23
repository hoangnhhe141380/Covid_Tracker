package com.example.covid_tracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_tracker.R;
import com.example.covid_tracker.model.MedicalDeclaredData;

import java.util.List;

public class MedicalDeclaredAdapter extends RecyclerView.Adapter<MedicalDeclaredAdapter.MedicalDeclaredViewHolder> {

    private List<MedicalDeclaredData> listSymptoms;

    public MedicalDeclaredAdapter(List<MedicalDeclaredData> listSymptoms) {
        this.listSymptoms = listSymptoms;
    }

    @NonNull
    @Override
    public MedicalDeclaredViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layout_medical_declared_data, parent, false);
        return new MedicalDeclaredViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalDeclaredViewHolder holder, int position) {
        MedicalDeclaredData symptom = listSymptoms.get(position);
        if (symptom == null) return;

        holder.tv_date_declare.setText("Date declare: "+symptom.getDateDeclare());
        holder.tv_date_start.setText("Date start: "+symptom.getDateStart());
        holder.tv_date_end.setText("Date end: "+symptom.getDateEnd());
        holder.tv_is_contact_f0.setText("Contact with f0: "+(symptom.isContactWithF0() ? "Yes" : "No"));
        holder.tv_place_contact.setText("Place contact: "+symptom.getPlaceContact());
        holder.tv_symptoms_mdd.setText("Symptoms: "+symptom.getSymptoms());
    }

    @Override
    public int getItemCount() {
        return listSymptoms != null ? listSymptoms.size() : 0;
    }

    public class MedicalDeclaredViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_date_start;
        private TextView tv_date_end;
        private TextView tv_is_contact_f0;
        private TextView tv_place_contact;
        private TextView tv_symptoms_mdd;
        private TextView tv_date_declare;

        public MedicalDeclaredViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date_declare = itemView.findViewById(R.id.tv_date_declare);
            tv_date_start = itemView.findViewById(R.id.tv_date_start);
            tv_date_end = itemView.findViewById(R.id.tv_date_end);
            tv_is_contact_f0 = itemView.findViewById(R.id.tv_is_contact_f0);
            tv_place_contact = itemView.findViewById(R.id.tv_place_contact);
            tv_symptoms_mdd = itemView.findViewById(R.id.tv_symptoms_mdd);

        }
    }
}
