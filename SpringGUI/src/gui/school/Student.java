package gui.school;

public class Student {
	//Bell bell;//의존성 약화시키기 위해 상위 객체 이용
	
	//생성자 주입도 주입으로 인정된다
//	public Student(Bell bell) {
//		this.bell=bell;
//	}
	
	public void goSchool() {
		System.out.println("등교합니다");
	}
	public void startStudy() {
		System.out.println("수업 시작합니다");
	}
	public void endStudy() {
		System.out.println("수업 종료합니다");
	}
	public void havLunch() {
		System.out.println("점심식사 합니다");
	}
	public void startStudy2() {
		System.out.println("오후수업 시작합니다");
	}
	public void endStudy2() {
		System.out.println("오후수업 종료합니다");
	}
	public void goHome() {
		System.out.println("집으로 갑니다");
	}
}
