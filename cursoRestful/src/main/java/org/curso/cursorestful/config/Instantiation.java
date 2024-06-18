package org.curso.cursorestful.config;

import org.curso.cursorestful.dto.AuthorDTO;
import org.curso.cursorestful.dto.CommentDTO;
import org.curso.cursorestful.entities.Post;
import org.curso.cursorestful.repository.PostRepository;
import org.curso.cursorestful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.curso.cursorestful.entities.User;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;


@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        List<User> usuarios = new ArrayList<>();

        usuarios.add(maria);
        usuarios.add(alex);
        usuarios.add(bob);

        for(User user : usuarios){
            userRepository.save(user);
        }

        Post post1 = new Post(null, sdf.parse("21/03/2024"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2024"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

        List<Post> posts = new ArrayList<>();

        posts.add(post1);
        posts.add(post2);

        for(Post post : posts) {
            postRepository.save(post);
        }



        CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2024"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2024"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2024"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
