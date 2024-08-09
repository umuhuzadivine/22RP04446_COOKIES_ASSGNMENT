import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the HTML file
        request.getRequestDispatcher("/test.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userInput = request.getParameter("userInput");
        if (userInput != null && !userInput.isEmpty()) {
            // Create a cookie with the user's input
            Cookie userCookie = new Cookie("user", userInput);
            userCookie.setMaxAge(60 * 60 * 24 * 7); // Cookie valid for 7 days
            response.addCookie(userCookie);
        }
        // Redirect to the GET request to ensure the page is loaded with updated cookie
        response.sendRedirect(request.getContextPath() + "/user");
    }
}
