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

// The servlet invoked to retrieve a record
@WebServlet("/getrow.html")
public class GetRowServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();
        try {
            int index = Integer.parseInt(request.getParameter("index")) - 1;
            List<String> rowI = new ArrayList<>(model.getRow(index));
            request.setAttribute("columnNames", model.getColumnNames());
            request.setAttribute("index", index);
            request.setAttribute("record", rowI);
        } catch (NumberFormatException e){
            e.printStackTrace(); // can't happen
        }

        // Invoke the JSP page.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/modifyRowSelected.jsp"); // second-to-last stage of modifying a row
        dispatch.forward(request, response);
    }
}