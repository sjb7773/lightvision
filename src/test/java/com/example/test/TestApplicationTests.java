package com.example.test;

import com.example.test.domain.Member;
import com.example.test.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
@Rollback(value = false)
class TestApplicationTests {

	@PersistenceContext
	EntityManager em;

	@Autowired
	MemberRepository memberRepository;

	@Test
	public void saveTest() {
		Member member = new Member();
		member.setName("123123");
		memberRepository.save(member);
	}

	@Test
	public void updateTest() {
		Member member1 = new Member();
		member1.setName("123123");
		memberRepository.save(member1);
		Member member2 = em.find(Member.class, 1);
		member2.setName("234234");
	}



}
