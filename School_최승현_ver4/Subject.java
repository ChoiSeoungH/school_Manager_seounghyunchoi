package School_최승현_ver4;

public class Subject {
  private int stuNo;
  private String subName;
  private int score;

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
