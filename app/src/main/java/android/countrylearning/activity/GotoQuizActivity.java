package android.countrylearning.activity;

import android.app.Activity;
import android.content.Intent;
import android.countrylearning.R;
import android.countrylearning.datasource.Database;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GotoQuizActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goto_quiz);

        Button startQuizButton = findViewById(R.id.start_quiz);
        Button goBackButton = findViewById(R.id.go_back);

        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.setCurrentLesson(0);
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
            }
        });

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.setCurrentLesson(Database.getNumberOfLessons() - 1);
                Intent intent = new Intent(getApplicationContext(), LearningActivity.class);
                startActivity(intent);
            }
        });
    }
}