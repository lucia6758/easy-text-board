package com.sbs.example.easytextboard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int article1_id = 0;
		String article1_title = "";
		String article1_body = "";

		int article2_id = 0;
		String article2_title = "";
		String article2_body = "";

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
					article1_id = id;
					article1_title = title;
					article1_body = body;
				} else if (id == 2) {
					article2_id = id;
					article2_title = title;
					article2_body = body;
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
					System.out.printf("%d / %s\n", article1_id, article1_title);
				}
				if (lastArticleId >= 2) {
					System.out.printf("%d / %s\n", article2_id, article2_title);
				}
			} else if (command.startsWith("article detail ")) {
				System.out.println("== 게시물 상세 ==");
				int inputedId = Integer.parseInt(command.split(" ")[2]);

				if (inputedId == 1) {
					if (article1_id == 0) {
						System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputedId);
						continue;
					}
					System.out.printf("번호 : %d\n", article1_id);
					System.out.printf("제목 : %s\n", article1_title);
					System.out.printf("내용 : %s\n", article1_body);
				} else if (inputedId == 2) {
					if (article1_id == 0) {
						System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputedId);
						continue;
					}
					System.out.printf("번호 : %d\n", article2_id);
					System.out.printf("제목 : %s\n", article2_title);
					System.out.printf("내용 : %s\n", article2_body);
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
