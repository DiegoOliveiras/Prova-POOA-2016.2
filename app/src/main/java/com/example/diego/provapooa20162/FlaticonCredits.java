package com.example.diego.provapooa20162;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FlaticonCredits extends AppCompatActivity {

    private TextView txtLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flaticon_credits);

        txtLink = (TextView) findViewById(R.id.txtLink);
        txtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.flaticon.com/authors/madebyoliver"));
                startActivity(browserIntent);
            }
        });
    }

    public void onBackPressed() {
        Intent it = new Intent(FlaticonCredits.this, MainActivity.class);
        startActivity(it);
    }
}
