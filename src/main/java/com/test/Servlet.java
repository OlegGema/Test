//Oleg Gema
package com.test;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@SuppressWarnings("unchecked")
@WebServlet("/textfileAPI")
public class Servlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            String q="";
            int limit=10000;
            int length=0;
            boolean metaData=false;

            JSONObject jsonObject=new JSONObject();

            String queryText = request.getParameter("q");
            if (!queryText.isEmpty() && queryText.trim().length() > 0)
                q = queryText;

            if (!request.getParameter("limit").isEmpty()) {
                int queryLimit = Integer.parseInt(request.getParameter("limit"));
                if (queryLimit != 0)
                    limit = queryLimit;
            }
            if (!request.getParameter("length").isEmpty()) {
                int queryLength = Integer.parseInt(request.getParameter("length"));
                if (queryLength != 0)
                    length = queryLength;
            }

            if (request.getParameter("includeMetaData").equals("true"))
                metaData = true;

            jsonObject.put("text", Query.query(q.toLowerCase(), limit, length));

            if (metaData) {
                jsonObject.put("metaData", Query.getMetaData());
            }

            response.setContentType("application/json");
            response.getWriter().write(Parser.parse(jsonObject.toJSONString()));
        }
        catch (NullPointerException e) {
            response.sendRedirect("/");
        }
    }
}
