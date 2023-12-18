package vo;

public class Subject {
  private final int stuNo;
  private final String subName;
  private final int score;

  public Subject(int stuNo, String subName, int score) {
    this.stuNo = stuNo;
    this.subName = subName;
    this.score = score;
  }

  public int getStuNo() {
    return stuNo;
  }

  public String getSubName() {
    return subName;
  }

  public int getScore() {
    return score;
  }

  @Override
  public String toString() {
    String data = stuNo + "\t" + subName + "\t" + score + "\t";
    return data;
  }


}
