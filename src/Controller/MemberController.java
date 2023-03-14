package Controller;

import java.sql.Connection;
import java.util.Scanner;

import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class MemberController {
	
	private static Connection conn;
	private static Scanner sc;

	public MemberController(Connection conn, Scanner sc) {
		this.sc = sc;
		this.conn = conn;
	}

	public static void doJoin() {
		String loginId = null;
		String loginPw = null;
		String loginPwChk = null;
		String name = null;
		System.out.println("== 회원 가입 ==");

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요");
				continue;
			}
			
			SecSql sql = new SecSql();

			sql.append("SELECT COUNT(*) > 0");
			sql.append("FROM `member`");
			sql.append("WHERE loginId = ?", loginId);

			boolean isloginIdDup = DBUtil.selectRowBooleanValue(conn, sql);
			
			if(isloginIdDup){
					System.out.printf("%s은(는) 이미 존재하는 아이디 입니다\n",loginId);
					continue;
			}
			
			
			break;
		}
		
		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요");
				continue;
			}
			
			boolean loginPwCheck = true;
			
			while(true) {
				System.out.printf("로그인 비밀번호 확인 : ");
				loginPwChk = sc.nextLine().trim();
				
				if (loginPwChk.length() == 0) {
					System.out.println("비밀번호 확인을 입력해주세요");
					continue;
				}
				
				if(loginPw.equals(loginPwChk) == false) {
					System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
					loginPwCheck = false;
				}
				break;
			}
			if (loginPwCheck) {
				break;
			}
		}
		while(true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();						
			
			if (name.length() == 0) {
				System.out.println("이름을 입력해주세요");
				continue;
			}
			break;
		}
		
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `member`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", name);

		DBUtil.insert(conn, sql);

		System.out.printf("%s 회원님 환영합니다\n", name);
			
	}

	
}
