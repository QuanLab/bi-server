package vn.five9.bi;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userToken = request.getHeader("user-token");

        if(userToken == null) {
            RequestDispatcher view = request.getRequestDispatcher("login.html");
            view.forward(request, response);
        } else {
            RequestDispatcher view = request.getRequestDispatcher("index.html");
            view.forward(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet " + this.getServletName() + " has started");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() + " has stopped");
    }

}