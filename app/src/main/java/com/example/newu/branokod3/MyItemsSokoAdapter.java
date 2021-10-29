package com.example.newu.branokod3;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import static com.example.newu.branokod3.MyItemsSoko.TAG;

public class MyItemsSokoAdapter extends RecyclerView.Adapter<MyItemsSokoAdapter.MyItemsSokoHolder> {
    List<CollectiveUploadsSoko> collectiveUploadsSokos;
    Context context;
    List<CollectiveUploadsSoko>Deleted;


    public MyItemsSokoAdapter(List<CollectiveUploadsSoko> collectiveUploadsSokos1, Context context1) {
        collectiveUploadsSokos = collectiveUploadsSokos1;
        context = context1;
    }

    @NonNull
    @Override
    public MyItemsSokoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.myitemsokolayout, viewGroup, false);
        return new MyItemsSokoAdapter.MyItemsSokoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemsSokoHolder myItemsSokoHolder, int i) {
        final CollectiveUploadsSoko current = collectiveUploadsSokos.get(i);
        Picasso.get().load(current.getUri()).fit().centerCrop().purgeable().placeholder(R.drawable.clipart46977169).into(myItemsSokoHolder.im1);
        myItemsSokoHolder.t2.setText(current.getDes());
        myItemsSokoHolder.t3.setText(current.getExpo());
        myItemsSokoHolder.t5.setText(current.getType());

    }

    @Override
    public int getItemCount() {
        return collectiveUploadsSokos.size();
    }







    public void deleteItem(int position, String theid, StorageReference uri) {
        collectiveUploadsSokos.remove(position);
            FirebaseDatabase.getInstance().getReference("Soko Uploads").child(theid).removeValue();
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

    public void delete(DataSnapshot itemSnapshot, String theid, StorageReference uri) {
        FirebaseDatabase.getInstance().getReference().child(theid).removeValue();
        itemSnapshot.getRef().removeValue();
        uri.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "Image deleted successfully");
                }
            }
        });
    }


    public class MyItemsSokoHolder extends RecyclerView.ViewHolder {
        ImageView im1;
        TextView t2,t3,t5,t6;
        public MyItemsSokoHolder(@NonNull View itemView) {
            super(itemView);
            im1=itemView.findViewById(R.id.sokoimageView);
            t2=itemView.findViewById(R.id.myItemSokodesc);
            t3=itemView.findViewById(R.id.myItemSokoexpe);
            t5=itemView.findViewById(R.id.myItemsSokotype);
            t6=itemView.findViewById(R.id.sokocontactexchange);



        }
    }
}
