//Marker object class which takes a structured markScheme and an attempt and calculates then returns the mark scored
//Classification can be calculated also.

import java.util.Arrays;

public class Marker {
  public int markAttempt (Exam markScheme, Exam examAttempt) {
    int marksQ1 = 0;
    int marksQ2 = 0;
    int marksQ3 = 0;

    int totalMarksAwarded = 0;

    int markSchemeAnswerQ1 = markScheme.getQ1Ans();
    Boolean markSchemeAnswerQ2 = markScheme.getQ2Ans();
    Boolean [] markSchemeAnswerQ3 = markScheme.getQ3Ans();

    int marksAwardedQ1 = markScheme.getQ1Marks();
    int marksAwardedQ2 = markScheme.getQ2Marks();
    int marksAwardedQ3 = markScheme.getQ3Marks();

    int examAttemptAnswerQ1 = examAttempt.getQ1Ans();
    Boolean examAttemptAnswerQ2 = examAttempt.getQ2Ans();
    Boolean [] examAttemptAnswerQ3 = examAttempt.getQ3Ans();

    //Calculate answer for question 1
    if (examAttemptAnswerQ1 == markSchemeAnswerQ1) {
      marksQ1 += marksAwardedQ1;
    }
    else if (examAttemptAnswerQ1 == (markSchemeAnswerQ1 + 1) || (examAttemptAnswerQ1 == (markSchemeAnswerQ1 - 1))) {
      marksQ1 += marksAwardedQ1 - 1;
    }
    else if (examAttemptAnswerQ1 < (markSchemeAnswerQ1 + 5) && (examAttemptAnswerQ1 > (markSchemeAnswerQ1 - 5))) {
      marksQ1 += 1;
    }//END if
    System.out.println("Question 1: " + marksQ1 + " of " + marksAwardedQ1);

    //Calculate answer for question 2
    if (examAttemptAnswerQ2 == markSchemeAnswerQ2) {
      marksQ2 += marksAwardedQ2;
    }//END if
    System.out.println("Question 2: " + marksQ2 + " of " + marksAwardedQ2);

    //Calculate answer for question 3
    if (Arrays.equals(examAttemptAnswerQ3, markSchemeAnswerQ3)) {
      marksQ3 += marksAwardedQ3;
      System.out.println("Question 3: " + marksQ3 + " of " + marksAwardedQ3);
    }
    else {
      int correctChoices = 0;
      for (int i = 0; i < 3; i++) {
        if (examAttemptAnswerQ3[i] == markSchemeAnswerQ3[i]) {
          correctChoices++;
        }//END if
      }//END for

      marksQ3 += correctChoices;
      System.out.println("Question 3: " + marksQ3 + " of " + marksAwardedQ3);
    }//END if

    totalMarksAwarded = marksQ1 + marksQ2 + marksQ3;
    return totalMarksAwarded;
  }//END markAttempt

  public double convertMarksToClassification (int mark, double firstBoundary, double upperSecondBoundary, double lowerSecondBoundary) {
    if (mark <= firstBoundary) {
      System.out.println("Classification: 1.1");
      return 1.1;
    }
    else if (mark <= upperSecondBoundary && mark < firstBoundary) {
      System.out.println("Classification: 2.1");
      return 2.1;
    }
    else if (mark <= lowerSecondBoundary && mark < upperSecondBoundary) {
      System.out.println("Classification: 2.2");
      return 2.2;
    }
    System.out.println("Classification: 0.0");
    return 0.0;
  }//END convertMarksToClassification
}//END class Marker
