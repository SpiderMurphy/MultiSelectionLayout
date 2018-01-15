package com.cyan.testapp.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.cyan.multiselectionlayout.MultiSelectionAdapter;
import com.cyan.testapp.R;
import com.cyan.testapp.databinding.AdapterItemBinding;
import com.cyan.testapp.models.ItemModel;

import java.util.List;
import java.util.Set;

/**
 * Created by Cyan on 15/01/2018.
 */

public class AdapterItem extends MultiSelectionAdapter<AdapterItem.ViewHolder> {
    // Items list
    private List<ItemModel> m_items;

    public List<ItemModel> get_items() {
        return m_items;
    }

    public void set_items(List<ItemModel> m_items) {
        this.m_items = m_items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterItemBinding binder = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_item, parent, false);
        ViewHolder holder = new ViewHolder(binder.getRoot());

        holder.set_binder(binder);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.get_binder().setItem(m_items.get(position));
        holder.m_checkbox.setOnCheckedChangeListener(null);

        if(isActive())
            holder.m_container_left.setVisibility(View.VISIBLE);
        else
            holder.m_container_left.setVisibility(View.GONE);

        if(isSelected(holder.get_binder().getItem())){
            holder.m_checkbox.setChecked(true);
        }
        else {
            holder.m_checkbox.setChecked(false);
        }

        holder.m_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                AdapterItem.this.onSelectItem(position, b);

                if(!isActive())
                    AdapterItem.this.notifyDataSetChanged();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isActive())
                    holder.m_checkbox.setChecked(!holder.m_checkbox.isChecked());
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(!isActive()) {
                    holder.m_checkbox.setChecked(true);
                    AdapterItem.this.notifyDataSetChanged();
                }

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return m_items != null ? m_items.size() : 0;
    }

    @Override
    protected Object getSelectedItem(int position) {
        return m_items.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AdapterItemBinding m_binder;

        // Left container
        public View m_container_left;

        // Checkbox
        public CheckBox m_checkbox;

        public AdapterItemBinding get_binder() {
            return m_binder;
        }

        public void set_binder(AdapterItemBinding binder) {
            this.m_binder = binder;
        }

        public ViewHolder(View itemView) {
            super(itemView);

            m_container_left = itemView.findViewById(R.id.container_left);
            m_checkbox = itemView.findViewById(R.id.check_box);
        }
    }
}
