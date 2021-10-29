package com.example.newu.branokod3;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;


public class SokoAdapter extends RecyclerView.Adapter<SokoAdapter.SokoImageHolder> {
    List<CollectiveUploadsSoko> collectiveUploadsSokos;
    Context context;
    OnItemClickListener listener;

    public void groupedandfiltered(List<CollectiveUploadsSoko> grouped) {
        collectiveUploadsSokos=grouped;
        notifyDataSetChanged();
    }




    public interface OnItemClickListener{
        void onItemClick( int pos);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public SokoAdapter(List<CollectiveUploadsSoko>collectiveUploadsSokos1, Context context1){
        collectiveUploadsSokos=collectiveUploadsSokos1;
        context=context1;
    }

    @NonNull
    @Override
    public SokoImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.sokoviewlayout, viewGroup, false);
        return new SokoImageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SokoImageHolder sokoImageHolder,  int i) {
        final CollectiveUploadsSoko current = collectiveUploadsSokos.get(i);
        Picasso.get().load(current.getUri()).fit().centerCrop().purgeable().placeholder(R.drawable.clipart46977169).into(sokoImageHolder.im1);
        sokoImageHolder.t1.setText(current.getDes());
        sokoImageHolder.t2.setText(current.getExpo());

    }
  
            @Override
    public int getItemCount() {
        return collectiveUploadsSokos.size();
    }


    public class SokoImageHolder extends RecyclerView.ViewHolder {

        TextView t1,t2;
        ImageView im1;

        public SokoImageHolder(@NonNull View itemView) {
            super(itemView);
            im1=itemView.findViewById(R.id.imageView14);
            t1=itemView.findViewById(R.id.sokoviewdesc);
            t2=itemView.findViewById(R.id.sokoviewexpe);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int pos = getAdapterPosition();
                        if (pos != Adapter.NO_SELECTION) {
                             listener.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
