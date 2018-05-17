package android.countrylearning.activity;

import android.content.Intent;
import android.countrylearning.R;
import android.countrylearning.datasource.Database;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startLearningButton = findViewById(R.id.start_learning);
        Button exitButton = findViewById(R.id.exit);

        startLearningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LearningActivity.class);
                startActivity(intent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.resetData();
                finishAffinity();
            }
        });

    }

}
