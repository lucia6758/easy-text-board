package com.sbs.example.easytextboard;

import java.util.Scanner;

public class APP {

	public void run() {
		Scanner scanner = new Scanner(System.in);

		Article article1 = new Article();

		Article article2 = new Article();

		int lastArticleId = 0;

		while (true) {
			System.out.printf("명령어) ");
			String command = scanner.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				System.out.printf("제목 : ");
				String title = scanner.nextLine();
				System.out.printf("내용 : ");
				String body = scanner.nextLine();
				int id = lastArticleId + 1;

				System.out.println("== 생성된 게시물 정보 ==");
				System.out.printf("번호 : %d\n", id);
				System.out.printf("제목 : %s\n", title);
				System.out.printf("내용 : %s\n", body);

				if (id == 1) {
					article1.id = id;
					article1.title = title;
					article1.body = body;
				} else if (id == 2) {
					article2.id = id;
					article2.title = title;
					article2.body = body;
				}

				lastArticleId = id;

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (lastArticleId == 0) {
					System.out.println("게시물이 없습니다");
					continue;
				}
				System.out.println("번호 / 제목");

				if (lastArticleId >= 1) {
					System.out.printf("%d / %s\n", article1.id, article1.title);
				}
				if (lastArticleId >= 2) {
					System.out.printf("%d / %s\n", article2.id, article2.title);
				}
			} else if (command.startsWith("article detail ")) {
				System.out.println("== 게시물 상세 ==");
				int inputedId = Integer.parseInt(command.split(" ")[2]);

				if (inputedId == 1) {
					if (article1.id == 0) {
						System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputedId);
						continue;
					}
					System.out.printf("번호 : %d\n", article1.id);
					System.out.printf("제목 : %s\n", article1.title);
					System.out.printf("내용 : %s\n", article1.body);
				} else if (inputedId == 2) {
					if (article1.id == 0) {
						System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputedId);
						continue;
					}
					System.out.printf("번호 : %d\n", article2.id);
					System.out.printf("제목 : %s\n", article2.title);
					System.out.printf("내용 : %s\n", article2.body);
				} else {
					System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputedId);
					continue;
				}
			} else if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else {
				System.out.println("== 존재하지 않는 명령어 ==");
			}
		}

		scanner.close();

	}

}
