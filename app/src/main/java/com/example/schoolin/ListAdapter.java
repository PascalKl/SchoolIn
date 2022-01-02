package com.example.schoolin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class ListAdapter extends ArrayAdapter<school> {

    private int resourceLayout;
    private Context mContext;

    public ListAdapter(Context context, int resource, List<school> schools) {
        super(context, resource, schools);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        school p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.tv_schoolname);
            TextView tt2 = (TextView) v.findViewById(R.id.tv_schoollocation);
            TextView tt3 = (TextView) v.findViewById(R.id.tv_schooleducation);

            if (tt1 != null) {
                tt1.setText(p.getName());
            }

            if (tt2 != null) {
                tt2.setText(p.getLocation());
            }
            if (tt3 != null) {
                tt3.setText(p.getEducation1()+", "+p.getEducation2()+", "+p.getEducation3());
            }
        }


        return v;
    }


}