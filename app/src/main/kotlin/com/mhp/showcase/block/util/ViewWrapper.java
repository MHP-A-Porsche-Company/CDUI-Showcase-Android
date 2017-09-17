package com.mhp.showcase.block.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {

    private V view;

   protected ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }


    public V getView() {
        return view;
    }
}
