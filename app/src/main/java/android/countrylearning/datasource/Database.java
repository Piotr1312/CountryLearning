package android.countrylearning.datasource;

import android.countrylearning.R;
import android.countrylearning.lesson.Lesson;
import android.countrylearning.lesson.LessonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static int currentRun;
    private static int currentLesson;
    private static List<Lesson> lessons;
    private static LessonBuilder builder;
    private static int numberOfLessons;

    static {
        currentRun = 0;
        currentLesson = 0;
        lessons = new ArrayList<>();

        builder = new LessonBuilder();
        builder.setLesson1(R.drawable.france1, R.string.france_description1);
        builder.setLesson2(R.drawable.france2, R.string.france_description2);
        builder.setQuestion1(R.string.france_question1, R.string.france_question1_answer1,
                R.string.france_question1_answer2, R.string.france_question1_answer3,
                R.string.france_question1_answer_right);
        builder.setQuestion2(R.string.france_question2, R.string.france_question2_answer1,
                R.string.france_question2_answer2, R.string.france_question2_answer3,
                R.string.france_question2_answer_right);
        lessons.add(builder.build());

        builder = new LessonBuilder();
        builder.setLesson1(R.drawable.italy1, R.string.italy_description1);
        builder.setLesson2(R.drawable.italy2, R.string.italy_description2);
        builder.setQuestion1(R.string.italy_question1, R.string.italy_question1_answer1,
                R.string.italy_question1_answer2, R.string.italy_question1_answer3,
                R.string.italy_question1_answer_right);
        builder.setQuestion2(R.string.italy_question2, R.string.italy_question2_answer1,
                R.string.italy_question2_answer2, R.string.italy_question2_answer3,
                R.string.italy_question2_answer_right);
        lessons.add(builder.build());

        builder = new LessonBuilder();
        builder.setLesson1(R.drawable.germany1, R.string.germany_description1);
        builder.setLesson2(R.drawable.germany2, R.string.germany_description2);
        builder.setQuestion1(R.string.germany_question1, R.string.germany_question1_answer1,
                R.string.germany_question1_answer2, R.string.germany_question1_answer3,
                R.string.germany_question1_answer_right);
        builder.setQuestion2(R.string.germany_question2, R.string.germany_question2_answer1,
                R.string.germany_question2_answer2, R.string.germany_question2_answer3,
                R.string.germany_question2_answer_right);
        lessons.add(builder.build());

        builder = new LessonBuilder();
        builder.setLesson1(R.drawable.poland1, R.string.poland_description1);
        builder.setLesson2(R.drawable.poland2, R.string.poland_description2);
        builder.setQuestion1(R.string.poland_question1, R.string.poland_question1_answer1,
                R.string.poland_question1_answer2, R.string.poland_question1_answer3,
                R.string.poland_question1_answer_right);
        builder.setQuestion2(R.string.poland_question2, R.string.poland_question2_answer1,
                R.string.poland_question2_answer2, R.string.poland_question2_answer3,
                R.string.poland_question2_answer_right);
        lessons.add(builder.build());

        builder = new LessonBuilder();
        builder.setLesson1(R.drawable.russia1, R.string.russia_description1);
        builder.setLesson2(R.drawable.russia2, R.string.russia_description2);
        builder.setQuestion1(R.string.russia_question1, R.string.russia_question1_answer1,
                R.string.russia_question1_answer2, R.string.russia_question1_answer3,
                R.string.russia_question1_answer_right);
        builder.setQuestion2(R.string.russia_question2, R.string.russia_question2_answer1,
                R.string.russia_question2_answer2, R.string.russia_question2_answer3,
                R.string.russia_question2_answer_right);
        lessons.add(builder.build());

        numberOfLessons = lessons.size();
    }

    public static int getCurrentRun() {
        return currentRun;
    }

    public static void setCurrentRun(int currentRun) {
        Database.currentRun = currentRun;
    }

    public static int getCurrentLesson() {
        return currentLesson;
    }

    public static void setCurrentLesson(int currentLesson) {
        Database.currentLesson = currentLesson;
    }

    public static List<Lesson> getLessons() {
        return lessons;
    }

    public static void resetData() {
        setCurrentRun(0);
        setCurrentLesson(0);
        for(Lesson lesson: lessons) {
            lesson.setQuestion1_answer(0);
            lesson.setQuestion2_answer(0);
            lesson.setAnsweredCorrectly(null);
        }
    }

    public static int getNumberOfLessons() {
        return numberOfLessons;
    }

}
