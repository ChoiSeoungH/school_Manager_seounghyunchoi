package Utils;

import dao.StudentDAO;
import dao.SubjectDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Utils {

  private static final String CUR_PATH = System.getProperty("user.dir") + "\\src\\";
  private static final Scanner sc = new Scanner(System.in);

  public static String getValue(String msg) {
    System.out.print(msg);
    return sc.nextLine();
  }

  public static int getValue(String msg, int start, int end) {
    while (true) {
      try {
        System.out.print(msg);
        int val = sc.nextInt();
        if (val < start || val > end) {
          System.out.println(start + " ~ " + end + "사이의 값을 입력해주세요");
          continue;
        }
        return val;
      } catch (Exception e) {
        sc.nextLine();
        System.out.println("숫자를 입력해주세요");
      }
    }
  }

  private static String loadData(String fileName) {
    String data = "";
    try (BufferedReader br = new BufferedReader(new FileReader(CUR_PATH + fileName))) {
      while (true) {
        String line = br.readLine();
        if (line == null) break;
        data += line;
        data += "\n";
      }
      data = data.substring(0, data.length() - 1);
    } catch (Exception e) {
      System.out.println(fileName + "로드 실패");
      e.printStackTrace();
    }
    return data;
  }

  public static void loadFile(StudentDAO stuDAO, SubjectDAO subDAO) {
    String stuData = loadData("student.txt");
    String subData = loadData("subject.txt");

    stuDAO.addStudentFromData(stuData);
    subDAO.addSubjectFromData(subData);
    stuDAO.updateMaxNo();
    System.out.println("데이터 로드 완료");

  }

  private static void saveData(String fileName, String data) {
    try (FileWriter fw = new FileWriter(CUR_PATH + fileName)) {
      fw.write(data);
      System.out.println(fileName + "저장완료");
    } catch (IOException e) {
      System.out.print("입출력 에러");
    }
  }


  public static void saveFile(StudentDAO stuDAO, SubjectDAO subDAO) {
    String stuData = stuDAO.getDataFromList();
    String subData = subDAO.getDataFromList();

    saveData("student.txt", stuData);
    saveData("subject.txt", subData);
  }
}
