package com.KoreaIT.example.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

import Controller.ArticleController;
import Controller.MemberController;

public class App {
	public void run() {
		Scanner sc = new Scanner(System.in);
		System.out.println("== 프로그램 시작 ==");

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/jdc_article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

			conn = DriverManager.getConnection(url, "root", "");

			
			ArticleController article = new ArticleController(conn ,sc);
			MemberController member = new MemberController(conn,sc);
			
			while (true) {
				System.out.printf("명령어) ");
				String cmd = sc.nextLine().trim();

				if (cmd.equals("member join")) {
					MemberController.doJoin();
				} else if (cmd.equals("article write")) {
					ArticleController.doWrtie();
				} else if (cmd.equals("article list")) {
					ArticleController.showList();
				} else if (cmd.startsWith("article detail ")) {
					ArticleController.showDetail(cmd);
				} else if (cmd.startsWith("article modify ")) {
					ArticleController.doModify(cmd);
				} else if (cmd.startsWith("article delete ")) {
					ArticleController.doDelete(cmd);
				}

				if (cmd.equals("exit")) {
					System.out.println("== 프로그램 종료 ==");
					break;
				}

			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		sc.close();
	}
}