package android.countrylearning.activity;

import android.app.Activity;
import android.content.Intent;
import android.countrylearning.datasource.Database;
import android.countrylearning.lesson.LessonService;
import android.countrylearning.R;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AnswersActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);

        final ImageView quizImage = findViewById(R.id.quiz_image);
        final TextView result = findViewById(R.id.result);
        final TextView question = findViewById(R.id.question);
        final RadioGroup answers = findViewById(R.id.answers);
        final RadioButton answer1 = findViewById(R.id.answer1);
        final RadioButton answer2 = findViewById(R.id.answer2);
        final RadioButton answer3 = findViewById(R.id.answer3);
        Button previousButton = findViewById(R.id.previous);
        Button nextButton = findViewById(R.id.next);

        setContent(quizImage, question,
                   answer1, answer2, answer3);
        selectAnswer(answers);
        checkAnswer(answers, result);
        setRadioButtonsUntouchable(answers);
        previousButton.setText(R.string.go_back);
        nextButton.setText(R.string.next);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Database.getCurrentLesson() == 0) {
                    Intent intent = new Intent(getApplicationContext(), GotoAnswersActivity.class);
                    startActivity(intent);
                } else {
                    Database.setCurrentLesson(Database.getCurrentLesson() - 1);
                    setContent(quizImage, question,
                               answer1, answer2, answer3);
                    selectAnswer(answers);
                    checkAnswer(answers, result);
                    setRadioButtonsUntouchable(answers);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Database.getCurrentLesson() == Database.getNumberOfLessons() - 1) {
                    Intent intent = new Intent(getApplicationContext(), FinishActivity.class);
                    startActivity(intent);
                } else {
                    Database.setCurrentLesson(Database.getCurrentLesson() + 1);
                    setContent(quizImage, question,
                               answer1, answer2, answer3);
                    selectAnswer(answers);
                    checkAnswer(answers, result);
                    setRadioButtonsUntouchable(answers);
                }
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


    private void selectAnswer(RadioGroup answers) {
        int answerId = LessonService.getAnswerId();
        answers.check(answerId);
    }

    private void checkAnswer(RadioGroup answers, TextView result) {
        RadioButton correctAnswerButton = null;
        String correctAnswer = getResources().getString(LessonService.getCorrectAnswer());
        RadioButton providedAnswerButton = findViewById(LessonService.getAnswerId());

        for(int i = 0; i < answers.getChildCount(); i++) {
            RadioButton answer =  (RadioButton) answers.getChildAt(i);

            if(answer.getText().toString().equals(correctAnswer)) {
                correctAnswerButton = answer;
            }
        }

        changeRadioButtonsColourToBlack(answers);

        if(LessonService.isAnsweredCorrectly()) {
            providedAnswerButton.setTextColor(Color.GREEN);
            result.setText(R.string.correct_answer);

        } else {
            providedAnswerButton.setTextColor(Color.RED);
            correctAnswerButton.setTextColor(Color.GREEN);
            result.setText(R.string.wrong_answer);
        }
    }

    private void changeRadioButtonsColourToBlack(RadioGroup answers) {
        for(int i = 0; i < answers.getChildCount(); i++) {
            RadioButton answer =  (RadioButton) answers.getChildAt(i);
            answer.setTextColor(Color.BLACK);
        }
    }

    private void setRadioButtonsUntouchable(RadioGroup answers) {
        for(int i = 0; i < answers.getChildCount(); i++) {
            RadioButton answer =  (RadioButton) answers.getChildAt(i);
            answer.setEnabled(false);
        }
    }

}