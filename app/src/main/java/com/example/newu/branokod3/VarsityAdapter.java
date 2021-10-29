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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VarsityAdapter extends RecyclerView.Adapter<VarsityAdapter.VarsityImageHolder> {
    List<CollectiveUploadsVarsity>collectiveUploadsVarsities;
    Context context;

    OnItemClickListener listener;

    public void groupedandfiltered(List<CollectiveUploadsVarsity> grouped) {
              collectiveUploadsVarsities=grouped;
              notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick( int pos);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener= listener;
    }


    public VarsityAdapter(List<CollectiveUploadsVarsity>collectiveUploadsVarsities1,Context context1){
        collectiveUploadsVarsities=collectiveUploadsVarsities1;
        context=context1;
    }
    @NonNull
    @Override
    public VarsityImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.versitysalelayout, viewGroup, false);
        return new VarsityImageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final VarsityImageHolder varsityImageHolder, int i) {
        final CollectiveUploadsVarsity current=collectiveUploadsVarsities.get(i);
        varsityImageHolder.t1.setText(current.getDesc());
        varsityImageHolder.t2.setText(current.getMuch());
        Picasso.get().load(current.getUri()).centerCrop().fit().into(varsityImageHolder.im1);

    }

    @Override
    public int getItemCount() {
        return collectiveUploadsVarsities.size();
    }

    public class VarsityImageHolder extends RecyclerView.ViewHolder{
        TextView t1,t2,t3;
        ImageButton imb1;
        ImageView im1;

        DatabaseReference firebaseDatabasewishlist,firebaseDatabasepotential,firebaseDatabaseprofile;
        StorageReference firebaseStoragewishlist,firebaseStoragePotential;
        FirebaseAuth auth;
        public VarsityImageHolder(@NonNull View itemView) {
            super(itemView);

            firebaseDatabasewishlist= FirebaseDatabase.getInstance().getReference("MyWish varsity");
            firebaseStoragewishlist= FirebaseStorage.getInstance().getReference("MyWish varsity");
            firebaseDatabasepotential=FirebaseDatabase.getInstance().getReference("My potential clients varsity");
            firebaseStoragePotential=FirebaseStorage.getInstance().getReference("My potential clients varsity");
            firebaseDatabaseprofile = FirebaseDatabase.getInstance().getReference("Profile");
            auth=FirebaseAuth.getInstance();

            im1=itemView.findViewById(R.id.imageView14);
            t1=itemView.findViewById(R.id.varsityviewdesc);
            t2=itemView.findViewById(R.id.varsityviewprice);

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
