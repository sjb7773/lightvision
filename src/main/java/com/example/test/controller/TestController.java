package com.example.test.controller;

import com.example.test.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final MemberService memberService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "home";
    }

    @GetMapping("/add")
    public String getAddPage() {
        return "members/memberAdd";
    }

    @PostMapping("/add")
    public String postAddPage(@RequestParam("username") String username) {
        memberService.save(username);
        return "members/memberAdd";
    }

    @GetMapping("/delete")
    public String getDeletePage() {
        return "members/memberDelete";
    }

    @PostMapping("/delete")
    public String postDeletePage(@RequestParam("username") String username) {
        memberService.delete(username);
        return "members/memberDelete";
    }

    @GetMapping("/update")
    public String getUpdatePage() {
        return "members/memberUpdate";
    }

    @PostMapping("/update")
    public String postUpdatePage(@RequestParam("beforename") String beforeName, @RequestParam("aftername") String afterName) {
        memberService.update(beforeName, afterName);
        return "members/memberUpdate";
    }


}
