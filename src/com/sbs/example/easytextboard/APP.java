package com.sbs.example.easytextboard;

import java.util.Scanner;

import com.sbs.example.easytextboard.controller.ArticleController;
import com.sbs.example.easytextboard.controller.MemberController;

public class App {

	public void run() {

		Scanner scanner = new Scanner(System.in);

		MemberController memberController = new MemberController();
		ArticleController articleController = new ArticleController();

		while (true) {
			System.out.printf("명령어) ");
			String command = scanner.nextLine();

			if (command.startsWith("article ")) {
				articleController.run(scanner, command);
			} else if (command.equals("member join")) {
				memberController.run(scanner, command);
			}

			else if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else {
				System.out.println("== 존재하지 않는 명령어 ==");
			}
		}

		scanner.close();

	}

}
