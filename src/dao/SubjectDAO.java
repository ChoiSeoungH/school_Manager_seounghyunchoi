package dao;

import Utils.Utils;
import vo.Student;
import vo.Subject;

import java.util.ArrayList;
import java.util.Random;

public class SubjectDAO {
  private final ArrayList<Subject> subList;

  public SubjectDAO() {
    subList = new ArrayList<>();
  }

  public ArrayList<Subject> getSubList() {
    return subList;
  }

  public void addSubjectFromData(String subData) {
    String[] temp = subData.split("\n");
    for (String s : temp) {
      String[] info = s.split("/");
      subList.add(new Subject(Integer.parseInt(info[0]), info[1], Integer.parseInt(info[2])));
    }
  }

  public String getDataFromList() {
    String data = "";
    for (Subject sub : subList) {
      data += sub.getStuNo() + "/" + sub.getSubName() + "/" + sub.getScore() + "\n";
    }
    return data;
  }

  public void deleteOnesSubject(int stuNo) {
    int cnt = 0;
    for (int i = 0; i < subList.size(); i++) {
      if (subList.get(i).getStuNo() == stuNo) {
        System.out.print(subList.get(i).getSubName() + " " + subList.get(i).getScore() + "점 ");
        cnt += 1;
      }
    }
    System.out.println();
    if (cnt == 0) {
      System.out.println("[no subjectData]");
      return;
    }
    String name = Utils.getValue("삭제할 과목 이름 입력 >> ");
    if (!findSubjectname(name)) {
      System.out.println("해당과목 이름이 없습니다");
      return;
    }


    for (int i = 0; i < subList.size(); i++) {
      if (subList.get(i).getStuNo() == stuNo && subList.get(i).getSubName().equals(name)) {
        subList.remove(i);
        System.out.println(subList.get(i).getSubName() + " " + subList.get(i).getScore() + "점 삭제완료");
      }
    }

    printOnesSubject(stuNo);
    System.out.println("\n삭제완료");
  }

  private void printOnesSubject(int stuNo) {
    for (Subject subject : subList) {
      if (subject.getStuNo() == stuNo) {
        System.out.print(subject);
      }
    }
  }

  public void addOnesSubject(int stuNo) {
    if (subList.size() == 0) {
      System.out.println("과목이없습니다");
      return;
    }
    Random rd = new Random();
    String name = Utils.getValue("과목 >> ");
    if (findSubjectname(stuNo, name)) {
      System.out.println("과목이름 중복");
      return;
    }

    int score = rd.nextInt(51) + 50;
    subList.add(new Subject(stuNo, name, score));


    printOnesSubject(stuNo);
    System.out.println("추가완료");
  }

  private boolean findSubjectname(int stuNo, String name) {
    for (Subject sub : subList) {
      if (sub.getStuNo() == stuNo && sub.getSubName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  private boolean findSubjectname(String name) {
    for (Subject sub : subList) {
      if (sub.getSubName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  public ArrayList<Double> SortByScore(ArrayList<Student> stuList) {
    ArrayList<Double> avg = new ArrayList<Double>();
    for (Student stu : stuList) {
      int cnt = 0;
      double score = 0.0;
      for (Subject sub : subList) {
        if (stu.getStuNo() == sub.getStuNo()) {
          score += sub.getScore();
          cnt += 1;
        }
      }
      if (cnt == 0) {
        avg.add(0.0);
      } else {
        score /= cnt;
        avg.add(score);
      }
    }
    return avg;

  }


  public void printStudentBySubject(StudentDAO stuDAO) {
    String name = Utils.getValue("과목 >> ");
    ArrayList<Integer> list = new ArrayList<Integer>();
    if (!findSubjectname(name)) {
      System.out.println("찾는 과목이 없습니다");
      return;
    }

    for (Subject sub : subList) {
      if (sub.getSubName().equals(name)) {
        for (Student stu : stuDAO.getStuList()) {
          if (sub.getStuNo() == stu.getStuNo()) {
            list.add(sub.getStuNo());
          }
        }
      }
    }

    if (list.size() == 0) {
      System.out.println("해당 과목은 학생 데이터가 없습니다");
      return;
    }
    stuDAO.printAllStudentBySubject(list);

  }

  public void deleteOnesAllSubject(int stuNo) {

    for (int i = 0; i < subList.size(); i++) {
      if (subList.get(i).getStuNo() == stuNo) {
        subList.remove(i);
        i--;

      }
    }

    printOnesSubject(stuNo);
    System.out.println("\n삭제완료");
  }
}
