package dao;

import Utils.Utils;
import vo.Student;
import vo.Subject;

import java.util.ArrayList;

public class StudentDAO {
  private final ArrayList<Student> stuList;
  private int maxNo;

  public StudentDAO() {
    stuList = new ArrayList<Student>();
  }

  public ArrayList<Student> getStuList() {
    return stuList;
  }

  public void addStudent() {
    System.out.println("[학생추가]");
    String id = Utils.getValue("▶id >> ");
    if (findStuId(id) != -1) {
      System.out.println("이미 존재하는 아이디가 있습니다");
      return;
    }
    String name = Utils.getValue("이름 >> ");

    stuList.add(new Student(++maxNo, name, id));
    System.out.println(stuList.get(stuList.size() - 1));
    System.out.println("추가완료");

  }

  private int findStuId(String id) {
    int idx = 0;
    for (Student stu : stuList) {
      if (stu.getStuId().equals(id)) {
        return idx;
      }
      idx += 1;
    }
    return -1;
  }

  public void deleteStudent(SubjectDAO subDAO) {
    System.out.println("[한 학생 삭제(과목까지)]");
    if (stuList.size() == 0) {
      System.out.println("[no StudentData]");
    }
    String id = Utils.getValue("▶삭제학생 id >> ");
    if (subDAO.getSubList().size() == 0) {
      System.out.println("[no subject data]");
    }

    int idx = findStuId(id);
    if (idx == -1) {
      System.out.println("존재하지 않는 아이디 입니다");
      return;
    }

    System.out.println(stuList.get(idx));

    subDAO.deleteOnesAllSubject(stuList.get(idx).getStuNo());
    stuList.remove(idx);
    updateMaxNo();
    System.out.println("학생 삭제 완료");
  }

  public void addStudentsSubject(SubjectDAO subDAO) {
    int no = Integer.parseInt(Utils.getValue("학번 >> "));
    int idx = findStuNo(no);
    if (idx == -1) {
      System.out.println("찾는 학번이 없습니다");
      return;
    }
    System.out.println(stuList.get(idx).getStuNo());
    subDAO.addOnesSubject(stuList.get(idx).getStuNo());


  }

  private int findStuNo(int no) {
    int idx = 0;
    for (Student stu : stuList) {
      if (stu.getStuNo() == no) {
        return idx;
      }
      idx += 1;
    }
    return -1;
  }

  public void deleteStudentsSubject(SubjectDAO subDAO) {
    int no = Integer.parseInt(Utils.getValue("학번 >> "));
    int idx = findStuNo(no);
    if (idx == -1) {
      System.out.println("찾는 학번이 없습니다");
      return;
    }
    System.out.println(stuList.get(idx));

    subDAO.deleteOnesSubject(stuList.get(idx).getStuNo());

  }


  public void addStudentFromData(String stuData) {
    String[] temp = stuData.split("\n");
    for (String s : temp) {
      String[] info = s.split("/");
      stuList.add(new Student(Integer.parseInt(info[0]), info[1], info[2]));
    }

  }

  public void updateMaxNo() {
    if (stuList == null) return;
    maxNo = 0;
    for (Student stu : stuList) {
      if (maxNo < stu.getStuNo()) {
        maxNo = stu.getStuNo();
      }
    }
  }

  public String getDataFromList() {
    String data = "";
    for (Student stu : stuList) {
      data += stu.getStuNo() + "/" + stu.getStuName() + "/" + stu.getStuId() + "\n";
    }
    return data;
  }

  public void printStudentByRank(SubjectDAO subDAO) {
    System.out.println("[전체학생 목록]");
    ArrayList<Student> temp = new ArrayList<Student>();

    for (int i = 0; i < stuList.size(); i += 1) {
      temp.add(stuList.get(i));
    }


    ArrayList<Double> avg = subDAO.SortByScore(stuList);
    for (int i = 0; i < avg.size(); i++) {
      double max = avg.get(i);
      for (int j = i; j < avg.size(); j++) {
        if (max < avg.get(j)) {
          max = avg.get(j);
          double temp2 = avg.get(i);
          avg.set(i, avg.get(j));
          avg.set(j, temp2);

          Student temp3 = temp.get(i);
          temp.set(i, temp.get(j));
          temp.set(j, temp3);
        }
      }

    }


    for (int i = 0; i < stuList.size(); i += 1) {
      System.out.println(temp.get(i));
      for (Subject sub : subDAO.getSubList()) {
        if (temp.get(i).getStuNo() == sub.getStuNo()) {
          System.out.print(sub.getSubName() + " " + sub.getScore() + "점 ");
        }
      }
      System.out.println();
      if (avg.get(i) == 0.0) {
        System.out.println("[no subjectdata]");
      } else {
        System.out.printf("평균 %.1f점 %n", avg.get(i));
      }
      System.out.println("--------------");
    }


  }

  public void printAllStudentBySubject(ArrayList<Integer> list) {
    ArrayList<Student> temp = new ArrayList<Student>();

    for (int i = 0; i < list.size(); i += 1) {
      Student stu = getStudentByStuNo(list.get(i));
      temp.add(new Student(stu.getStuNo(), stu.getStuName(), stu.getStuId()));
    }

    for (int i = 0; i < temp.size(); i += 1) {
      String name = temp.get(i).getStuName();
      for (int k = i; k < temp.size(); k += 1) {
        if (temp.get(k).getStuName().compareTo(name) < 0) {
          name = temp.get(k).getStuName();
          Student student = temp.get(k);
          temp.set(k, temp.get(i));
          temp.set(i, student);
        }
      }
    }

    for (Student s : temp) {
      System.out.println(s);
    }

  }

  private Student getStudentByStuNo(int no) {
    if (stuList.size() == 0) return null;
    for (Student s : stuList) {
      if (no == s.getStuNo()) {
        return s;
      }
    }
    return null;
  }
}
