package com.sbs.example.easytextboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.easytextboard.dto.Member;

public class MemberController extends Controller {

	private List<Member> members;
	private int lastMemberId;

	public MemberController() {
		members = new ArrayList<>();
		lastMemberId = 0;
	}

	private int join(String userId, String userPw, String userName) {
		Member member = new Member();

		member.id = lastMemberId + 1;
		member.userId = userId;
		member.userPw = userPw;
		member.userName = userName;

		members.add(member);

		lastMemberId = member.id;

		return member.id;
	}

	public void run(Scanner scanner, String command) {
		System.out.println("== 회원가입 ==");

		System.out.printf("아이디 : ");
		String userId = scanner.nextLine();
		System.out.printf("비밀번호 : ");
		String userPw = scanner.nextLine();
		System.out.printf("이름 : ");
		String userName = scanner.nextLine();

		int id = join(userId, userPw, userName);

		System.out.printf("%d번 회원으로 가입되었습니다.\n", id);

	}

}
