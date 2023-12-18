/*
  무조건 파일 업로드 먼저
  처음부터 우리 데이터가 연결된 상태

*/
public class Controller {
  private StudentDAO stuDAO;
  private SubjectDAO subDAO;

  public Controller() {
    stuDAO = new StudentDAO();
    subDAO = new SubjectDAO();
    Utils.loadFile(stuDAO, subDAO);
  }

  public void run() {
    outer : while (true) {
      mainMenu();
      int start =0, end =8;
      int menu = Utils.getValue("메뉴[%d-%d] 입력 >> ".formatted(start,end), start, end);
      switch (menu) {
        case 0:
          System.out.println("종료");
          break outer;
        case 1:
          stuDAO.addStudent();
          break;
        case 2:
          stuDAO.deleteStudent(subDAO);
          break;
        case 3:
          stuDAO.addStudentsSubject(subDAO);
          break;
        case 4:
          stuDAO.deleteStudentsSubject(subDAO);
          break;
        case 5:
          stuDAO.printStudentByRank(subDAO);
          break;
        case 6:
          subDAO.printStudentBySubject(stuDAO);
          break;
        case 7:
          Utils.saveFile(stuDAO,subDAO);
          break;
        case 8:
          Utils.loadFile(stuDAO,subDAO);
          break;
      }


    }//eow

  }//eom

  private void mainMenu() {
    System.out.println("[1]학생추가"); // 학번(1001) 자동증가 : 학생id 중복 불가
    System.out.println("[2]학생삭제"); // 학생 id 입력후 삭제 과목도 같이 삭제
    System.out.println("[3]과목추가"); //학번 입력후 점수 랜덤 50-100 : 과목이름 중복 저장불가능
    System.out.println("[4]과목삭제"); // 학번 입력후 과목삭제
    System.out.println("[5]전체학생목록"); // 점수로 출력
    System.out.println("[6]과목별학생목록"); // 과목이름 입력받아서 해당 과목 학생이름과 과목점수 출력 (오름차순 이름순)
    System.out.println("[7]파일저장");
    System.out.println("[8]파일로드");
    System.out.println("[0] 종료");
  }
}
