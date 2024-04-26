package com.example.test;

import com.example.test.entity.Test;
import com.example.test.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class TestApplication {

	/*public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}*/
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args).getBean(TestApplication.class).execute();
	}

	@Autowired
	TestRepository repository;

	private void execute(){
		//setup();
		//showList();
		//showOne();
		//updateTest();
		deleteTest();
	}

	private void setup(){
		Test test1 = new Test(null, "JDK는 JRE와 컴파일러 등의 개발 도구가 포함된다.", true, "오윤석");
		test1 = repository.save(test1);
		System.out.println("등록한 퀴즈는," +test1+ "입니다.");

		Test test2 = new Test(null, "자바 프로그램을 개발하려면 JDK가 반드시 필요하다.", true, "오윤석");
		test2 = repository.save(test2);
		System.out.println("등록한 퀴즈는," +test2+ "입니다.");
	}

	private void showList(){
		System.out.println("---모든 데이터 취득 개시---");
		Iterable<Test> testzes=repository.findAll();
		for(Test test: testzes){
			System.out.println(test);
		}
		System.out.println("---모든 데이터 취득 완료---");
	}

	private void showOne(){
		System.out.println("---1건 취득 개시---");
		Optional<Test> testOpt = repository.findById(1);
		if(testOpt.isPresent()){ //.isPresent() > 있는지 없는지
			System.out.println(testOpt.get());
		}else {
			System.out.println("해당 데이터는 존재하지 않습니다.");
		}
		System.out.println("---1건 취득 완료---");
	}

	private void updateTest(){
		System.out.println("---변경 처리 개시---");
		Test test1 = new Test(1, "JVM은 운영체제별로 동일한 JVM이 사용된다.", false, "홍길동");
		test1 = repository.save(test1);
		System.out.println("변경된 데이터는 " + test1 + "입니다.");
		System.out.println("---변경 처리 완료---");
	}

	private void deleteTest(){
		System.out.println("---삭제 처리 개시---");
		repository.deleteById(2);
		System.out.println("---삭제 처리 완료---");
	}

}