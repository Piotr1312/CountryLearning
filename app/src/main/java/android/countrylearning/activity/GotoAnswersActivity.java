package android.countrylearning.activity;

import android.app.Activity;
import android.content.Intent;
import android.countrylearning.R;
import android.countrylearning.datasource.Database;
import android.countrylearning.lesson.Lesson;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class GotoAnswersActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goto_answers);

        Button gotoAnswersButton = findViewById(R.id.goto_answers);
        Button goBackButton = findViewById(R.id.go_back);

        gotoAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allAnswered()) {
                    Database.setCurrentLesson(0);
                    Intent intent = new Intent(getApplicationContext(), AnswersActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.please_answer_all_questions, Toast.LENGTH_SHORT).show();
                }
            }
        });

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.setCurrentLesson(Database.getNumberOfLessons() - 1);
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean allAnswered() {
        boolean allAnswered = true;
        List<Lesson> lessons = Database.getLessons();

        for (Lesson lesson : lessons) {
            if (lesson.isAnsweredCorrectly() == null) {
                allAnswered = false;
                break;
            }
        }

        return allAnswered;
    }
}