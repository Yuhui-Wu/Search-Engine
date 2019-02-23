<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up Page</title>
        <link rel="stylesheet" type="text/css" href="styles/sign.css">
    </head>
    <body>
        <form action="signup" method="POST" onsubmit="return check()">
            <h1 align="center">Sign up</h1>
            <table align="center">
                <tbody>
                    <tr>
                        <td>Your Username:</td>
                        <td><input type="text" name="username" required="required" placeholder="Hannah" /></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="text" name="email" required="required" placeholder="example@123.com" /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" required="required" placeholder="eg. 123456"/></td>
                    </tr>
                    <tr>
                        <td>Password Confirm:</td>
                        <td><input type="password" name="password_conf" required="required" placeholder="the same as password"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input class="button" value="Sign up" type="submit"/></td>
                <script>
                    function check(){
                        var p=document.getElementsByName("password")[0].value;
                        var pc=document.getElementsByName("password_conf")[0].value;
                        if (p===pc)
                            return true;
                        else{
                            document.getElementsByClassName("hint")[0].innerHTML="Please confirm your password.";
                            return false;
                        }                           
                    }
                </script>
                <%--give a hint about why sign up failed--%>
                <p class = "hint" align="center"><% String msg=(String)request.getAttribute("signup_fail");
                if(msg==null){msg="";}%><%=msg%></p>
                </tr>
                </tbody>
            </table>
            <p class="link" align="center">Already have an account? <a href="login.jsp">Go and log in</a></p>
        </form>
    </body>
</html>