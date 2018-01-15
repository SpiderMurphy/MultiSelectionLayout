package com.cyan.multiselectionlayout;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Cyan on 15/01/2018.
 */

public abstract class MultiSelectionAdapter<VH extends RecyclerView.ViewHolder>
                                               extends RecyclerView.Adapter<VH> {
    // Multi selection toolbar
    private MultiSelectionToolbar m_toolbar;

    public MultiSelectionToolbar get_toolbar() {
        return m_toolbar;
    }

    public void set_toolbar(MultiSelectionToolbar m_toolbar) {
        this.m_toolbar = m_toolbar;
    }

    /**
     * Call to add selection to toolbar
     *
     * @param position
     * @param select
     */
    protected void onSelectItem(int position, boolean select){
        // Signal toolbar to add item
        if(select) {
            // First time we add an item makes toolbar appear
            if(m_toolbar.isEmpty())
                m_toolbar.notifySelectionMode(true);

            m_toolbar.add(getSelectedItem(position));
        }
        // Signal toolbar to remove item
        else {
            m_toolbar.remove(getSelectedItem(position));

            // If no more items left makes toolbar disappear
            if(m_toolbar.isEmpty())
                m_toolbar.notifySelectionMode(false);
        }
    }

    /**
     * Check if an item is already selected
     *
     * @param item
     * @return
     */
    protected boolean isSelected(Object item){
        return m_toolbar.isSelected(item);
    }

    /**
     *
     * @return
     * toolbar active status
     */
    protected  boolean isActive(){
        return m_toolbar.isActive();
    }

    /**
     * Get object at position
     *
     * @param position
     * @return
     */
    protected abstract Object getSelectedItem(int position);
}
