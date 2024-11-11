package com.sibkm.clientapp.controller;

import com.sibkm.clientapp.entity.request.LoginRequest;
import com.sibkm.clientapp.entity.response.LoginResponse;
import com.sibkm.clientapp.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Menampilkan halaman login, bukan redirect
    }

    @PostMapping("/login")
    public String loginUser(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
        // Mengirim permintaan login ke backend melalui AuthService
        LoginResponse loginResponse = authService.login(loginRequest);

        if (loginResponse != null) {
            // Jika login berhasil, buat sesi dan set atribut sesi pengguna
            HttpSession session = request.getSession();
            session.setAttribute("userLoggedIn", true);
            session.setAttribute("username", loginResponse.getUsername()); // Mendapatkan username dari LoginResponse
            int oneDayInSeconds = 24 * 60 * 60;
            session.setMaxInactiveInterval(oneDayInSeconds); // Mengatur sesi aktif selama satu hari

            return "redirect:/home";
        } else {
            // Jika login gagal, redirect ke halaman login gagal
            return "redirect:/login";
        }
    }
}
