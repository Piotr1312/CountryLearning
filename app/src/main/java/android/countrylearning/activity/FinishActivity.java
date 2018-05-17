package android.countrylearning.activity;

import android.app.Activity;
import android.content.Intent;
import android.countrylearning.R;
import android.countrylearning.datasource.Database;
import android.countrylearning.lesson.Lesson;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class FinishActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        TextView resultText = findViewById(R.id.result);
        TextView finishQuestion = findViewById(R.id.finish_question);
        Button finishButton = findViewById(R.id.finish);
        Button goBackButton = findViewById(R.id.go_back);

        int result = calculateResult();
        int numberOfQuestions = Database.getNumberOfLessons();
        resultText.setText(getString(R.string.result, result, numberOfQuestions));
        setContent(finishButton, goBackButton, finishQuestion);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Database.getCurrentRun() == 0) {
                    Database.setCurrentRun(1);
                    Database.setCurrentLesson(0);
                    Intent intent = new Intent(getApplicationContext(), LearningActivity.class);
                    startActivity(intent);
                } else {
                    Database.resetData();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Database.getCurrentRun() == 0) {
                    Database.setCurrentLesson(Database.getNumberOfLessons() - 1);
                    Intent intent = new Intent(getApplicationContext(), AnswersActivity.class);
                    startActivity(intent);
                } else {
                    Database.resetData();
                    finishAffinity();
                }
            }
        });
    }

    private int calculateResult() {
        int result = 0;
        List<Lesson> lessons = Database.getLessons();

        for (Lesson lesson : lessons) {
            if (lesson.isAnsweredCorrectly() == true) {
                result++;
            }
        }

        return result;
    }

    private void setContent(Button finishButton, Button goBackButton, TextView finishQuestion) {
        if (Database.getCurrentRun() == 0) {
            finishButton.setText(R.string.yes);
            goBackButton.setText(R.string.go_back);
        } else {
            finishQuestion.setVisibility(View.INVISIBLE);
            finishButton.setText(R.string.main_menu);
            goBackButton.setText(R.string.exit);
        }
    }

}