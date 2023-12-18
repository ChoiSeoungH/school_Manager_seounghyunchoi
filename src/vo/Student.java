package vo;

public class Student {
  private final int stuNo;
  private final String stuName;
  private final String stuId;

  public Student(int stuNo, String stuName, String stuId) {
    this.stuNo = stuNo;
    this.stuName = stuName;
    this.stuId = stuId;
  }

  public int getStuNo() {
    return stuNo;
  }

  public String getStuName() {
    return stuName;
  }

  public String getStuId() {
    return stuId;
  }

  @Override
  public String toString() {
    String data = stuNo + "\t" + stuName + "\t" + stuId + "\t";
    return data;
  }

}
