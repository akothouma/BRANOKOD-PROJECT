package com.example.newu.branokod3;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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

public class WishlistAdapterVarsity extends RecyclerView.Adapter<WishlistAdapterVarsity.VarsityHolder> {
    List<CollectiveUploadsVarsity> collectiveUploadsVarsityList;
    Context context;
    OnItemClickListener listener;

    public void delete2(DataSnapshot snapshot, String needid2) {
        FirebaseDatabase.getInstance().getReference().child(needid2).removeValue();
        snapshot.getRef().removeValue();
    }

    public void delete1(DataSnapshot snapshot, String needid1) {
        FirebaseDatabase.getInstance().getReference().child(needid1).removeValue();
        snapshot.getRef().removeValue();
    }

    public void delete(DataSnapshot snapshot, String needid) {
        FirebaseDatabase.getInstance().getReference().child(needid).removeValue();
        snapshot.getRef().removeValue();
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;

    }
    public WishlistAdapterVarsity(List<CollectiveUploadsVarsity>collectiveUploadsVarsityList1, Context context1) {
        collectiveUploadsVarsityList = collectiveUploadsVarsityList1;
        context = context1;
    }

    @NonNull
    @Override
    public VarsityHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.wishlistlayout, viewGroup, false);
        return new WishlistAdapterVarsity.VarsityHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VarsityHolder varsityHolder, int i) {
        CollectiveUploadsVarsity current=collectiveUploadsVarsityList.get(i);
        Picasso.get().load(current.getUri()).fit().centerCrop().placeholder(R.drawable.clipart46977169).into(varsityHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return collectiveUploadsVarsityList.size();
    }

    public class VarsityHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView t1;

        public VarsityHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView2);
            t1=itemView.findViewById(R.id.viewitemonwishlist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!= Adapter.NO_SELECTION){
                            listener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
