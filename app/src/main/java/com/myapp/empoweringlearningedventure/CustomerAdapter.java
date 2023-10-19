package com.myapp.empoweringlearningedventure;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class CustomerAdapter extends BaseAdapter {

    private ArrayList<Button> mButtons = null;
    private int mColumnWidth, mColumnHeight;

    public CustomerAdapter(ArrayList<Button> buttons, int columnWidth, int columnHeight) {
        mButtons = buttons;
        mColumnWidth = columnWidth;
        mColumnHeight = columnHeight;
    }
    @Override
    public int getCount() {
        return mButtons.size();
    }

    @Override
    public Object getItem(int position) {
        return (Object) mButtons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Button button;

        if (view == null) {
            button = mButtons.get(position);
        } else {
            button = (Button) view;
        }

        android.widget.AbsListView.LayoutParams params = new android.widget.AbsListView.LayoutParams(mColumnWidth, mColumnHeight);
        button.setLayoutParams(params);

        return button;
    }
}
