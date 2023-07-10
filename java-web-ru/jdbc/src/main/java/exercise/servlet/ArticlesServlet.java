package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang3.ArrayUtils;
import exercise.TemplateEngineUtil;

public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        try {
            List<Article> articles = new ArrayList<>();
            Integer articlesPeriodPage = 10;
            int pageNormalized =
                    request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
            int offset  = pageNormalized * articlesPeriodPage - articlesPeriodPage;

            // Запрос для получения данных компании. Вместо знака ? буду подставлены определенные значения
            String query = "SELECT id, title, body FROM articles ORDER BY id LIMIT ? OFFSET ?;";
            // Используем PreparedStatement
            // Он позволяет подставить в запрос значения вместо знака ?
            PreparedStatement statement = connection.prepareStatement(query);
            // Указываем номер позиции в запросе (номер начинается с 1) и значение,
            // которое будет подставлено
            statement.setInt(1, 10);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getLong("id"));
                article.setTitle(resultSet.getString("title"));
                article.setBody(resultSet.getString("body"));
                articles.add(article);
            }
            resultSet.close();
            statement.close();
            request.setAttribute("page", pageNormalized);
            request.setAttribute("articles", articles);

        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String id = getId(request);
        Article article = new Article();

        try {
            article = findById(connection, id);

        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        if (article.getId() == 0) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        request.setAttribute("article", article);
        TemplateEngineUtil.render("articles/show.html", request, response);
        // END
    }

    private Article findById(Connection connection, String id)
         throws SQLException {

        String query = "SELECT id, title, body FROM articles WHERE id= ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, Long.valueOf(id));
        ResultSet resultSet = statement.executeQuery();
        Article article = new Article();
        article.setId(0L);
        article.setTitle("");
        article.setBody("");

        while (resultSet.next()) {
            article.setId(resultSet.getLong("id"));
            article.setTitle(resultSet.getString("title"));
            article.setBody(resultSet.getString("body"));
        }

        resultSet.close();
        statement.close();
        return article;
    }
}
