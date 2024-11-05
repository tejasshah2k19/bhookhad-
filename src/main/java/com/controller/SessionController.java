package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.UserBean;
import com.dao.UserDao;
import com.service.Mailservice;
import com.service.OtpService;

import jakarta.mail.MessagingException;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class SessionController {
	@Autowired
	UserDao userDao;

	@Autowired
	Mailservice mailService;

	@Autowired
	OtpService otpService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/newuser")
	public String signup() {

		return "Signup";
	}

	@PostMapping("/saveuser")
	public String saveUser(@Validated UserBean user, BindingResult result, Model model) throws MessagingException {
		if (result.hasErrors()) {
			model.addAttribute("result", result);
			return "Signup";
		} else {

			File file = new File("C:\\projects\\Bhookad\\Bhookad\\src\\main\\webapp\\profilepic",
					user.getProfilePic().getOriginalFilename());
			try {
				FileUtils.writeByteArrayToFile(file, user.getProfilePic().getBytes(), false);
				user.setProfilePicPath("profilepic/" + user.getProfilePic().getOriginalFilename());

			} catch (IOException e) {
				e.printStackTrace();
			}
			user.setRole("USER");
			userDao.addUser(user);// insert into

			try {
				mailService.sendWelcomeEmailTemplate(user.getEmail(), user.getFirstName());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "Login";

		}
	}

	@GetMapping("/login")
	public String loginopen() {
		return "Login";
	}

	@PostMapping("/logincheck")
	public String login(@Validated UserBean user, BindingResult result, Model model) {
		boolean flag = userDao.checkUser(user);
		if (flag) {
			return "home";
		} else {
			model.addAttribute("error", "Invalid credentials. Please try again.");
			return "Login";
		}
	}

	@PostMapping("/authenticate")
	public String authenticate(UserBean user, Model model) {// email password
		UserBean dbUser = userDao.getUserByEmail(user.getEmail());
		if (dbUser == null) {
			model.addAttribute("error", "Invalid Credentials : Email");
			return "Login";// email wrong
		} else {
			// email valid =>
//			dbUser -> password -> encrypted 
//			user -> password -> plainText 
			boolean ans = passwordEncoder.matches(user.getPassword(), dbUser.getPassword());

			if (ans == true) {
				// valid password
				// user? role ?
				// admin => admin home
				model.addAttribute("user", dbUser);

				if (dbUser.getRole().equals("ADMIN")) {
					return "home";// admin home
				} else if (dbUser.getRole().equals("USER")) {
					return "UserHome";
				} else {
					model.addAttribute("error", "Invalid Credentials : Role");
					return "Login";
				}
				// user => user home
			} else {
				// invalid -> password
				model.addAttribute("error", "Invalid Credentials : Password");
				return "Login";
			}

		}

	}

	@PostMapping("/sendOtp")
	public String sendOtp(@RequestParam("email") String email, Model model) throws MessagingException {
		UserBean user = userDao.findByEmail(email);

		if (user == null) {
			model.addAttribute("error", "Email not found! Please sign up.");
			return "ForgotPassword";
		}

		String otp = otpService.generateOtp();

		otpService.sendOtpEmail(email, otp);

		userDao.saveOtpForUser(email, otp);

		model.addAttribute("message", "OTP sent to your email.");
		model.addAttribute("email", email);
		return "UpdatePassword";
	}

	@PostMapping("/updatePassword")
	public String updatePassword(@Validated UserBean user, BindingResult result, Model model) {

		boolean isValidOtp = userDao.verifyOtp(user.getEmail(), user.getOtp());

		if (!isValidOtp) {
			model.addAttribute("error", "Invalid OTP. Please try again.");
			return "UpdatePassword";
		} else {

			userDao.updatePassword(user.getEmail(), user.getNewPassword());
			model.addAttribute("message", "Password updated successfully. You can now login.");
			return "Login";
		}
	}

	@GetMapping("/forgotPassword")
	public String showForgotPasswordPage() {
		return "ForgotPassword";
	}

	@PostMapping("/verifyOtp")
	public String verifyOtp(@RequestParam("email") String email, @RequestParam("otp") String otp, Model model) {
		String storedOtp = userDao.getOtpForUser(email);

		if (storedOtp != null && storedOtp.equals(otp)) {
			return "UpdatePassword";
		} else {
			model.addAttribute("error", "Invalid OTP. Please try again.");
			return "UpdatePassword";
		}
	}

}