//MultipleChoiceQuestion object class in which one of 3 choices is correct. Also stores mark for question.

public class MultipleChoiceQuestion {
  private Boolean[] allAnswers = {false, false, false};

  private int mark;

  public MultipleChoiceQuestion(Boolean a, Boolean b, Boolean c, int mk) {
    if (a == true) {
      allAnswers[0] = true;
    }
    if (b == true) {
      allAnswers[1] = true;
    }
    if (c == true) {
      allAnswers[2] = true;
    }//END if

    mark = mk;
  }//END constructor MultipleChoiceQuestion

  public Boolean[] getAnswer() {
    return allAnswers;
  }//END getAnswer

  public int getMark() {
    return mark;
  }//END getMark

}//END class MultipleChoiceQuestion
