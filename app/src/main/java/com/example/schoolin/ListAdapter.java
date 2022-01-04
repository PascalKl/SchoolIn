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
    private int mode;

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
                String i = p.getName().replace("-"," - ");
                tt1.setText(i);
            }

            if (tt2 != null) {
                tt2.setText(p.getLocation());
            }
            if (tt3 != null) {
                String i = p.getEducation1();
                if(p.getEducation2().equals("") == false){
                    i= i+", "+p.getEducation2();
                }
                if(p.getEducation3().equals("") == false){
                    i= i+", "+p.getEducation3();
                }
                tt3.setText(i);
            }
        }
        return v;
    }
}