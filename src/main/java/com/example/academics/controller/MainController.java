package com.example.academics.controller;


import com.example.academics.model.Course;
import com.example.academics.model.Role;
import com.example.academics.model.Users;
import com.example.academics.model.repo.CourseRepository;
import com.example.academics.model.repo.UserRepository;
import com.example.academics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserService userService;

    @GetMapping(
            produces = MediaType.IMAGE_JPEG_VALUE,
            path = "course-img/{courseId}")
    @ResponseBody
    public byte[] getImage(@PathVariable("courseId") Long blogId, HttpServletResponse response) {
        return courseRepository.findById(blogId).orElseThrow().getPhoto();
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("courses")
    public String home(Model model) {
        model.addAttribute("courses", courseRepository.findAll());
        return "courses";
    }

    @GetMapping("users")
    public String user(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }


    @GetMapping("courses/{id}")
    public String blog(@PathVariable("id") Long id, Model model) {
        Course course = courseRepository.findById(id).orElseThrow();
        model.addAttribute(course);

        return "courses";
    }

    @GetMapping("addCourse")
    public String createPost(Model model) {
        Course newCourse = new Course().setCreatedDate(LocalDateTime.now());
        model.addAttribute("newCourse", newCourse);
        return "addCourse";
    }

    @GetMapping("editCourse/{id}")
    public String editPost(Model model, @PathVariable long id) {
        Course newCourse = courseRepository.findById(id).orElseThrow();
        model.addAttribute("newCourse", newCourse);
        return "editCourse";
    }

    @GetMapping("deleteCourse/{id}")
    public String deletePost(@PathVariable long id) {
        courseRepository.deleteById(id);
        return "redirect:/courses";
    }

    @PostMapping(value = "savecourse", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String savePost(
            @ModelAttribute Course course,
            @RequestPart("photofile") MultipartFile photo,
            Principal principal
    ) {
        courseRepository.save(course);
        try {
            course.setPhoto(photo.getBytes());
            courseRepository.save(course);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/courses";
    }

    @GetMapping("adduser")
    public String createUser(Model model) {
        Users newUsers = new Users();
        model.addAttribute("newUser", newUsers);
        return "adduser";
    }

    @PostMapping(value = "saveuser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveUser(
            @ModelAttribute Users users,
            Principal principal
    ) {
        Role role_user = new Role("ROLE_USER");
        users.setRoles(Set.of(role_user));
        userService.saveUser(users);
        return "redirect:/";
    }

    @GetMapping("admin")
    @ResponseBody
    public String adminPanel() {
        return "admin";
    }

    @GetMapping("about")
    public String about() {
        return "about";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("register")
    public String register(Model model) {
        Users newUsers = new Users();
        model.addAttribute("newUser", newUsers);
        return "register";
    }


    @GetMapping("contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("admissions")
    public String admissions() {
        return "admissions";
    }

}
