package com.example.moodindigo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;
    RelativeLayout layout;

    public SliderAdapter(Context context, RelativeLayout layout1){
        this.context = context;
        layout = layout1;
    }

    public int[] images= {

            R.drawable.image1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,

    };

    public String[] description = {

            "Mood Indigo 2019 or \'MI 2K19\' witnessed the grandeur of happiness and joy. With flying feet all around the institute, " +
                    "IIT Bombay was overwhelmed by the beauty of herself and the enthusiasm of " +
                    "people visiting her from all around the world. From painted walls to the best performances, from " +
                    "the selfie points to huge concerts, it was certainly an event not to be missed. With a footfall of " +
                    "around 140K+ there were huge gatherings where people get to know each other irrespective of the " +
                    "society of the world whom they belong to. Known as Asia\'s largest cultural fest, it experienced " +
                    "once in a lifetime things. Let\'s get to them one by one....",

    };

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_intro_slides, container, false);


        ImageView image = view.findViewById(R.id.image);
        TextView text = view.findViewById(R.id.text);
        container.addView(view);

        image.setImageResource(images[position]);
        text.setText(description[0]);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ConstraintLayout)object);
    }
}
