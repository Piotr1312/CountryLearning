package android.countrylearning.lesson;

public class LessonBuilder {
    private Lesson lesson;

    public LessonBuilder() {
        lesson = new Lesson();
    }

    public void setLesson1(int image, int text) {
        lesson.setImage1(image);
        lesson.setText1(text);
    }

    public void setLesson2(int image, int text) {
        lesson.setImage2(image);
        lesson.setText2(text);
    }

    public void setQuestion1(int question, int answer1, int answer2, int answer3, int answerRight) {
        lesson.setQuestion1(question);
        lesson.setQuestion1_answer1(answer1);
        lesson.setQuestion1_answer2(answer2);
        lesson.setQuestion1_answer3(answer3);
        lesson.setQuestion1_answer_right(answerRight);
    }

    public void setQuestion2(int question, int answer1, int answer2, int answer3, int answerRight) {
        lesson.setQuestion2(question);
        lesson.setQuestion2_answer1(answer1);
        lesson.setQuestion2_answer2(answer2);
        lesson.setQuestion2_answer3(answer3);
        lesson.setQuestion2_answer_right(answerRight);
    }

    public Lesson build() {
        return lesson;
    }
}
