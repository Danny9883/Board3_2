package com.green.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.menus.controller.MenuController;
import com.green.user.dto.UserDto;
import com.green.user.mapper.UserMapper;

@Controller
@RequestMapping("/Users")
public class UserController {

	@Autowired
	private UserMapper userMapper;

    // /Users/WriteForm
    @RequestMapping("/WriteForm")
    public  ModelAndView  writeForm() {
    	
    	ModelAndView  mv  = new ModelAndView();
    	mv.setViewName("users/write");
    	mv.addObject("msg", "욱's");
    	
    	return  mv;
    }
    
    // /Users/Write
    @RequestMapping("/Write")
    public  ModelAndView  write( UserDto userDto ) {
    	System.out.println( "UserConntroller write() ->  " + userDto );
    	
    	// 넘어온 data 로 DB 에 저장
    	userMapper.insertUser( userDto );
    	
    	ModelAndView  mv  = new ModelAndView();
    	mv.setViewName("redirect:/Users/List");
    	return  mv;
    }
    
    

	// /Users/List
	@RequestMapping("/List")
	public  ModelAndView  list() {
		
		// DB 에서 사용자 목록을 조회
		List<UserDto> userList = userMapper.getUserList();
		
		ModelAndView  mv = new ModelAndView();
		mv.setViewName("users/list");
		mv.addObject("userList", userList);
		
		return  mv;
	}
	
	
	
	// /Users/Delete?userid=SKY
	@RequestMapping("/Delete")
	public  ModelAndView  delete( UserDto userDto ) {
		
		// 넘겨받은 자료를 출력
		System.out.println( "UserCont delete() -> " + userDto );
		
		// DB 의 자료를 삭제
		userMapper.deleteUser( userDto );
		
		// 목록으로 이동
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		return  mv;
	}
	
	
	// /Users/UpdateForm?userid=SKY
	@RequestMapping("/UpdateForm")
	public  ModelAndView  updateForm( UserDto userDto ) {
		// 넘어온 userDto 정보
		System.out.println("넘어온 정보 -> " + userDto);
		
		// 수정을 위해 DB 에서 조회한 정보
		UserDto  user = userMapper.getUser( userDto ); 
		System.out.println("조회된 정보 -> " + user);
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("users/update");
		mv.addObject("user", user);
		
		return  mv;
	}
	
	// /Users/Update
	// userid=SEA&passwd=12345&username=%EB%B0%94%EB%8B%A4&email=sea%40green.com
	@RequestMapping("/Update")
	public  ModelAndView  update( UserDto userDto ) {
		
		userMapper.updateUser(userDto);
		
		ModelAndView  mv  = new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		return  mv;
	}
	
	
	
	

	
}
