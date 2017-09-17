package com.mhp.showcase.block.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewAdapterBase<T, V extends View> extends RecyclerView.Adapter<ViewWrapper> {

    private List<T> items = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new ViewWrapper<>(onCreateItemView(parent, viewType));
//    }

    /**
     * Set the items to be rendered
     *
     * @param items The list of items to be rendered
     */
    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<T> getItems() {
        return items;
    }

}
