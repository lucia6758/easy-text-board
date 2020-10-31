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

	private boolean joinableUserId(String userId) {
		for (Member member : members) {
			if (member.userId.equals(userId)) {
				return false;
			}
		}
		return true;
	}

	public void run(Scanner scanner, String command) {

		if (command.equals("member join")) {
			System.out.println("== 회원가입 ==");

			int tryCount = 0;
			int maxTryCount = 3;
			boolean userIdIsValid = false;
			String userId = "";
			String userPw = "";
			String userName = "";

			while (true) {
				if (tryCount >= maxTryCount) {
					System.out.println("다음에 다시 시도해주십시오.");
					break;
				}

				System.out.printf("아이디 : ");
				userId = scanner.nextLine().trim();

				if (userId.length() == 0) {
					tryCount++;
					continue;
				} else if (joinableUserId(userId) == false) {
					tryCount++;
					System.out.printf("%s는 이미 사용중인 아이디입니다.\n", userId);
					continue;
				}

				userIdIsValid = true;
				break;
			}

			if (userIdIsValid == false) {
				return;
			}

			while (true) {
				System.out.printf("비밀번호 : ");
				userPw = scanner.nextLine().trim();

				if (userPw.length() == 0) {
					System.out.println("비밀번호를 입력해주십시오");
					continue;
				}
				break;
			}

			while (true) {
				System.out.printf("이름 : ");
				userName = scanner.nextLine().trim();

				if (userName.length() == 0) {
					System.out.println("이름을 입력해주십시오");
					continue;
				}
				break;
			}

			int id = join(userId, userPw, userName);

			System.out.printf("%d번 회원으로 가입되었습니다.\n", id);

		}
	}
}
