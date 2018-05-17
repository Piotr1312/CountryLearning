package android.countrylearning.activity;

import android.app.Activity;
import android.content.Intent;
import android.countrylearning.R;
import android.countrylearning.datasource.Database;
import android.countrylearning.lesson.LessonService;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final ImageView quizImage = findViewById(R.id.quiz_image);
        final TextView question = findViewById(R.id.question);
        final RadioGroup answers = findViewById(R.id.answers);
        final RadioButton answer1 = findViewById(R.id.answer1);
        final RadioButton answer2 = findViewById(R.id.answer2);
        final RadioButton answer3 = findViewById(R.id.answer3);
        Button previousButton = findViewById(R.id.previous);
        Button nextButton = findViewById(R.id.next);

        answers.clearCheck();
        setContent(quizImage, question,
                answer1, answer2, answer3);
        checkIfAnswered(answers);
        answers.setVisibility(View.VISIBLE);
        previousButton.setText(R.string.go_back);
        nextButton.setText(R.string.next);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(answers);

                if (Database.getCurrentLesson() == 0) {
                    Intent intent = new Intent(getApplicationContext(), GotoQuizActivity.class);
                    startActivity(intent);
                } else {
                    Database.setCurrentLesson(Database.getCurrentLesson() - 1);
                    setContent(quizImage, question,
                            answer1, answer2, answer3);
                    checkIfAnswered(answers);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(answers);

                if (Database.getCurrentLesson() == Database.getNumberOfLessons() - 1) {
                    Intent intent = new Intent(getApplicationContext(), GotoAnswersActivity.class);
                    startActivity(intent);
                } else {
                    Database.setCurrentLesson(Database.getCurrentLesson() + 1);
                    setContent(quizImage, question,
                            answer1, answer2, answer3);
                    checkIfAnswered(answers);
                }
            }
        });

        answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                LessonService.setAnswerId(checkedId);
            }
        });
    }

    private void setContent(ImageView image, TextView question,
                            RadioButton answer1, RadioButton answer2, RadioButton answer3) {
        image.setImageResource(LessonService.getImage());
        question.setText(LessonService.getQuestion());
        answer1.setText(LessonService.getAnswer(1));
        answer2.setText(LessonService.getAnswer(2));
        answer3.setText(LessonService.getAnswer(3));
    }

    private void checkIfAnswered(RadioGroup answers) {
        int answerId = LessonService.getAnswerId();
        if (answerId == 0) {
            answers.clearCheck();
        } else {
            answers.check(answerId);
        }
    }

    private void checkAnswer(RadioGroup answers) {
        if (answers.getCheckedRadioButtonId() == -1) {
            return;
        }

        String correctAnswer = getResources().getString(LessonService.getCorrectAnswer());
        RadioButton answer = findViewById(LessonService.getAnswerId());
        String providedAnswer = answer.getText().toString();

        if (!correctAnswer.equals(providedAnswer)) {
            LessonService.setAnsweredCorrectly(false);
        } else {
            LessonService.setAnsweredCorrectly(true);
        }
    }

}