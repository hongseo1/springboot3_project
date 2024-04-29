package com.example.test;

import com.example.test.entity.Test;
import com.example.test.repository.TestRepository;
import com.example.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class TestApplication {

	/*public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}*/
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args).getBean(TestApplication.class).execute();
	}

	@Autowired
	//TestRepository repository;
	TestService service;

	private void execute(){
		/*setup();
		showList();
		showOne();*/
		/*updateTest();
		deleteTest();*/
		doTest();
	}

	private void setup(){
		/*
		//TestRepository repository;
		Test test1 = new Test(null, "JDK는 JRE와 컴파일러 등의 개발 도구가 포함된다.", true, "오윤석");
		test1 = repository.save(test1);
		System.out.println("등록한 퀴즈는," +test1+ "입니다.");

		Test test2 = new Test(null, "자바 프로그램을 개발하려면 JDK가 반드시 필요하다.", true, "오윤석");
		test2 = repository.save(test2);
		System.out.println("등록한 퀴즈는," +test2+ "입니다.");
		*/

		//TestService srvice
		System.out.println("---등록 처리 개시---");
		Test test1 = new Test(null, "JDK는 JRE와 컴파일러 등의 개발 도구가 포한된다.", true, "오윤석");
		Test test2 = new Test(null, "자바 프로그램을 개발하려면 JDK가 반드시 필요하다.", true, "오윤석");
		Test test3 = new Test(null, "바이트 코드는 JVM에 독립적이지만 JVM은 운영체제에 종속적이다", true, "오윤석");
		Test test4 = new Test(null, "자바 프로그램을 실행 하려면"+"JRE를 설치해도 상관없다.", true, "오윤석");
		Test test5 = new Test(null, "JVM은 운영체제별로 동일한 JVM이 사용된다.", false, "홍길동");
		List<Test> testList = new ArrayList<>();
		Collections.addAll(testList, test1, test2, test3, test4, test5);
		for(Test test : testList){
			service.insertTest(test);
		}
		System.out.println("---등록 처리 완료---");
	}

	private void showList(){
		/*
		//TestRepository repository;
		System.out.println("---모든 데이터 취득 개시---");
		Iterable<Test> testzes=repository.findAll();
		for(Test test: testzes){
			System.out.println(test);
		}
		System.out.println("---모든 데이터 취득 완료---");
		*/

		//TestService srvice
		System.out.println("---모든 데이터 취득 개시---");
		Iterable<Test> testzes=service.selectAll();
		for(Test test: testzes){
			System.out.println(test);
		}
		System.out.println("---모든 데이터 취득 완료---");
	}

	private void showOne(){
		/*
		//TestRepository repository;
		System.out.println("---1건 취득 개시---");
		Optional<Test> testOpt = repository.findById(1);
		if(testOpt.isPresent()){ //.isPresent() > 있는지 없는지
			System.out.println(testOpt.get());
		}else {
			System.out.println("해당 데이터는 존재하지 않습니다.");
		}
		System.out.println("---1건 취득 완료---");
		*/

		//TestService srvice
		System.out.println("---1건 취득 개시---");
		Optional<Test> testOpt = service.selectOneById(1);
		if(testOpt.isPresent()){ //.isPresent() > 있는지 없는지
			System.out.println(testOpt.get());
		}else {
			System.out.println("해당 데이터는 존재하지 않습니다.");
		}
		System.out.println("---1건 취득 완료---");
	}

	private void updateTest(){
		/*
		//TestRepository repository;
		System.out.println("---변경 처리 개시---");
		Test test1 = new Test(1, "JVM은 운영체제별로 동일한 JVM이 사용된다.", false, "홍길동");
		test1 = repository.save(test1);
		System.out.println("변경된 데이터는 " + test1 + "입니다.");
		System.out.println("---변경 처리 완료---");
		*/

		//TestService srvice
		System.out.println("---변경 처리 개시---");
		Test test1 = new Test(1, "스프링은 프레임워크입니까?", true, "변경담당");
		service.updateTest(test1);
		System.out.println("변경된 데이터는 " + test1 + "입니다.");
		System.out.println("---변경 처리 완료---");
	}

	private void deleteTest(){
		/*
		//TestRepository repository;
		System.out.println("---삭제 처리 개시---");
		repository.deleteById(2);
		System.out.println("---삭제 처리 완료---");
		*/

		//TestService srvice
		System.out.println("---삭제 처리 개시---");
		service.deleteTestById(2);
		System.out.println("---삭제 처리 완료---");
	}

	//TestService srvice
	public  void doTest(){
		System.out.println("---퀴즈 1건 취득 개시---");
		Optional<Test> testOpt = service.selectOneRandomTest();
		if(testOpt.isPresent()){
			System.out.println(testOpt.get());
		}else {
			System.out.println("해당 데이터는 존재하지 않습니다.");
		}
		System.out.println("---퀴즈 1건 취득 완료---");
		Boolean myAnswer = true;
		Integer id = testOpt.get().getId();
		if(service.checkTest(id, myAnswer)){
			System.out.println("정답입니다!!!");
		} else {
			System.out.println("오답입니다.");
		}
	}

}