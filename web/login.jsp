<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="styles/sign.css">
    </head>
    <body>
        <form action="login" method="POST">
            <h1 align="center">Log in</h1>
            <table align="center">
                <tbody>
                    <tr>
                        <td>Your Username:</td>
                        <td><input type="text" name="username" required="required"/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" required="required"></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input class="button" type="submit" value="Log in" /></td>
                <%--give a hint about why log in failed--%>
                <p class = "hint" align="center"><% String msg=(String)request.getAttribute("login_fail");
                if(msg==null){msg="";}%><%=msg%></p>
                </tr>
                </tbody>
            </table>
            <p class ="link" align="center">Not have an account yet? <a href="signup.jsp">Go and sign up</a></p>
        </form>
    </body>
</html>

