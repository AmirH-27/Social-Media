<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form:form action="login" method="post" modelAttribute="user">
            Student ID: <form:input path="id"/>
            <form:errors path="id" /><br/><br/>
            Password: <form:input path="password" type="password" />
            <form:errors path="password" /><br/><br/>
            <input type="submit" value="Login">
        </form:form>
    </body>
</html>