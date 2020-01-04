package com.example.moodindigo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class IntroSlidesActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private SliderAdapter mSlideAdapter;
    private RelativeLayout layout;
    private TextView skipText;

    public int[] background = {
            R.drawable.background_1,
            R.drawable.background_2,
            R.drawable.background_3,
            R.drawable.background_4,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_layout);
        layout = findViewById(R.id.slider_layout);

        mSlideViewPager = findViewById(R.id.slider);
        mSlideAdapter = new SliderAdapter(this, layout);
        mSlideViewPager.setAdapter(mSlideAdapter);
        mSlideViewPager.addOnPageChangeListener(viewListner);
        skipText = findViewById(R.id.skip_1);

        skipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroSlidesActivity.this, HomeScreenActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });

    }


    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            layout.setBackgroundResource(background[position]);
            int l = background.length;
            if(position == l-1){
                skipText.setText("Go to the app..");
            }
            else {
                skipText.setText("SKIP");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
