<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Patient Data App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
        }

        .main {
            background-color: #ffffff;
            padding: 20px;
            margin: 50px auto;
            width: 300px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            text-align: center;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            box-sizing: border-box;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="main">
    <h1>Advanced Search</h1>
    <!-- comma separated search does not make sense here -->
    <form method="GET" action="/runadvancedsearch.html">
        <input type="text" name="keyword" placeholder="Enter search keyword here"/>
        <br>
        <%List<String> patients = (List<String>) request.getAttribute("result");%>
        <select name="category"> <!-- drop-down menu of all the fields-->
            <option value="" disabled selected>Select category</option>
            <% for (String col:patients){%>
                <option value=<%=col%>><%= col %></option>
            <%}%>
        </select>
        <br>
        <input type="submit" value="Search"/>
    </form>
</div>-
</body>
</html>



-