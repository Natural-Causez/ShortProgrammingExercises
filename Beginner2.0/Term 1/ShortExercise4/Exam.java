//Exam object class to initialise a NumericalQuestion, a BooleanQuestion and a MultipleChoiceQuestion

public class Exam {
  private NumericalQuestion question1;
  private BooleanQuestion question2;
  private MultipleChoiceQuestion question3;

  private int totalMark;

  public Exam(NumericalQuestion q1, BooleanQuestion q2, MultipleChoiceQuestion q3) {
    question1 = q1;
    question2 = q2;
    question3 = q3;

    totalMark = question1.getMark() + question2.getMark() + question3.getMark();
  }//END constructor Exam

  public int getQ1Ans() {
    return question1.getAnswer();
  }//END getQ1Ans

  public Boolean getQ2Ans() {
    return question2.getAnswer();
  }//END getQ2Ans

  public Boolean[] getQ3Ans() {
    return question3.getAnswer();
  }//END getQ3Ans

  public int getQ1Marks() {
    return question1.getMark();
  }//END getQ1Marks

  public int getQ2Marks() {
    return question2.getMark();
  }//END getQ2Marks

  public int getQ3Marks() {
    return question3.getMark();
  }//END getQ3Marks

}//END class Exam
