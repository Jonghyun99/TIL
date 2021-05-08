package repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;

public interface MemberRepository {
	Member save(Member member);
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String id);
	List<Member> findAll();

}
