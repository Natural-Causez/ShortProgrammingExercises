//NumericalQuestion object class to represent a question with a numerical answer. Also stores mark for question.

public class NumericalQuestion {
  private int answer;
  private int mark;

  public NumericalQuestion(int ans, int mk) {
    answer = ans;
    mark = mk;
  }//END setAnswer

  public int getAnswer() {
    return answer;
  }//END getAnswer

  public int getMark () {
    return mark;
  }//END getMark

}//END class NumericalQuestion
