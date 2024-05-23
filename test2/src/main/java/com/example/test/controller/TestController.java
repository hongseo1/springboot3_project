package com.example.test.controller;

import com.example.test.entity.Test;
import com.example.test.form.TestForm;
import com.example.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/test")
public class TestController {
    /** DI 대상 */
    @Autowired
    TestService service;

    /** form-backing bean의 초기화 */
    @ModelAttribute
    public TestForm setUpForm() {
        TestForm form = new TestForm();
        // 라디오 버튼의 초깃값 설정
        form.setAnswer(true);
        return form;
    }

    /** Test 목록 표시 */
    @GetMapping
    private String showList(TestForm testForm, Model model) {
        //신규 등록 설정(요부분)
        testForm.setNewTest(true);
        //퀴즈 목록 취득
        Iterable<Test> list = service.selectAll();
        //표시용 Model에 저장
        model.addAttribute("list", list);
        model.addAttribute("title","등록 폼");
        return "crud";
    }

    @PostMapping("/insert")
    public String insert(@Validated TestForm testForm, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes) {
        // Form에서 Entity로 넣기
        Test test = new Test();
        test.setQuestion(testForm.getQuestion());
        test.setAnswer(testForm.getAnswer());
        test.setAuthor(testForm.getAuthor());

        // 입력 체크
        if (!bindingResult.hasErrors()) {
            service.insertTest(test);
            redirectAttributes.addFlashAttribute("complete", "등록이 완료되었습니다");
            return "redirect:/test";
        } else {
            // 에러가 발생한 경우에는 목록 표시로 변경
            return showList(testForm, model);
        }
    }

    /** Test 데이터를 1건 취득해서 폼 안에 표시 */
    @GetMapping("/{id}")
    public String showUpdate(TestForm testForm, @PathVariable Integer id, Model model) {
        // Test를 취득(Optional로 래핑)
        Optional<Test> testOpt = service.selectOneById(id);

        // TestForm에 채워넣기
        Optional<TestForm> testFormOpt = testOpt.map(t -> makeTestForm(t));

        // TestForm이 null이 아니라면 값을 취득
        if(testFormOpt.isPresent()) {
            testForm = testFormOpt.get();
        }

        // 변경용 모델 생성
        makeUpdateModel(testForm, model);
        return "crud";
    }

    /** 변경용 모델 생성 */
    private void makeUpdateModel(TestForm testForm, Model model) {
        model.addAttribute("id", testForm.getId());
        testForm.setNewTest(false);
        model.addAttribute("testForm", testForm);
        model.addAttribute("title", "변경 폼");
    }

    /** id를 키로 사용해 데이터를 변경 */
    @PostMapping("/update")
    public String update(
            @Validated TestForm testForm,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        // TestForm에서 Test으로 채우기
        Test test = makeTest(testForm);
        // 입력 체크
        if (!result.hasErrors()) {
            // 변경 처리, Flash scope를 사용해서 리다이렉트 설정
            service.updateTest(test);
            redirectAttributes.addFlashAttribute("complete", "변경이 완료되었습니다");
            // 변경 화면을 표시
            return "redirect:/test/" + test.getId();
        } else {
            // 변경용 모델을 생성
            makeUpdateModel(testForm, model);
            return "crud";
        }
    }

// ----------【 아래는 Form과 도메인 객체를 다시 채우기】 ----------
    /** TestForm에서 Test로 다시 채우기, 반환값으로 돌려줌 */
    private Test makeTest(TestForm testForm) {
        Test test = new Test();
        test.setId(testForm.getId());
        test.setQuestion(testForm.getQuestion());
        test.setAnswer(testForm.getAnswer());
        test.setAuthor(testForm.getAuthor());
        return test;
    }

    /** Test에서 TestForm로 다시 채우기, 반환값으로 돌려줌 */
    private TestForm makeTestForm(Test test) {
        TestForm form = new TestForm();
        form.setId(test.getId());
        form.setQuestion(test.getQuestion());
        form.setAnswer(test.getAnswer());
        form.setAuthor(test.getAuthor());
        form.setNewTest(false);
        return form;
    }

    /** id를 키로 사용해 데이터를 삭제 */
    @PostMapping("/delete")
    public String delete(
            @RequestParam("id") String id,
            Model model,
            RedirectAttributes redirectAttributes) {
        // 퀴즈 정보를 1건 삭제하고 리다이렉트
        service.deleteTestById(Integer.parseInt(id));
        redirectAttributes.addFlashAttribute("delcomplete", "삭제 완료했습니다");
        return "redirect:/test";
    }

    /** Test 데이터를 랜덤으로 한 건 가져와 화면에 표시 */
    Integer nextId = 0;
    @GetMapping("/play")
    public String showTest(TestForm testForm, Model model) {
        // Test 정보 취득(Optional으로 래핑)
        Optional<Test> testOpt = service.selectOneById(++nextId);
        //Optional<Test> testOpt = service.selectOneRandomTest();

        // 값이 있는지 확인
        if(testOpt.isPresent()) {
            // TestForm으로 채우기
            Optional<TestForm> testFormOpt = testOpt.map(t -> makeTestForm(t));
            testForm = testFormOpt.get();
        } else {
            model.addAttribute("msg", "등록된 문제가 없습니다");
            nextId = 0;
            return "play";
        }

        // 표시용 모델에 저장
        model.addAttribute("testForm", testForm);

        return "play";
    }

    /** 퀴즈의 정답/오답 판단 */
    @PostMapping("/check")
    public String checkTest(TestForm testForm, @RequestParam Boolean answer, Model model){
        if (service.checkTest(testForm.getId(), answer)) {
            model.addAttribute("msg","정답입니다.");
        } else {
            model.addAttribute("msg","오답입니다.");
        }
        return "answer";
    }
}
