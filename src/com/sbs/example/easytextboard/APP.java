package com.sbs.example.easytextboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	private List<Article> articles;

	private int lastArticleId;
	private int articlesSize;

	public App() {
		articles = new ArrayList<>();
		lastArticleId = 0;

		for (int i = 1; i <= 32; i++) {
			add("제목" + i, "내용" + i);
		}
	}

	private Article getArticle(int id) {
		int index = getIndexById(id);

		if (index == -1) {
			return null;
		}

		return articles.get(index);
	}

	private int getIndexById(int id) {
		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).id == id) {
				return i;
			}
		}
		return -1;
	}

	private void removeArticle(int id) {
		int index = getIndexById(id);
		if (index == -1) {
			return;
		}
		articles.remove(index);
	}

	private int add(String title, String body) {

		Article article = new Article();

		article.id = lastArticleId + 1;
		article.title = title;
		article.body = body;
		
		articles.add(article);

		lastArticleId = article.id;

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

			} else if (command.startsWith("article list ")) {
				int page = Integer.parseInt(command.split(" ")[2]);
				System.out.printf("== 게시물 리스트 %d==\n", page);

				if (page <= 1) {
					page = 1;
				}

				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				int articlesInAPage = 10;
				int startPos = articles.size() - 1;
				startPos -= (page - 1) * articlesInAPage;
				int endPos = startPos - (articlesInAPage - 1);

				if (startPos < 0) {
					System.out.printf("%d페이지는 존재하지 않습니다.\n", page);
					continue;
				}

				System.out.println("번호 / 제목");

				for (int i = startPos; i >= endPos; i--) {
					if (i < 0) {
						continue;
					}

					Article article = articles.get(i);

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

			} else if (command.startsWith("article search ")) {
				String[] commandBits = command.split(" ");
				String searchKeyword = commandBits[2];

				int page = 1;

				if (commandBits.length >= 4) {
					page = Integer.parseInt(commandBits[3]);
				}

				if (page <= 1) {
					page = 1;
				}

				System.out.println("== 게시물 검색 ==");

				int searchResultArticleLen = 0;
				for (Article article : articles) {
					if (article == null) {
						break;
					}

					if (article.title.contains(searchKeyword)) {
						searchResultArticleLen++;
					}
				}

				Article[] searchResultArticles = new Article[searchResultArticleLen];

				int searchResultArticlesIndex = 0;
				for (Article article : articles) {
					if (article == null) {
						break;
					}

					if (article.title.contains(searchKeyword)) {
						searchResultArticles[searchResultArticlesIndex] = article;
						searchResultArticlesIndex++;
					}
				}

				if (searchResultArticles.length == 0) {
					System.out.println("일치하는 게시물이 없습니다.");
					continue;
				}

				System.out.println("번호 / 제목");

				int articlesInAPage = 10;
				int startPos = searchResultArticles.length - 1;
				startPos -= (page - 1) * articlesInAPage;
				int endPos = startPos - (articlesInAPage - 1);

				if (startPos < 0) {
					System.out.printf("%d페이지는 존재하지 않습니다.\n", page);
					continue;
				}

				for (int i = startPos; i >= endPos; i--) {
					if (i < 0) {
						continue;
					}

					Article article = searchResultArticles[i];

					System.out.printf("%d / %s\n", article.id, article.title);
				}
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
