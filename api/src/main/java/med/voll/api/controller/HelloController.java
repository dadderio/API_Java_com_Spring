package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//SpringMVC - sobre a classe escreve a anotação @RestController e a RequestMapping, indicar a url que o Controller vai responder

@RestController
@RequestMapping("/hello")

public class HelloController {

    @GetMapping
    public String olaMundo(){
        return "Hello World Spring!";
    }
}
