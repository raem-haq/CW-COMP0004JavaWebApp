package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// The servlet invoked to delete a row
@WebServlet("/rundeleterow.html")
public class DeleteRowServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();
        String message;
        try {
            int index = Integer.parseInt(request.getParameter("index")) - 1;//convert string to int
            model.deleteRow(index); // should always work
            message = "Deleted row successfully";
        } catch (NumberFormatException e){
            message = "Request index cannot be converted to Integer"; // should never happen
        }
        request.setAttribute("message", message);

        // Invoke the JSP page.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/edited.jsp");
        dispatch.forward(request, response);
    }
}