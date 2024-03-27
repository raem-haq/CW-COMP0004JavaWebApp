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
    <h1>Sorting</h1>

    <%
        List<String> record = (List<String>) request.getAttribute("record");
        int index = Integer.parseInt(request.getParameter("index"));
        List<String> columnNames = (List<String>) request.getAttribute("columnNames");%>
    <p>How do you want to modify this row?</p>
    <!-- third and second-to-last step in modifying -->
    <table>
        <thead>
        <tr>
            <%for (String col : columnNames) {%>
            <td><%=col%></td>
            <%}%>
        </tr>
        </thead>
        <tbody>
        <tr>
            <%for (String value : record) {%>
            <td><%=value%></td>
            <%}%>
        </tr>
        </tbody>
    </table>

    <form method="POST" action="/runmodifyrow.html">
        <select name="index">
            <option value=<%=index%> selected><%=index%></option>
        </select>
        <%for (int i = 0; i < columnNames.size(); i++){%>
        <!-- default values are the current contents of this record -->
        <input type="text" name="<%=columnNames.get(i)%>" value="<%=record.get(i)%>"  />
        <%}%>
        <input type="submit" value="Modify" />
    </form>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
