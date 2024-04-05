package com.br.spring.ioc.xml;

public class StudentDao {
	public void selectStudentList() {
		System.out.println("dao쪽 학생 전체 목록 조회용 메소드 실행");
	}
	
	public void insertStudent(Student s) {
		System.out.println("dao쪽 학생 등록용 메소드 실행");
	}
}
