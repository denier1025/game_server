package root.index_html;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Alexey on 06.06.2017.
 */

public class Index extends AbstractHandler {

    private static String INDEX = "/";

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");

        if(!target.equals(INDEX)) {
            return;
        }
        PrintWriter writer = response.getWriter();
        writer.println("<html>" +
                "<head>" +
                "<title></title>" +
                "</head>" +
                "<body>" +
                "<form action=\"/test/\" method=\"GET\">" +
                "Name:<input type=\"text\" name=\"username\"/>\n" +
                "<input type=\"submit\" name=\"submit\" value=\"SignUp\"/>" +
                "</form>" +
                "</body>" +
                "</html>");

        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
    }

}
