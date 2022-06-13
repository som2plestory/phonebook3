package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {
	//필드
	//생성자
	//메소드-gs
	//메소드-일반
	
	//전화번호 수정폼
	@RequestMapping(value="/modifyForm/{id}", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(/* Model model, */@PathVariable("id") int id){
		System.out.println("PhoneController>modifyForm");
		
		/*
		 * PhoneDao phoneDao = new PhoneDao(); PersonVo personVo =
		 * phoneDao.getPerson(id);
		 */
		//파라미터 꺼내기
				//System.out.println(id);
		
		//ds 데이터보내기 -->request attribute에 넣는다
		//model.addAttribute("personVo", personVo);
				
		return "/WEB-INF/views/modifyForm.jsp";
	}
	
	//전화번호 수정
	@RequestMapping(value="/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@RequestParam("id")int id, @RequestParam("name")String name,
						@RequestParam("hp")String hp,@RequestParam("company")String company) {
		System.out.println("PhoneController>modify()");
		
		//System.out.println(id);
		
		PhoneDao phoneDao = new PhoneDao();
		PersonVo personVo = new PersonVo(id, name, hp, company);
		System.out.println(personVo);
		phoneDao.personUpdate(personVo);
		return "redirect:/list";
	}
		
	//전화번호 삭제
	@RequestMapping(value="/delete/{id}", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable("id") int id) {
		System.out.println("PhoneController>delete()");
		
		
		//파라미터 꺼내기
		System.out.println(id);
		
		//Dao로 처리하기(삭제)
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personDelete(id);
		System.out.println(count);
		
		return "redirect:/list";
	}
	
	@RequestMapping(value="/delete2", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete2(@RequestParam("no")int no) {
		System.out.println("Phonedontrolle>delete()");
		
		
		//파라미터 꺼내기
		System.out.println(no);
		
		//Dao로 처리하기
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personDelete(no);
		
		
		return "redirect:/list";
	}
			
	
	//전화번호 리스트
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhoneController>list()");
		
		//Dao를 통해서 personList(주소)를 가져온다
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getPersonList();
		
		//ds 데이터보내기 -->request attribute에 넣는다
		model.addAttribute("personList", personList);
		
		
		return "/WEB-INF/views/list.jsp";
	}
	
	//전화번호 등록폼
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("PhoneController>writeForm");
		return "/WEB-INF/views/writeForm.jsp";
	}
	
	//전화번호 등록
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@RequestParam("name") String name,
						@RequestParam("hp") String hp,
						@RequestParam("company") String company) {
		System.out.println("PhoneController>write()");
		
		/*
		//파라미터 꺼내기
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);
		*/
		
		//vo로 묶기
		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);
	 
		//dao로 저장하기
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personInsert(personVo);
		System.out.println(count);
		
		return "/WEB-INF/views/list.jsp";
	}
	
	


}


