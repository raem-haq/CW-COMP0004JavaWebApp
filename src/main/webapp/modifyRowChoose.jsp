<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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
    <h1>Modify row</h1>

    <%
        List<List<String>> patients = (List<List<String>>) request.getAttribute("result");%> <!-- always contains column names -->
    <p>Which row do you want to modify?</p>
    <!-- first stage in modifying -->

    <table>
        <thead>
        <tr>
            <td>Index</td>
            <%for (String col : patients.get(0)) {%>
            <td><%=col%></td>
            <%}%>
        </tr>
        </thead>
        <tbody>
        <%for (int i = 1; i < patients.size(); i++) {
        String href = "/getrow.html?index="+String.valueOf(i);%> <!-- each index redirects to the getRow servlet -->
        <tr>
            <td><a href=<%=href%>><%=i%></a></td>
            <%for (String value : patients.get(i)) {%>
            <td><%=value%></td>
            <%}%>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
