package co.edu.unbosque.taller_3;


import co.edu.unbosque.taller_3.DTO.User;
import co.edu.unbosque.taller_3.services.UserService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "login", value = "/login")
public class LogInServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello, ";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        List<User> users = new UserService().getUsers().get();

        User userFounded = users.stream()
                .filter(user -> username.equals(user.getUsername()) && username.equals(user.getUsername()))
                .findFirst()
                .orElse(null);

        if (userFounded != null) {
            request.setAttribute("role", userFounded.getRole());

            Cookie cookie = new Cookie("role", userFounded.getRole());
            cookie.setMaxAge(20);
            response.addCookie(cookie);

            RequestDispatcher dispatcher = request.getRequestDispatcher("./home.jsp");

            try {
                dispatcher.forward(request, response);
            } catch (ServletException e){
                e.printStackTrace();
            }

        } else {
            response.sendRedirect("./401.html");
        }
    }

    public void destroy() {
    }
}