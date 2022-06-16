package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.PhoneService;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {
	// 필드
	@Autowired
	private PhoneService phoneService;
	//private PhoneDao phoneDao; // = new PhoneDao() x; 주입해줘
	
	// 생성자
	// 메소드-gs
	// 메소드-일반

	// 전화번호 수정폼
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(Model model, @RequestParam("no") int no) {
		System.out.println("PhoneController>modifyForm");

		// (X)PhoneDao phoneDao = new PhoneDao();
		// (X)PersonVo personVo = phoneDao.getPerson(no);
		
		//Service를 통해 1명 정보 가져오기
		PersonVo personVo = phoneService.getPerson(no);

		// ds 데이터보내기 -->request attribute에 넣는다
		model.addAttribute("personVo", personVo);

		return "modifyForm";
	}

	// 전화번호 수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(Model model, @ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController>modify()");

		// (X)System.out.println(id);
		// (x)phoneDao.personUpdate(personVo);
		
		//Service를 통해 수정하기
		phoneService.personUpdate(personVo);
		
		return "redirect:/list";
	}

	//전화번호 삭제(@PathVariable 사용)
	@RequestMapping(value="/delete2/{no}", method={RequestMethod.GET, RequestMethod.POST})
	public String delete2(@PathVariable("no") int num) {
		System.out.println("PhoneController>delete()");
		
		//주소에서 값 꺼내기
		System.out.println(num);
		
		// Service를 통해서 삭제한다
		int count = phoneService.personDelete(num);
		System.out.println(count);
		
		return "redirect:/list";
	}

	//전화번호 삭제(파라미터 사용)
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		System.out.println("Phonedontrolle>delete()");

		//파라미터 꺼내기
		System.out.println(no);

		// (X)Dao로 처리하기
		// (X)PhoneDao phoneDao = new PhoneDao();
		// (X)phoneDao.personDelete(no);
		
		//Service를 통해 삭제하기
		phoneService.personDelete(no);

		return "redirect:/list";
	}

	// 전화번호 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("PhoneController>list()");

		// (x)Dao를 통해서 personList(주소)를 가져온다
		// (x)PhoneDao phoneDao = new PhoneDao();
		
		//Service를 통해서 personList(주소)를 가져온다
		//PhoneService phoneService = new PhoneService();
		List<PersonVo> personList = phoneService.getPersonList(); //dao의 존재를 몰라요

		// ds 데이터보내기 -->request attribute에 넣는다
		model.addAttribute("personList", personList);

		return "list";
	}

	// 전화번호 등록폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("PhoneController>writeForm");
		
		return "/writeForm";
	}

	// 전화번호 등록(@ModelAttribute 사용)
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController>write()");

		// (x)vo로 묶기--ModelAttribute
		// (x)PersonVo personVo = new PersonVo(name, hp, company);

		// (x)dao로 저장하기
		// (x)PhoneDao phoneDao = new PhoneDao();
		// (x)phoneDao.personInsert(personVo);
		
		//Service를 통해 저장한다.
		phoneService.personInsert(personVo);

		return "redirect:/list";
	}
	
	
	// 전화번호 등록(파라미터 사용)
	@RequestMapping(value="/write2", method={RequestMethod.GET, RequestMethod.POST})
	public String write2(@RequestParam("name") String name, 
					    @RequestParam("hp") String hp,
					    @RequestParam("company") String company) {
		
		System.out.println("PhoneController>write2()");
		
		//vo로 묶기
		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);
		
		// Service를 통해서 저장한다
		int count = phoneService.personInsert(personVo);
		System.out.println(count);
		
		//리다이렉트
		return "redirect:/list";
	}
	
	
	//테스트
	@RequestMapping(value="/test", method= {RequestMethod.GET, RequestMethod.POST})
	public String test() {
		System.out.println("PhoneController>test()");
		//다오
		return "test";
	}

}
