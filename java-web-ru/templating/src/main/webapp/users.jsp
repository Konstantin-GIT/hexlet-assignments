<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Company</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
       <table class="table">
               <thead>
                  <tr>
                   <th scope="col">ID</th>
                   <th scope="col">FirstName</th>
                   <th scope="col">LastName</th>
                   <th scope="col">Email</th>
                   <th scope="col"></th>
                 </tr>
               </thead>
               <tbody>
               <c:forEach var="user" items="${users}">
                 <tr>
                   <th scope="row">
                   ${user.get("id")}
                   </th>
                   <td>
                   ${user.get("firstName") }
                   </td>
                   <td>
                    ${user.get("lastName") }
                    </td>
                   <td>
                   ${user.get("email") }
                   </td>
                   <td>
                   <a href='/users/show?id=${user.get("id")}'> Показать </a>
                   </td>
                 </tr>
                 </c:forEach>
               </tbody>
             </table>
    </body>
</html>
<!-- END -->
