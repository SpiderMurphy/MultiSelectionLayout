package com.cyan.multiselectionlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cyan on 14/01/2018.
 */

public class MultiSelectionLayout extends FrameLayout implements MultiSelectionToolbar {
    // Toolbar (extend class for further customizations)
    protected Toolbar m_toolbar;

    // Toolbar container (to make that fancy gradient like google drive)
    protected View m_container;

    // Gradient drawable (background for container)
    private GradientDrawable m_gradient;

    // Selected items
    private Set<Object> m_items;

    // Active flag
    private boolean m_active;

    // Custom attributes
    // Gradient color start
    private int m_bg_gradient_start;

    // Gradient color end
    private int m_bg_gradient_end;

    // Menu resource id
    private int m_menu_resource;

    public boolean is_active() {
        return m_active;
    }

    public void set_active(boolean m_active) {
        this.m_active = m_active;

        // Reset selection
        if(!m_active)
            m_items.clear();

        // Show / hide selection bar
        if(m_active) {
            m_container.setVisibility(View.VISIBLE);
            update();
        }
        else
            m_container.setVisibility(View.GONE);
    }

    public MultiSelectionLayout(@NonNull Context context) {
        super(context);
    }

    public MultiSelectionLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MultiSelectionLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MultiSelectionLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    /**
     * Load custom attributes and init properties
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs){
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MultiSelectionLayout, 0, 0);

        try{
            m_bg_gradient_start = typedArray.getColor(R.styleable.MultiSelectionLayout_bg_gradient_start, -1);
            m_bg_gradient_end = typedArray.getColor(R.styleable.MultiSelectionLayout_bg_gradient_end, -1);
            m_menu_resource = typedArray.getResourceId(R.styleable.MultiSelectionLayout_menu_resource, 0);

            if(m_bg_gradient_start == -1)
                m_bg_gradient_start = ContextCompat.getColor(context, android.R.color.transparent);

            if(m_bg_gradient_end == -1)
                m_bg_gradient_end = ContextCompat.getColor(context, android.R.color.transparent);
        }
        finally{
            typedArray.recycle();

            // Create hashset
            m_items = new HashSet<>();
        }
    }

    /**
     * Update toolbar description (with new items count)
     */
    private void update(){
        m_toolbar.setTitle(String.valueOf(m_items.size()) + " items selected");
    }

    /**
     * Set on menu item click listener for toolbar
     *
     * @param listener
     * listener to be set
     */
    public void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener listener){
        m_toolbar.setOnMenuItemClickListener(listener);
    }

    /**
     * Override on finish inflate to append the toolbar
     * in the layout.
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // Inflating default container layout and attributes
        m_container = LayoutInflater.from(getContext()).inflate(R.layout.mls_container_layout, this, false);

        // Gets toolbar
        m_toolbar = m_container.findViewById(R.id.mls_toolbar);

        // Setup background
        m_gradient = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
                new int[]{ m_bg_gradient_start,
                        m_bg_gradient_end });

        m_gradient.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        m_container.setBackground(m_gradient);

        // Inflate menu
        if(m_menu_resource != 0){
            m_toolbar.inflateMenu(m_menu_resource);
        }

        // Not visible at startup
        m_container.setVisibility(View.GONE);

        addView(m_container);
    }

    @Override
    public void add(Object item) {
        m_items.add(item);
        update();
    }

    @Override
    public void remove(Object item) {
        m_items.remove(item);
        update();
    }

    @Override
    public void notifySelectionMode(boolean active) {
        set_active(active);
    }

    @Override
    public int getSelectedItemsCount() {
        return m_items.size();
    }

    @Override
    public boolean isEmpty() {
        return m_items.isEmpty();
    }

    @Override
    public boolean isSelected(Object item) {
        return m_items.contains(item);
    }

    @Override
    public boolean isActive() {
        return m_active;
    }

    @Override
    public Object[] getItems() {
        return m_items.toArray();
    }
}
