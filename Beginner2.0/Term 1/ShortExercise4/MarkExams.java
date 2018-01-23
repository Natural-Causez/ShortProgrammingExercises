//MarkExams object class which initialises all questions, an exam and a marker to evaluate an attempt.

public class MarkExams {
  public static void main(String[] args) {

    //Defined by assignment
    NumericalQuestion nqMarkScheme = new NumericalQuestion(120369, 11);
    BooleanQuestion bqMarkScheme = new BooleanQuestion(true, 1);
    MultipleChoiceQuestion mcqMarkScheme = new MultipleChoiceQuestion(true, false, false, 3);

    Exam markScheme = new Exam(nqMarkScheme, bqMarkScheme, mcqMarkScheme);

    NumericalQuestion nqAttempt = new NumericalQuestion(120368, 0);
    BooleanQuestion bqAttempt = new BooleanQuestion(false, 0);
    MultipleChoiceQuestion mcqAttempt = new MultipleChoiceQuestion(false, true, true, 0);

    Exam examAttempt = new Exam(nqAttempt, bqAttempt, mcqAttempt);

    Marker marker = new Marker();

    int totalMark = marker.markAttempt(markScheme, examAttempt);
    System.out.println("Total Mark: " + totalMark);

    marker.convertMarksToClassification(totalMark, 13, 11, 8);
  }//END main
}//END class MarkExams
