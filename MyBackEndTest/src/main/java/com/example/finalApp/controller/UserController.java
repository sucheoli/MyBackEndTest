package com.example.finalApp.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.finalApp.dto.UserDTO;
import com.example.finalApp.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
   private final UserService userService;

   // 요청 URL : GET /user/join => joinForm()메소드 실행
   @GetMapping("/join")
   public String joinForm(Model model) {
      if (!model.containsAttribute("user")) {
         // Model에 user가 없으면 새 UserDTO(빈 객체) 넣기
         model.addAttribute("user", new UserDTO());
      }
      return "user/join"; // templates/user/join.html
   }

   // 회원가입 처리
   // 요청 URL : POST /user/join => join() 메소드 실행
   @PostMapping("/join")
   public String join(@ModelAttribute("user") UserDTO user, @RequestParam("confirm-password") String confirmPassword,
         RedirectAttributes ra) {

      try {
         userService.join(user, confirmPassword);
         ra.addFlashAttribute("msg", "회원가입이 완료되었습니다. 로그인해주세요.");
         return "redirect:/user/login";
      } catch (Exception e) {
         ra.addFlashAttribute("error", "회원가입중 오류가 발생했습니다.");
         return "redirect:/user/join";
      }

   }

   // 요청 URL : GET /user/login => loginForm() 메소드 실행
   @GetMapping("/login")
   public String loginForm() {
      return "user/login";
   }

   // 요청 URL : POST /user/login => login() 메소드 실행
   @PostMapping("/login")
   public String login(@RequestParam("loginId") String loginId, @RequestParam("password") String password,
         HttpSession session, RedirectAttributes ra) {
      Optional<Long> userId = userService.login(loginId, password);
      if (userId.isPresent()) {
         session.setAttribute("userId", userId.get());
         session.setAttribute("loginId", userId);
         return "redirect:/board/list"; // 로그인 성공 시 게시판으로 이동
      } else {
         ra.addFlashAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
         return "redirect:/user/login"; // 로그인 실패 시 다시 로그인 페이지로 이동
      }

   }

   // 요청 URL : GET /user/logout => logout() 메소드 실행
   @GetMapping("/logout")
   public String logout(HttpSession session) {
      session.invalidate();
      return "redirect:/user/login";
   }

}
