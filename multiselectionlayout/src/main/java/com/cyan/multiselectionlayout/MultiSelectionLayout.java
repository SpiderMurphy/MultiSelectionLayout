package com.cyan.multiselectionlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Cyan on 14/01/2018.
 */

public class MultiSelectionLayout extends FrameLayout {
    // Toolbar (extend class for further customizations)
    protected Toolbar m_toolbar;

    // Toolbar container (to make that fancy gradient like google drive)
    protected View m_container;

    // Gradient drawable (background for container)
    private GradientDrawable m_gradient;

    // Object that handles multiselection mode
    private MultiSelectionEvents m_handler;

    // Active flag
    private boolean m_active;

    // Custom attributes
    // Gradient color start
    private int m_bg_gradient_start;

    // Gradient color end
    private int m_bg_gradient_end;

    public boolean is_active() {
        return m_active;
    }

    public void set_active(boolean m_active) {
        this.m_active = m_active;
    }

    public MultiSelectionLayout(@NonNull Context context) {
        super(context);
    }

    public MultiSelectionLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiSelectionLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MultiSelectionLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Load custom attributes
     *
     * @param context
     * @param attrs
     */
    private void load_attrs(Context context, AttributeSet attrs){
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MultiSelectionLayout, 0, 0);

        try{
            m_bg_gradient_start = typedArray.getColor(R.styleable.MultiSelectionLayout_bg_gradient_start, -1);
            m_bg_gradient_end = typedArray.getColor(R.styleable.MultiSelectionLayout_bg_gradient_end, -1);
        }
        finally{
            typedArray.recycle();
        }
    }

    /**
     * Override on finish inflate to append the toolbar
     * in the layout.
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // Inflating default container layout and attributes
        m_container = LayoutInflater.from(getContext()).inflate(R.layout.mls_container_layout, this);

        // Setup background
        m_gradient = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
                new int[]{ m_bg_gradient_start,
                        m_bg_gradient_end });


    }
}
