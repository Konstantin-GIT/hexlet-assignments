package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        Path fullPath = Paths.get("src/main/resources/users.json").toAbsolutePath().normalize();
        String data = Files.readString(fullPath, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        //List<Map<String, String>> result = new  ObjectMapper().readValue(data, new TypeReference<>() { });
        mapper.readValue(data, List.class);
        return mapper.readValue(data, List.class);

        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        response.setContentType("text/html;charset=UTF-8");
        StringBuilder body = new StringBuilder();

        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application</title>
                        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                              rel=\"stylesheet\"
                              integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                              crossorigin=\"anonymous\">
                    </head>
                    <body>
                         <table class="table">
                                       <thead>
                                         <tr>
                                           <th scope="col">ID</th>
                                           <th scope="col">FullName</th>
                                         </tr>
                                       </thead>
                                       <tbody>
                    """);

        List<Map<String, String>> users = getUsers();

        for (Map<String, String> user: users) {
            String userId =  user.get("id");
            String fullName = user.get("firstName") + " " + user.get("lastName");
            body.append("""
                           <tr>
                               <th scope="row">
                                    """ + userId + """
                                </th>
                                <td>
                                    <a href="/users/"""
                                    + userId + """
                                    ">""" + fullName + """
                                    </a>
                                </td>
                            </tr> """);
        }
        body.append("""
                                    </tbody>
                                 </table>
                            </body>
                        </html>
                    """);
        String result = body.toString();
        PrintWriter out = response.getWriter();
        out.println(result);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();
        List<Map<String, String>>  usersSelected = users.stream()
                .filter(item -> item.get("id").equals(id))
                .collect(Collectors.toList());

        String body = "";
        if (usersSelected.size() != 0) {
            response.setContentType("text/html;charset=UTF-8");
            Map<String, String> user = usersSelected.get(0);
            body = """
                        <!DOCTYPE html>
                        <html lang=\"ru\">
                            <head>
                                <title>Example application</title>
                                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                                 rel="stylesheet"
                                integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
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
                                         </tr>
                                       </thead>
                                       <tbody>
                                         <tr>
                                           <th scope="row">
                                           """ + user.get("id") + """
                                           </th>
                                           <td>
                                           """ +  user.get("firstName") + """
                                           </td>
                                           <td>
                                           """ + user.get("lastName") + """
                                           </td>
                                           <td>
                                           """ + user.get("email") + """
                                           </td>
                                         </tr>
                                       </tbody>
                                     </table>
                                </body>
                        </html>
                        """;
        } else {
            response.sendError(404, "Not found");
        }

        PrintWriter out = response.getWriter();
        out.println(body);
        // END
    }
}
