package com.example.test.service;

import com.example.test.domain.Member;
import com.example.test.dto.MemberRequest;
import com.example.test.dto.MemberResponse;
import com.example.test.dto.UpdateMemberRequest;
import com.example.test.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {


    /*
     * 1. 메소드명 변경 : xxx -> xxxMember
     * 2. 회원 조회 메소드 추가
     * 3. 조회시 리턴값으로 MemberResponse 사용
     * 4. 저장, 업데이트시 MemberRequest 파라미터로 사용
     * 5. createMemberResponseFromMember 메소드 추가 : Member 파라미터로 받아서 MemberResponse로 생성해서 반환
     * 6. createMemberFromMemberRequest 메소드 추가 : MemberRequest 파라미터로 받아서 Member 생성해 반환
     * 7. 삭제시 사용하는 jpa 메소드 변경 : delete -> deleteById (deleteById = findById + delete)
     * */

    private final MemberRepository memberRepository;

    public MemberResponse getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다."));
        return this.createMemberResponseFromMember(member);
    }
    public MemberResponse saveMember(MemberRequest memberRequest) {
        Member member = memberRepository.save(this.createMemberFromMemberRequest(memberRequest));
        return this.createMemberResponseFromMember(member);
    }


    @Transactional
    public void updateMember(Long id, UpdateMemberRequest updateMemberRequest) {
        Member member = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다."));
        member.setId(id);
        member.setName(updateMemberRequest.getName());
    }

    @Transactional
    public void deleteMember(Long id) {
       memberRepository.deleteById(id);
    }

    private MemberResponse createMemberResponseFromMember(Member member) {
        return new MemberResponse(member.getId(), member.getName());
    }

    private Member createMemberFromMemberRequest(MemberRequest memberRequest) {
        Member member = new Member();
        member.setName(memberRequest.getName());
        return member;
    }
}
