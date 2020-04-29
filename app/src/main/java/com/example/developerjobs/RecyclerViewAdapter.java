package com.example.developerjobs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    AppDatabase appDatabase;
    SavedJobDao savedJobDao;
    public List<Job> jobs;
    public List<SavedJob> savedJobs;
    private ClickListener clickListener;

    public RecyclerViewAdapter(@Nullable List<Job> jobs,@Nullable ClickListener clickListener) {
        this.jobs = jobs;
        this.clickListener = clickListener;
        appDatabase = MyApplication.getInstance().getDatabase();
        savedJobDao = appDatabase.savedJobDao();
        savedJobs = savedJobDao.getSavedJobs();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Job job = jobs.get(position);
        boolean is_saved = false;
        holder.id = job.getId();
        holder.title.setText(job.getTitle());
        holder.type.setText(job.getType());
        holder.created_at.setText(job.getCreatedAt());

        for(SavedJob s : savedJobs){
            if(job.getId().equals(s.id)) {
                is_saved = true;
                holder.save.setImageResource(R.drawable.ic_favorite_black_24dp);
            }
        }

        Picasso.get()
                .load(job.getCompanyLogo())
                .placeholder(R.drawable.ic_import_contacts_black_24dp)
                .error(R.drawable.ic_import_contacts_black_24dp)
                .resize(200, 200)
                .into(holder.logo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener != null){
                    clickListener.replaceFragment(position, job);
                }
            }
        });

        boolean is_saved_finally = is_saved;
        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_saved_finally) {
                    holder.save.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    savedJobDao.delete(new SavedJob(job.getId()));
                }
                if(!is_saved_finally) {
                    holder.save.setImageResource(R.drawable.ic_favorite_black_24dp);
                    savedJobDao.insert(new SavedJob(job.getId()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, type, created_at;
        ImageView logo, save;
        String id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            type = itemView.findViewById(R.id.type);
            created_at = itemView.findViewById(R.id.created_at);
            logo = itemView.findViewById(R.id.logo);
            save = itemView.findViewById(R.id.save);
        }
    }

    public interface ClickListener{
        void replaceFragment(int id, Job job);
    }
}
