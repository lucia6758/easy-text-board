package com.sbs.example.easytextboard;

import java.util.Scanner;

public class App {

	private Article[] articles = new Article[3];

	private int lastArticleId = 0;
	private int articlesSize = 0;

	private int articlesSize() {
		return articlesSize;
	}

	private Article getArticle(int id) {
		int index = getIndexById(id);

		if (index == -1) {
			return null;
		}

		return articles[index];
	}

	private int getIndexById(int id) {
		for (int i = 0; i < articlesSize(); i++) {
			if (articles[i].id == id) {
				return i;
			}
		}
		return -1;
	}

	private void removeArticle(int id) {
		int index = getIndexById(id);
		if (index != -1) {
			for (int i = index + 1; i < articlesSize(); i++) {
				articles[i - 1] = articles[i];
			}
		}
		articlesSize--;
	}

	private int add(String title, String body) {

		if (articlesSize == articles.length) {
			Article[] newArticles = new Article[articles.length * 2];

			for (int i = 0; i < articles.length; i++) {
				newArticles[i] = articles[i];
			}
			articles = newArticles;
		}

		Article article = new Article();

		article.id = lastArticleId + 1;
		article.title = title;
		article.body = body;

		lastArticleId = article.id;

		articles[articlesSize] = article;

		articlesSize++;

		return article.id;
	}

	private void modify(int inputedId, String title, String body) {
		Article article = getArticle(inputedId);
		article.title = title;
		article.body = body;
	}

	public void run() {

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어) ");
			String command = scanner.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				System.out.printf("제목 : ");
				String title = scanner.nextLine();
				System.out.printf("내용 : ");
				String body = scanner.nextLine();

				int id = add(title, body);

				System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (articlesSize() == 0) {
					System.out.println("게시물이 없습니다");
					continue;
				}
				System.out.println("번호 / 제목");

				for (int i = articlesSize() - 1; i >= 0; i--) {
					Article article = articles[i];

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

			} else if (command.startsWith("article delete ")) {
				System.out.println("== 게시물 삭제 ==");
				int inputedId = Integer.parseInt(command.split(" ")[2]);

				Article article = getArticle(inputedId);

				if (article == null) {
					System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputedId);
					continue;
				}

				removeArticle(inputedId);

				System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputedId);

			} else if (command.startsWith("article modify ")) {
				System.out.println("== 게시물 수정 ==");
				int inputedId = Integer.parseInt(command.split(" ")[2]);

				Article article = getArticle(inputedId);

				if (article == null) {
					System.out.printf("%d번 게시물이 존재하지 않습니다.\n", inputedId);
					continue;
				}
				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : ");
				String title = scanner.nextLine();
				System.out.printf("내용 : ");
				String body = scanner.nextLine();

				modify(inputedId, title, body);

				System.out.printf("%d번 게시물이 수정되었습니다.\n", inputedId);

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
