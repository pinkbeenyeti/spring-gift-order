package gift.controller;

import gift.service.KakaoLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/kakao")
public class KakaoLoginController {
    @Autowired
    private KakaoLoginService kakaoLoginService;

    private String accessToken;

    @GetMapping("/login")
    public String kakaoLogin() {
        return "redirect:" + kakaoLoginService.kakaoLogin();
    }

    @GetMapping
    public String kakaoLoginVerify(@RequestParam("code") String authorizationCode) {
        accessToken = kakaoLoginService.getAccessToken(authorizationCode);

        return "redirect:/kakao/login/success";
    }

    @GetMapping("login/success")
    public String kakaoLoginSuccess(Model model) {
        model.addAttribute("accessToken", accessToken);

        return "kakaoLogin";
    }
}
