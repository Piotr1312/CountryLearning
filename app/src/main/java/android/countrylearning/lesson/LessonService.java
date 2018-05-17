package android.countrylearning.lesson;

import android.countrylearning.datasource.Database;

import java.util.List;

public class LessonService {
    private static List<Lesson> lessons;

    static {
        lessons = Database.getLessons();
    }

    public static int getImage() {
        if (Database.getCurrentRun() != 0 && !getCurrentLesson().isAnsweredCorrectly()) {
            return getCurrentLesson().getImage2();
        } else {
            return getCurrentLesson().getImage1();
        }
    }

    public static int getText() {
        if (Database.getCurrentRun() != 0 && !getCurrentLesson().isAnsweredCorrectly()) {
            return getCurrentLesson().getText2();
        } else {
            return getCurrentLesson().getText1();
        }
    }

    public static int getQuestion() {
        if (Database.getCurrentRun() != 0) {
            return getCurrentLesson().getQuestion2();
        } else {
            return getCurrentLesson().getQuestion1();
        }
    }

    public static int getAnswer(int number) {
        int answer = 0;

        switch (number) {
            case 1:
                if (Database.getCurrentRun() != 0) {
                    answer = getCurrentLesson().getQuestion2_answer1();
                } else {
                    answer = getCurrentLesson().getQuestion1_answer1();
                }
                break;
            case 2:
                if (Database.getCurrentRun() != 0) {
                    answer = getCurrentLesson().getQuestion2_answer2();
                } else {
                    answer = getCurrentLesson().getQuestion1_answer2();
                }
                break;
            case 3:
                if (Database.getCurrentRun() != 0) {
                    answer = getCurrentLesson().getQuestion2_answer3();
                } else {
                    answer = getCurrentLesson().getQuestion1_answer3();
                }
        }

        return answer;
    }

    public static void setAnswerId(int id) {
        if (Database.getCurrentRun() != 0) {
            getCurrentLesson().setQuestion2_answer(id);
        } else {
            getCurrentLesson().setQuestion1_answer(id);
        }
    }

    public static int getAnswerId() {
        if (Database.getCurrentRun() != 0) {
            return getCurrentLesson().getQuestion2_answer();
        } else {
            return getCurrentLesson().getQuestion1_answer();
        }
    }

    public static int getCorrectAnswer() {
        if (Database.getCurrentRun() != 0) {
            return getCurrentLesson().getQuestion2_answer_right();
        } else {
            return getCurrentLesson().getQuestion1_answer_right();
        }
    }

    public static void setAnsweredCorrectly(boolean answeredCorrectly) {
        getCurrentLesson().setAnsweredCorrectly(answeredCorrectly);
    }

    public static boolean isAnsweredCorrectly() {
        return getCurrentLesson().isAnsweredCorrectly();
    }

    private static Lesson getCurrentLesson() {
        return lessons.get(Database.getCurrentLesson());
    }

}