package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; // 이건 hello.html 의미하는 것임
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        // model에 속성 추가 "name"은 html에서 쓰이는 값 이름, 뒤 name은 request로 받은 값 value(html에 넘겨줌)
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http body에 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // API 방식, 문자 그대로 내려준다
    }

    @GetMapping("hello-api")
    @ResponseBody // JSON으로 반환하는 게 기본
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}

