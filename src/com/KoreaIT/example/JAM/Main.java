package com.KoreaIT.example.JAM;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ArrayList<Article> articles = new ArrayList<>();

		int lastArticleId = 0;

		while (true) {

			System.out.printf("명령어 ) ");
			String cmd = sc.nextLine().trim();
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			String cmdBits[] = cmd.split(cmd);

			if (cmd.equals("article write")) {

				System.out.println("게시물 작성");

				int id = lastArticleId + 1;
				lastArticleId = id;

				System.out.println("제목 : ");
				String title = sc.nextLine();

				System.out.println("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body);
				articles.add(article);
				lastArticleId++;

				System.out.printf("%d 게시물이 생성 되었습니다\n", id);

			} else if (cmd.equals("article list")) {
				System.out.println("게시물 목록");

				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다");
					continue;
				}
				System.out.println("번호    |   제목");
				for (Article article : articles) {
					System.out.printf("%d	|	%s\n", article.id, article.title);
				}
			} else if (cmd.equals("exit")) {
				System.out.println("종료");
				break;
			}

			else {
				System.out.println("존재하지 않는 명령어 입니다");
				continue;
			}

		}

	}
}
