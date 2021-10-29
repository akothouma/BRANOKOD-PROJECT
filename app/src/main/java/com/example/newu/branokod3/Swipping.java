package com.example.newu.branokod3;

import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.StorageReference;

public class Swipping extends ItemTouchHelper.SimpleCallback {
    private MyItemsSokoAdapter adapter;
    private final ColorDrawable background;
    private String theid;
    StorageReference uri;

    public Swipping(int dragDirs, int swipeDirs, MyItemsSokoAdapter adapter, ColorDrawable background, String theid, StorageReference uri) {
        super(dragDirs, swipeDirs);
        this.adapter = adapter;
        this.background = background;
        this.theid = theid;
        this.uri = uri;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        adapter.deleteItem(position,theid,uri);


    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;

         if (dX < 0) { // Swiping to the left

            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
        } else { // view is unSwiped
             background.setBounds(0, 0, 0, 0);
         }
             background.draw(c);



    }
}
