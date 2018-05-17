package android.countrylearning.activity;

import android.app.Activity;
import android.content.Intent;
import android.countrylearning.R;
import android.countrylearning.datasource.Database;
import android.countrylearning.lesson.LessonService;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LearningActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        final ImageView learnImage = findViewById(R.id.learn_image);
        final EditText learnText = findViewById(R.id.learn_text);
        final Button previousButton = findViewById(R.id.previous);
        final Button nextButton = findViewById(R.id.next);

        setContent(learnImage, learnText);
        previousButton.setText(R.string.go_back);
        nextButton.setText(R.string.next);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Database.getCurrentLesson() == 0) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Database.setCurrentLesson(Database.getCurrentLesson() - 1);
                    setContent(learnImage, learnText);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Database.getCurrentLesson() == Database.getNumberOfLessons() - 1) {
                    Intent intent = new Intent(getApplicationContext(), GotoQuizActivity.class);
                    startActivity(intent);
                } else {
                    Database.setCurrentLesson(Database.getCurrentLesson() + 1);
                    setContent(learnImage, learnText);
                }
            }
        });

    }

    private void setContent(ImageView image, EditText text) {
        image.setImageResource(LessonService.getImage());
        text.setText(LessonService.getText());
    }

}
