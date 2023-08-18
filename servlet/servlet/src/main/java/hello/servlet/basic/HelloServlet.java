package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username"); // 메세지 받아오기
        System.out.println("username = " + username);

        // 메세지 클라이언트에 보내기
        response.setContentType("text/plain"); // http 헤더
        response.setCharacterEncoding("utf-8"); // http 헤더
        response.getWriter().write("hello " + username); // http 메세지 바디에 들어감
    }
}
