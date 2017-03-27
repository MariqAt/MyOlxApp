package com.ittalents.myfirstaplication.model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ittalents.myfirstaplication.R;

import java.util.List;

/**
 * Created by ASUS on 26.3.2017 г..
 */

public class OLXAdapter extends ArrayAdapter<RegularUser.Notice> {

    private Context context;
    private  int layoutID;
    private List<RegularUser.Notice> noticeList;

    public OLXAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<RegularUser.Notice> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        noticeList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(layoutID, parent, false);

        ImageView imageView = (ImageView) row.findViewById(R.id.notice_row_image);
        imageView.setImageResource(noticeList.get(position).getPictureID());

        TextView titleView = (TextView) row.findViewById(R.id.notice_row_title);
        titleView.setText(noticeList.get(position).getTitle());

        TextView priceView = (TextView) row.findViewById(R.id.notice_row_price);
        priceView.setText(noticeList.get(position).getPrice()+" лв");

        return row;
    }
}
