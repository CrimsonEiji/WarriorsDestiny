package com.example.WarriorsTest.controller;


import com.example.WarriorsTest.models.DTO.UserRegisterDTO;
import com.example.WarriorsTest.models.entity.UserEntity;
import com.example.WarriorsTest.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthorizationController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthorizationController(ModelMapper modelMapper, UserService userService, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
    @PostMapping("/login-error")
    public String getLoginError(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                                RedirectAttributes redirectAttributes) {
        System.out.println(username);
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY,username);
        redirectAttributes.addFlashAttribute("loginError",true);

        return "redirect:/auth/login";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute UserRegisterDTO userRegisterDTO,
                               BindingResult bindingResult,
                               RedirectAttributes ra) {

        if (bindingResult.hasErrors()) {
            ra.addFlashAttribute("userRegisterDTO", userRegisterDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);

            return "redirect:register";
        }
       var newUser = modelMapper.map(userRegisterDTO, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userService.saveAndFlush(newUser);

        return "login";
    }
    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO userRegisterDTO() {
        return new UserRegisterDTO();
    }
}
