package com.acejob.acejob;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class rootActivity extends AppCompatActivity {
    int onStartCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onStartCount = 1;
        if (savedInstanceState == null) // 1st time
        {
            this.overridePendingTransition(R.animator.slide_up,
                    R.animator.slide_up);

        } else // already created so reverse animation
        {
            onStartCount = 2;
        }

    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        if (onStartCount > 1) {
            this.overridePendingTransition(R.animator.slide_down,
                    R.animator.slide_down);

        } else if (onStartCount == 1) {
            onStartCount++;
        }

    }

}