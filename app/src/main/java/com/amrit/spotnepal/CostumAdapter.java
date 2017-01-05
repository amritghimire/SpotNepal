package com.amrit.spotnepal;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity.*;
import android.widget.Toast;
public class CostumAdapter extends BaseAdapter{
    spot[] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CostumAdapter(Activity mainActivity, spot[] spots) {
        // TODO Auto-generated constructor stub
        context=mainActivity;
        result=spots;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
        TextView tvv;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.spots_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.nameOfPlaceInList);
        holder.img=(ImageView) rowView.findViewById(R.id.imgOfPlace);
        holder.tvv=(TextView) rowView.findViewById(R.id.shortDescription) ;
        holder.tv.setText(result[position].name);
        holder.img.setImageResource(result[position].drawable);
        holder.tvv.setText(result[position].shortDescription);
        holder.img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.sptC.getContext(),descriptionOfPlace.class);
                intent.putExtra("name",result[position].name);
                intent.putExtra("description",result[position].longDescription);
                context.startActivity(intent);
            }
        });
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.sptC.getContext(),descriptionOfPlace.class);
                intent.putExtra("name",result[position].name);
                intent.putExtra("description",result[position].longDescription);
                context.startActivity(intent);
            }
        });
        return rowView;
    }

}
