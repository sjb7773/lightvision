package com.example.test.service;

import com.example.test.domain.Member;
import com.example.test.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(String name) {
        Member member = new Member();
        member.setName(name);
        memberRepository.save(member);
    }

    @Transactional
    public void update(String beforeName, String afterName) {
        Member member = memberRepository.findByName(beforeName);
        member.setName(afterName);
    }

    public void delete(String name) {
        Member member = memberRepository.findByName(name);
        memberRepository.delete(member);
    }
}
