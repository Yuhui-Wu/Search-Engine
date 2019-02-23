<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Result</title>
        <link href="https://fonts.googleapis.com/css?family=Spectral+SC" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">

        <link href="styles/read.css" rel="stylesheet">
    </head>
    <body>
        <!-- result of search -->
        <!-- for loop -->
        <%
            if (request.getAttribute("video_url") != null) {
                ArrayList<String> urls = (ArrayList<String>) request.getAttribute("video_url");
                if (urls.isEmpty()) {
        %>
        <p style="margin-left: 5%"> Sorry. Please try another query.</p>
        <%
        } else
            for (String url : urls) {
        %>
        <iframe width="100%" height="333" src="<%=url%>" frameborder="0" allowfullscreen></iframe>           
        <p> </p>
        <%}
            }%>
        
            <p style="height: 20px">  </p>
    </body>
</html>
