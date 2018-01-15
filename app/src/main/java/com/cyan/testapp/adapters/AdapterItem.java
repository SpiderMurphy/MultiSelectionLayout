package com.cyan.testapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import com.cyan.multiselectionlayout.MultiSelectionAdapter;
import com.cyan.testapp.models.ItemModel;

import java.util.List;
import java.util.Set;

/**
 * Created by Cyan on 15/01/2018.
 */

public class AdapterItem extends MultiSelectionAdapter<AdapterItem.ViewHolder> {
    // Checked array
    private Set<ItemModel> m_checked_items;

    // Items list
    private List<ItemModel> m_items;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    protected Object getSelectedItem(int position) {
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
