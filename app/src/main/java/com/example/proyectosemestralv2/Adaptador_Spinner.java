package com.example.proyectosemestralv2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class Adaptador_Spinner extends BaseAdapter {
    private Context context;
    private String[] array;

    public Adaptador_Spinner(Context context, String[] array) {
        this.context = context;
        this.array = array;
    }
//en proceso

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
