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
    	System.out.println( "UserConntroller write() -> userDto : " + userDto );
    	
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
	
	
	
	//--------------------------- 
/*
	
	// /Users/Delete?user_id=${ user.userid }
	@RequestMapping("/Delete")
	public  String  delete( UserDto userDto ) {
		System.out.println("삭제할 아이디 : " + userDto);
		userMapper.deleteUser(userDto);
		
		return "redirect:/Users/List";
	}
	
	
	// /Users/UpdateForm?userid=${ user.userid }
	@RequestMapping("/UpdateForm")
	public  String  updateForm(UserDto userDto, Model model) {
		System.out.println("넘어온 대상 : " + userDto);
		UserDto user = userMapper.getUser(userDto);
		System.out.println("바꿀 대상의 정보 : " + user);
		model.addAttribute("user", user);
		return "users/update";
	}
	
	// /Users/Update
	@RequestMapping("/Update")
	public  String  update(UserDto userDto) {
		userMapper.updateUser(userDto);
		
		return "redirect:/Users/List";
	}
	*/
	
}
