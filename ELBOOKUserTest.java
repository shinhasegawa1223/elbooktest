package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Entity.UserList;
import com.example.demo.Service.UserService;

@SpringBootTest
public class ELBOOKUserTest {

	@Autowired
	private UserService userService;

	//@Disabledをつけることでテストスキップが行える

	@Test
	@Disabled
	//全件取得処理のテストコード
	void findAllTest() {
		//count の値を変えてください
		int count = 2;
		List<UserList> lists = userService.findUser();
		System.out.println(lists.size());
		assertEquals(count, lists.size());
	}
	
	@Test
	//@Disabled
	void targetUser() {
		List<UserList> lists = userService.targetUser(7);
		UserList targetuser = lists.get(0);
		System.out.println(targetuser.getUser_name());
		assertEquals("kamikita", targetuser.getUser_name());
		assertEquals("kamik@com", targetuser.getMail());
	}

	@Test
	@Disabled
	//ユーザー追加処理
	void createUser() {
		UserList userList = new UserList();
		userList.setUser_name("okita");
		userList.setMail("okita@com");
		userList.setPass("password");
		userService.createUser(userList);

		List<UserList> lists = userService.targetUser(15);
		UserList targetuser = lists.get(0);
		assertEquals("okita", targetuser.getUser_name());
		assertEquals("okita@com", targetuser.getMail());
	}

	@Test
	@Disabled
	void editUser(){
		UserList userList = new UserList();
		userList.setUser_id(6);
		userList.setUser_name("kamikita");
		userList.setMail("kamikita@com");
		userList.setPass("password");
		userService.editUser(userList);	
		
		assertEquals("kamikita", userList.getUser_name());
		assertEquals("kamikita@com", userList.getMail());
	}
	
	@Test
	@Disabled
	//ユーザーの削除処理のテストコード
	//deleteflg の上書きを行う為一部処理の変更が必要

	//Todo target user検索においてデリートフラグも取得できるようにする
	void deleteUser() {

		//引数を変えてください
		userService.deleteUser(14);

		List<UserList> lists = userService.targetUser(14);
		UserList userList = lists.get(0);
		System.out.println("user_id:" + userList.getUser_id());
		assertEquals(14, userList.getUser_id());
		System.out.println("-----------------");

	}

}
