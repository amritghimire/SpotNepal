package com.amrit.spotnepal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by amrit on 6/30/16.
 */
public class AndroidImageAdapter extends PagerAdapter {
    int[] drawables;
    Context context;

    public AndroidImageAdapter(Context context, int[] drawables) {
        this.context = context;
        this.drawables = drawables;
    }

    @Override
    public int getCount() {
        return drawables.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(drawables[position]);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("abc", String.valueOf(drawables[position]));
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawables[position]);

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                File fldr = new File(Environment.getExternalStorageDirectory() + File.separator + "spotnepal");
                boolean success = true;
                if (!fldr.exists()) {
                    success = fldr.mkdir();
                }
                File folderr = new File(Environment.getExternalStorageDirectory() + File.separator + "spotnepal"+File.separator+"images");
                if (!fldr.exists()) {
                    success = fldr.mkdir();
                }

                if (success) {
                    File f = new File(Environment.getExternalStorageDirectory() + File.separator + "spotnepal"+File.separator+"images"
                            + File.separator + context.getResources().getResourceEntryName(drawables[position]) + ".png");
                    if(!f.exists()) {
                        try {
                            Toast.makeText(context, "Copying file to your storage", Toast.LENGTH_LONG).show();
                            f.createNewFile();
                            FileOutputStream fo = new FileOutputStream(f);
                            fo.write(bytes.toByteArray());
                            fo.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    Uri path = Uri.fromFile(f);
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "image/*");
                    context.startActivity(intent);
                             // Do something on success
                } else {
                    // Do something else on failure
                }

            }
        });
        ((ViewPager) container).addView(imageView);
        return  imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((ImageView) object);
    }
}
