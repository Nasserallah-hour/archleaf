package tn.atch.acrh.AuthenticationService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.atch.acrh.ClientService.model.Client;
import tn.atch.acrh.ClientService.service.ClientService;

@Controller
public class AuthenticationController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ClientService clientService;
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/signup")
    public String signupPost(@RequestParam String name, @RequestParam String lastname,
                             @RequestParam String email, @RequestParam String password) {

        clientService.createClient(Client.builder().email(email).name(name).lastname(lastname).password(passwordEncoder.encode(password)).build());
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginPost() {
        // No additional logic is needed here; Spring Security handles the authentication
        return "redirect:/products"; // Redirect to the products page after successful login
    }
}