package kr.icia.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.icia.domain.MemberVO;
import lombok.Getter;

@Getter
public class CustomUser extends User {
	// 사용자 계정과 권한 정보 저장.

	private static final long serialVersionUID = 1L;
	// 직렬화, 역직렬화. (현재는 형식을 맞췄을뿐 별다른 의미 없음)

	private MemberVO member;
	// 디비에서 추출한 회원정보 초기화.

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}// 상속을 받으면서 의무적으로 구현한 생성자.
		// <? extends 클래스명> : 제너릭 타입의 상위 제한.
		// <? super 클래스명> : 제너릭 타입의 하위 제한.
		// <?> : 제너릭 타입 제한 없음.

	public CustomUser(MemberVO vo) {
		super(vo.getUserid(), vo.getUserpw()
				, vo.getAuthList().stream()
				.map(auth -> 
				new SimpleGrantedAuthority(
						auth.getAuth()))
				.collect(Collectors.toList()));
		this.member = vo;
		// 사용자 아이디, 패스워드, 권한 목록으로 초기화.
	}
}
