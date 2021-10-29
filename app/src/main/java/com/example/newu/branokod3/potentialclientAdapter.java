package com.example.newu.branokod3;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.newu.branokod3.WishlistAdapter.WishImageHolder.TAG;

public class potentialclientAdapter extends RecyclerView.Adapter<potentialclientAdapter.PotentialHolder> {
    List<Profiledetails> profiledetails;
    Context context;
    OnItemClickListener listener;

    public void delete(DataSnapshot itemSnapshot, String koi) {
        FirebaseDatabase.getInstance().getReference().child(koi).removeValue();
        itemSnapshot.getRef().removeValue();
        Log.d(TAG, "delete:reached ");
    }

    public interface OnItemClickListener{
        void onItemClick(int pos);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public potentialclientAdapter(List<Profiledetails> profiledetails1, Context context1) {
        profiledetails=profiledetails1;
        context=context1;
    }

    @NonNull
    @Override
    public PotentialHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.potentialclientlayoutfile, viewGroup, false);
        return new PotentialHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PotentialHolder potentialHolder, int i) {
        Profiledetails pr= profiledetails.get(i);
        Picasso.get().load(pr.getUri()).placeholder(R.drawable.blacknwhite).fit().centerCrop().into(potentialHolder.im1);

    }

    @Override
    public int getItemCount() {
        return profiledetails.size();
    }


    public class PotentialHolder extends RecyclerView.ViewHolder {
        ImageView im1;
        public PotentialHolder(@NonNull View itemView) {
            super(itemView);
            im1 = itemView.findViewById(R.id.imageView);


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
