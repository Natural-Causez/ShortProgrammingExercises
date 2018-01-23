//BooleanQuestion object class in which answer is true or false. Also stores mark for question.

public class BooleanQuestion {
  private Boolean answer;
  private int mark;

  public BooleanQuestion (Boolean ans, int mk) {
    answer = ans;
    mark = mk;
  }//END constructor BooleanQuestion

  public Boolean getAnswer() {
    return answer;
  }//END getAnswer

  public int getMark() {
    return mark;
  }//END getMark
}//END class BooleanQuestion
