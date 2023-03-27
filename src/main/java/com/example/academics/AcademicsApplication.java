package com.example.academics;

import com.example.academics.model.Course;
import com.example.academics.model.Role;
import com.example.academics.model.User;
import com.example.academics.model.repo.CourseRepository;
import com.example.academics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Set;

@SpringBootApplication
public class AcademicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademicsApplication.class, args);
    }

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserService userService;

    @Bean
    public CommandLineRunner bootstrap() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                File userPhotoMan = new File("./src/main/resources/static/img/comment-1.jpg");

                Role role_admin = new Role("ROLE_ADMIN");
                Role role_user = new Role("ROLE_USER");

                User admin = saveUser("Kadyrmamat", "Momunov", "admin", role_admin);

                User user = saveUser("Perizat", "Kurmanbaeva", "222", role_user);


                File img01 = new File("./src/main/resources/static/images/course_1.jpg");
                File img02 = new File("./src/main/resources/static/images/course_2.jpg");
                File img03 = new File("./src/main/resources/static/images/course_3.jpg");
                File img04 = new File("./src/main/resources/static/images/course_4.jpg");

                String description = "You are allowed to convert this template as any kind of CMS theme or template for your custom website builder. You can also use this for your clients. Thank you for choosing us.";
                String content = """
                        This is a description of the video post. You can also have an image instead of the video. You can free download Xtra Blog Template from TemplateMo website. Phasellus maximus quis est sit amet maximus. Vestibulum vel rutrum lorem, ac sodales augue. Aliquam erat volutpat. Duis lectus orci, blandit in arcu est, elementum tincidunt lectus. Praesent vel justo tempor, varius lacus a, pharetra lacus.
                        Duis pretium efficitur nunc. Mauris vehicula nibh nisi. Curabitur gravida neque dignissim, aliquet nulla sed, condimentum nulla. Pellentesque id venenatis quam, id cursus velit. Fusce semper tortor ac metus iaculis varius. Praesent aliquam ex vel lectus ornare tristique. Nunc et eros quis enim feugiat tincidunt et vitae dui.""";

                String title1 = "How To Create Mobile Apps Using Ionic";
                String title2 = "How To Create Mobile Apps Using Ionic";
                String title3 = "How To Create Mobile Apps Using Ionic";
                String title4 = "How To Create Mobile Apps Using Ionic";

                String category = "cddcdcdcdcd";
                String price = "99.9";

                saveCourse(title1,description,price,img01);
                saveCourse(title2,description,price,img02);
                saveCourse(title3,description,price,img03);
                saveCourse(title4,description,price,img04);
            }

            private User saveUser(String firstname, String lastname, String username_password, Role role_user) throws IOException {
                    User user = new User().setFirstName(firstname)
                            .setLastName(lastname)
                            .setUsername(username_password)
                            .setPassword(username_password)
                            .setRoles(Set.of(role_user));

                    userService.saveUser(user);
                    return user;

            }


            private void saveCourse(String title,String description, String price,File img01) throws IOException {
                Course blog1;
                try (FileInputStream fileInputStream = new FileInputStream(img01)) {

                    blog1 = new Course()
                            .setTitle(title)
                            .setDescription(description)
                            .setPrice(price)
                            .setCreatedDate(LocalDateTime.now())
                            .setPhoto(fileInputStream.readAllBytes());
                }
                courseRepository.save(blog1);
            }
        };
    }

    @Bean("base64encoder")
    public Base64EncoderToString base64() {
        return bytes -> Base64.getEncoder().encodeToString(bytes);
    }

}
interface Base64EncoderToString{
    String encode(byte[] bytes);
}