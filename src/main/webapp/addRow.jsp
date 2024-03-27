<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
    <style>
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
    <h1>Add a row</h1>
    <% List<String> columnNames = (List<String>) request.getAttribute("result");%>
    <p>Enter dates in the form YYYY-MM-DD</p> <!--I planned a full list of how to format different field
    but ran out of time -->
    <form method="POST" action="/runaddrow.html">
        <% for (String field : columnNames){%>
        <input type="text" name=<%=field%> placeholder=<%=field%> /> <!-- add data for each field
        may be left blank except for ID-->
        <%}%>
        <input type="submit" value="Add" />
    </form>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>