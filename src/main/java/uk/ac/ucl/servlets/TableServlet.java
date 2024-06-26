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
import java.util.List;

// The servlet invoked to retrieve the full table/patient info
// was going to be used to add a View Table button as well, but ran out of time.
// used in deleting, modifying, sorting.
@WebServlet("/table.html")
public class TableServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();
        request.setAttribute("result", model.getTable());

        ServletContext context = getServletContext();
        // used by many pages, thus uses a nextpage attribute
        RequestDispatcher dispatch = context.getRequestDispatcher("/"+request.getParameter("nextpage"));
        dispatch.forward(request, response);
    }
}