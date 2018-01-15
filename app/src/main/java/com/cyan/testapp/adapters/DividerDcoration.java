package com.cyan.testapp.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cyan.testapp.R;

/**
 * Created by Cyan on 15/01/2018.
 */

public class DividerDcoration extends RecyclerView.ItemDecoration {
    // Divider drawable
    private Drawable m_divider;

    // Gap from last item to bottom
    private int m_offset;

    public DividerDcoration(Context context, int offset) {
        this.m_divider = ContextCompat.getDrawable(context, R.drawable.divider_line);
        this.m_offset = (int)((double)offset*context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        // Childs counter
        int childs = parent.getChildCount();

        int left = parent.getLeft();
        int right = parent.getWidth();

        for(int i=0; i<childs; i++){
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + m_divider.getIntrinsicHeight();

            m_divider.setBounds(left, top, right, bottom);
            m_divider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(state.getItemCount() > 0 && parent.getChildAdapterPosition(view) == (state.getItemCount()-1) )
            outRect.set(0, 0, 0, m_offset);
    }
}
