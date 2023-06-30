package com.example.test.controller;

import com.example.test.dto.MemberRequest;
import com.example.test.dto.MemberResponse;
import com.example.test.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class TestController {
    /*
    * 1. view 처리하지 않도록 수정
    * 2. HTTP method 종류 변경
    * 3. 저장, 업데이트시 파라미터 MemberRequest 타입으로 받음
    * 4. 조회시 반환값으로 MemberResponse 사용
    * 5. 응답 body 가 필요한 경우 반환시 ResponseEntity 사용
    * 6. 회원 조회 기준 변경 : username -> id, Account table 에서 id가 기본키라서
    * 7. 메소드명 변경 : 기존에는 page 에 대한 의미를 담고 있었음
    * 8. url 변경 : 행위가 url 에 포함되지 않도록, 리소스를 포함하도록 수정
    * 9. 저장, 업데이트시 location 헤더 필드에 리소스 위치 알려주도록 변경
    * 10. id를 파라미터로 받아서 location 필드를 포함한 헤더 반환하는 메소드인 createHttpHeaderWithLocation 추가 : 새로 저장된 회원 정보가 저장된 위치를 알려주는 메소드
    * 11. 저장시 id 정보를 알려주기 위해 MemberResponse 반환
    * 12. RequestParam -> PathVariable 로 변경 : 쿼리파라미터까지 포함하는게 자원위치라고 생각하지 않아서
    * */

    private final MemberService memberService;
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        return ResponseEntity.ok().body(memberService.getMember(id));
    }

    @PostMapping
    public ResponseEntity<Void> saveMember(@RequestBody MemberRequest memberRequest) {
        MemberResponse memberResponse = memberService.saveMember(memberRequest);
        return new ResponseEntity<>(this.createHttpHeaderWithLocation(memberResponse.getId()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }

    @PutMapping
    public ResponseEntity<Void> updateMember(@RequestBody MemberRequest memberRequest) {
        memberService.updateMember(memberRequest);
        return new ResponseEntity<>(this.createHttpHeaderWithLocation(memberRequest.getId()), HttpStatus.CREATED);
    }
    private HttpHeaders createHttpHeaderWithLocation(Long id) {
        HttpHeaders responseHeader = new HttpHeaders();
        String hostAddress = "localhost:8080";
        URI location = UriComponentsBuilder.newInstance()
                .path(hostAddress + "/member" + "/"+ id).build().toUri();
        responseHeader.setLocation(location);
        return responseHeader;
    }


}
