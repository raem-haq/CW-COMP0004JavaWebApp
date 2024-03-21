<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
    <style>
        /* Add your CSS styles here */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
            color: #333;
        }
        .header {
            background-color: #fafaaa;
            color: #333;
            padding: 10px;
            text-align: center;
        }
        .main {
            margin: 10px auto;
            width: 80%;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #fff;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #fff;
            color: #000;
        }
        tr:hover {
            background-color: #f2f2f2;
        }
        .footer {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h1>Search Result</h1>

    <%
        List<List<String>> patients = (List<List<String>>) request.getAttribute("result");
        if (patients.size() > 1) {%> <!-- always contains column names -->
    <p>Which field do you want to sort (ascending)?</p>
    <form method="POST" action="/sorttable.html">
        <select name="field">
            <option value="" disabled selected>Select field</option>
            <% for (String col:patients.getFirst()){%>
            <option value=<%=col%>><%= col %></option>
            <%}%>
        </select>
        <select name="order">
            <option value="" disabled selected>Select ordering</option>
            <option value="asc">Ascending</option>
            <option value="desc">Descending</option>
        </select>
        <input type="submit" value="Sort">
    </form>
    <table>
        <thead>
        <tr>
                <%for (String col : patients.get(0)) {%>
                <td><%=col%></td>
                <%}%>
        </tr>
        </thead>
        <tbody>
        <%for (int i = 1; i < patients.size(); i++) {%>
        <tr>
            <%for (String value : patients.get(i)) {%>
            <td><%=value%></td>
            <%}%>
        </tr>
        <%}%>
        </tbody>
    </table>
    <%} else {%>
    <p>No results found</p>
    <%}%>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
