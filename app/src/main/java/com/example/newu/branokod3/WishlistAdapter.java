package com.example.newu.branokod3;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


import java.util.List;

import static com.example.newu.branokod3.WishlistAdapter.WishImageHolder.TAG;


public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishImageHolder> {
    List<CollectiveUploadsSoko>collectiveUploadsSokos;
    Context context;
    OnItemClickListener listener;

    public void delete(DataSnapshot itemSnapshot, String theid) {
        FirebaseDatabase.getInstance().getReference().child(theid).removeValue();
        itemSnapshot.getRef().removeValue();
        Log.d(TAG, "delete:reached ");
    }

    public void delete12(DataSnapshot itemSnapshot, String koi) {
        FirebaseDatabase.getInstance().getReference().child(koi).removeValue();
        itemSnapshot.getRef().removeValue();
        Log.d(TAG, "delete:reached ");
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public WishlistAdapter(List<CollectiveUploadsSoko>collectiveUploadsSokos1, Context context1){
        collectiveUploadsSokos=collectiveUploadsSokos1;
        context=context1;
    }
    @NonNull
    @Override
    public WishImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.wishlistlayout, viewGroup, false);
        return new WishlistAdapter.WishImageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final WishImageHolder wishImageHolder, int i) {
        CollectiveUploadsSoko current=collectiveUploadsSokos.get(i);
        Picasso.get().load(current.getUri()).fit().centerCrop().purgeable().placeholder(R.drawable.clipart46977169).into(wishImageHolder.imageView);
    }

    @Override

    public int getItemCount() {
        return collectiveUploadsSokos.size();
    }

    public class WishImageHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView t1;
        public static final String TAG="WishlistAdapter";

        public WishImageHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView2);
            t1=itemView.findViewById(R.id.viewitemonwishlist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                       int position=getAdapterPosition();
                       if(position!=Adapter.NO_SELECTION){
                           listener.OnItemClick(position);
                       }
                        }
                    }
                });
            }

        }
    }

