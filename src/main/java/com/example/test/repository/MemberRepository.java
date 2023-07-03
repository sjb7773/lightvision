package com.example.test.repository;

import com.example.test.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    /*
    * 1. findByName 삭제
    * */
}

