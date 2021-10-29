package com.example.newu.branokod3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfilepotentialAdapter extends RecyclerView.Adapter<ProfilepotentialAdapter.profilepotentHolder> {
    List<Profiledetails> profiledetails;
    Context context;

    public ProfilepotentialAdapter(List<Profiledetails> profiledetails1, Context context1) {
        profiledetails=profiledetails1;
        context=context1;
    }

    @NonNull
    @Override
    public profilepotentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.potentialclientprofilelayout, parent, false);
        return new ProfilepotentialAdapter.profilepotentHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull profilepotentHolder holder, int position) {
        Profiledetails pr= profiledetails.get(position);
        Picasso.get().load(pr.getUri()).placeholder(R.drawable.blacknwhite).fit().centerCrop().into(holder.im1);
        holder.t1.setText(pr.getRegno());
        holder.t2.setText(pr.getCam());
        holder.t3.setText(pr.getPhone());


    }

    @Override
    public int getItemCount() {
        return profiledetails.size();
    }

    public class profilepotentHolder extends RecyclerView.ViewHolder {
        ImageView im1;
        TextView t1,t2,t3;
        public profilepotentHolder(@NonNull View itemView) {
            super(itemView);

            im1=itemView.findViewById(R.id.imageView5);
            t1=itemView.findViewById(R.id.wStudReg);
            t2=itemView.findViewById(R.id.wStudCampus);
            t3=itemView.findViewById(R.id.wStudexchange);
        }
    }
}
