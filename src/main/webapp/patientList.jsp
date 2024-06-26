<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Patients:</h2>
  <ul>
    <%
      List<String> patients = (List<String>) request.getAttribute("patientIDs");
      for (String id : patients)
      {
        String href = "/runadvancedsearch.html?keyword="+id+"&category=ID";
    %>
    <!-- utilises the advanced search servlet to find the patient with that id -->
    <li><a href="<%=href%>"><%=id%></a>
    </li>
    <% } %>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
