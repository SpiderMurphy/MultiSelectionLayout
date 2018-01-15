package com.cyan.multiselectionlayout;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Cyan on 15/01/2018.
 */

public abstract class MultiSelectionAdapter<VH extends RecyclerView.ViewHolder>
                                               extends RecyclerView.Adapter<VH> {
    // Multi selection toolbar
    private MultiSelectionToolbar m_toolbar;

    /**
     * Call to add selection to toolbar
     *
     * @param position
     * @param select
     */
    protected void onSelectItem(int position, boolean select){
        // Signal toolbar to add item
        if(select)
            m_toolbar.add(getSelectedItem(position));
        // Signal toolbar to remove item
        else
            m_toolbar.remove(getSelectedItem(position));
    }

    /**
     * Get object at position
     *
     * @param position
     * @return
     */
    protected abstract Object getSelectedItem(int position);
}
