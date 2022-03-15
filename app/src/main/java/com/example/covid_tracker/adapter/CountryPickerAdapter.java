package com.example.covid_tracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.covid_tracker.R;
import com.example.covid_tracker.model.Summary;

import java.util.List;

public class CountryPickerAdapter extends ArrayAdapter<Summary> {
    public CountryPickerAdapter(@NonNull Context context, int resource, @NonNull List<Summary> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country_selected, parent, false);
        TextView tv_country_selected = convertView.findViewById(R.id.tv_country_selected);
        Summary summary = this.getItem(position);
        if(summary != null){
            tv_country_selected.setText(summary.getCountry());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country_picker, parent, false);
        TextView tv_country_picker = convertView.findViewById(R.id.tv_country_picker);
        Summary summary = this.getItem(position);
        if(summary != null){
            tv_country_picker.setText(summary.getCountry());
        }
        return convertView;
    }
}
