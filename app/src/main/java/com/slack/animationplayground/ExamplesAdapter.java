package com.slack.animationplayground;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExamplesAdapter extends RecyclerView.Adapter<ExamplesAdapter.ViewHolder> {

    private ArrayList<Example> exampleArrayList;

    public ExamplesAdapter(@NonNull ArrayList<Example> exampleArrayList) {
        this.exampleArrayList = exampleArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Example example = exampleArrayList.get(position);
        holder.titleTextView.setText(example.getTitle());
        holder.descriptionTextView.setText(example.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class<?> clazz = example.getActivityClass();
                if (clazz != null) {
                    v.getContext().startActivity(new Intent(v.getContext(), clazz));
                } else {
                    Toast.makeText(v.getContext(), R.string.error_example_missing_activity, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return exampleArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title)
        TextView titleTextView;
        @Bind(R.id.description)
        TextView descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
