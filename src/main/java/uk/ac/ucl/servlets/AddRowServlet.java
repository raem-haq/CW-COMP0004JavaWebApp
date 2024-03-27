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

// The servlet invoked to add a row to the table.
@WebServlet("/runaddrow.html")
public class AddRowServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();
        List<String> columnNames = model.getColumnNames();
        HashMap<String, String> record = new HashMap<>();

        for (String header : columnNames){
            record.put(header, request.getParameter(header));
        }
        String message = model.addRow(record);
        request.setAttribute("message", message);

        // Invoke the JSP page.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/edited.jsp");
        dispatch.forward(request, response);
    }
}