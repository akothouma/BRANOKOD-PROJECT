package com.example.newu.branokod3;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileHolder> {
   List<Profiledetails>profiledetails;
   Context  context;

    public ProfileAdapter(List<Profiledetails> profiledetails1, Context context1) {
        profiledetails=profiledetails1;
        context=context1;
    }

    @NonNull
    @Override
    public ProfileHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.profilelayout, viewGroup, false);
        return new  ProfileHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHolder profileHolder, int i) {
        Profiledetails pr= profiledetails.get(i);
        Picasso.get().load(pr.getUri()).placeholder(R.drawable.blacknwhite).fit().centerCrop().into(profileHolder.im1);
        profileHolder.t1.setText(pr.getRegno());
        profileHolder.t2.setText(pr.getCam());
        profileHolder.t3.setText(pr.getPhone());

    }

    @Override
    public int getItemCount() {
        return profiledetails.size();
    }

    public class ProfileHolder extends RecyclerView.ViewHolder {
        ImageView im1;
        TextView t1,t2,t3;
        public ProfileHolder(@NonNull View itemView) {
            super(itemView);
            im1=itemView.findViewById(R.id.imageView5);
            t1=itemView.findViewById(R.id.wStudReg);
            t2=itemView.findViewById(R.id.wStudCampus);
            t3=itemView.findViewById(R.id.wStudexchange);

        }
    }

}

