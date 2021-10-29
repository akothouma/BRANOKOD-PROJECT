package com.example.newu.branokod3;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.newu.branokod3.MyItemsSoko.TAG;

public class MyItemsVarsityAdapter extends RecyclerView.Adapter<MyItemsVarsityAdapter.VarsityViewHolder> {
    java.util.List<CollectiveUploadsVarsity> collectiveUploadsVarsityList;
    Context context;

    public MyItemsVarsityAdapter(List<CollectiveUploadsVarsity> collectiveUploadsVarsityList1, Context context1){
        collectiveUploadsVarsityList=collectiveUploadsVarsityList1;
        context=context1;
    }



    @NonNull
    @Override
    public VarsityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.myitemsvarsitylayout,viewGroup,false);
        return new VarsityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VarsityViewHolder varsityViewHolder, int i) {
        final CollectiveUploadsVarsity current = collectiveUploadsVarsityList.get(i);
        Picasso.get().load(current.getUri()).fit().centerCrop().purgeable().placeholder(R.drawable.clipart46977169).into(varsityViewHolder.im1);
        varsityViewHolder.t2.setText(current.getType());
        varsityViewHolder.t1.setText(current.getDuration());
        varsityViewHolder.t3.setText(current.getDesc());
        varsityViewHolder.t5.setText(current.getMuch());

    }

    @Override
    public int getItemCount() {
        return collectiveUploadsVarsityList.size();
    }



    public void delete(DataSnapshot snapshot, String needid, StorageReference uri) {
        FirebaseDatabase.getInstance().getReference().child(needid).removeValue();
        snapshot.getRef().removeValue();
        uri.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Image deleted successfully");
                }
            }
        });
    }

    public void delete1(DataSnapshot snapshot, String needid1, StorageReference uri) {
        FirebaseDatabase.getInstance().getReference().child(needid1).removeValue();
        snapshot.getRef().removeValue();
        uri.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "Image deleted successfully");
                }
            }
        });
    }

    public void delete2(DataSnapshot snapshot, String needid2, StorageReference uri) {
        FirebaseDatabase.getInstance().getReference().child(needid2).removeValue();
        snapshot.getRef().removeValue();
        uri.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "Image deleted successfully");
                }
            }
        });
    }

    public void deleteItem(int position, String theid, StorageReference uri) {
        collectiveUploadsVarsityList.remove(position);
        FirebaseDatabase.getInstance().getReference("Varsity Uploads").child(theid).removeValue();
        uri.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "Image deleted successfully");
                }
            }
        });
        notifyItemRemoved(position);
    }


    public class VarsityViewHolder extends RecyclerView.ViewHolder {
        ImageView im1;
        TextView t1,t2,t3,t5;

        public VarsityViewHolder(@NonNull View itemView) {
            super(itemView);
            im1=itemView.findViewById(R.id.myitemsvarsityimageView);
            t1=itemView.findViewById(R.id.myItemsVarsityduration);
            t2=itemView.findViewById(R.id.myItemsVarsitytype);
            t3=itemView.findViewById(R.id.myItemVarsitydesc);
            t5=itemView.findViewById(R.id.myItemsVarsityPrice);

        }
    }
}
