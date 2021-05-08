package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
/*import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;*/

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemoryMemberRepositoryTest {

	MemoryMemberRepository repository = new MemoryMemberRepository(); // 인터페이스 미포함
//	MemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach
	public void afterEach() { // 테스트 데이터 클리어
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
		Member result = repository.findById(member.getId()).get();
	 	assertThat(member).isEqualTo(result);
	 	
	 	System.out.println("member ID : " + (member.getId()));
		
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();
		
		assertThat(result).isEqualTo(member1);
		
		
		System.out.println("member1 ID : " + (member1.getId()));
		System.out.println("member2 ID : " + (member2.getId()));
		
	}
	
	@Test
	public void finaAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member1.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		assertThat(result.size()).isEqualTo(2);
		
		System.out.println("member1-ALL ID : " + (member1.getId()));
		System.out.println("member2-ALL ID : " + (member2.getId()));
	}
	
	
}
