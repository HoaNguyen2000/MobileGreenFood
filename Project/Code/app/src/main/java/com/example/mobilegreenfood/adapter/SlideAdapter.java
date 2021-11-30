package com.example.mobilegreenfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mobilegreenfood.R;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SlideAdapter extends SliderViewAdapter<SlideAdapter.Holer> {
    int[] listImage;
    public SlideAdapter(int[] listImage){
        this.listImage = listImage;
    }
    @Override
    public Holer onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_slide_item, parent, false);
        return new Holer(view);
    }

    @Override
    public void onBindViewHolder(Holer viewHolder, int position) {
        viewHolder.imageView.setImageResource(listImage[position]);
    }

    @Override
    public int getCount() {
        return listImage.length;
    }

    public class Holer extends SliderViewAdapter.ViewHolder{
        ImageView imageView;
        public Holer(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
