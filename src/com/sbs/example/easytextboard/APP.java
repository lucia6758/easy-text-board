package com.sbs.example.easytextboard;

import java.util.Scanner;

public class APP {

	Article[] articles = new Article[10];

	int lastArticleId = 0;
	int articlesSize = 0;
	
	int articlesSize() {
		return articlesSize;
	}

	public Article getArticle(int id) {
		if (id < 1) {
			return null;
		}
		if (id > lastArticleId) {
			return null;
		}
		return articles[id - 1];
	}

	public void run() {

		Scanner scanner = new Scanner(System.in);

		int maxArticlesCount = articles.length;

		while (true) {
			System.out.printf("명령어) ");
			String command = scanner.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				if (articlesSize() >= maxArticlesCount) {
					System.out.println("더 이상 작성할 수 없습니다.");
					continue;
				}
				
				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("제목 : ");
				String title = scanner.nextLine();
				System.out.printf("내용 : ");
				String body = scanner.nextLine();

				System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

				Article article = new Article();

				article.id = id;
				article.title = title;
				article.body = body;
				
				articles[articlesSize] = article;
				
				articlesSize++;

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (articlesSize() == 0) {
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

				if (article == null) {
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
