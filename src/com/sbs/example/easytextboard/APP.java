package com.sbs.example.easytextboard;

import java.util.Scanner;

public class APP {

	Article article1 = new Article();
	Article article2 = new Article();

	public Article getArticle(int id) {
		if (id == 1) {
			return article1;
		} else if (id == 2) {
			return article2;
		}
		return null;
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);

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
				System.out.printf("제목 : %s\n", title);
				System.out.printf("내용 : %s\n", body);
				System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

				Article article = getArticle(id);

				article.id = id;
				article.title = title;
				article.body = body;

				lastArticleId = id;

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (lastArticleId == 0) {
					System.out.println("게시물이 없습니다");
					continue;
				}
				System.out.println("번호 / 제목");

				for (int i = 1; i <= lastArticleId; i++) {
					Article article = getArticle(i);

					System.out.printf("%d / %s\n", article.id, article.title);
				}

			} else if (command.startsWith("article detail ")) {
				System.out.println("== 게시물 상세 ==");
				int inputedId = Integer.parseInt(command.split(" ")[2]);

				Article article = getArticle(inputedId);

				if (lastArticleId == 0 || inputedId > lastArticleId) {
					System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputedId);
					continue;
				}

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);

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
