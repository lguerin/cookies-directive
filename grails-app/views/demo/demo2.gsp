<%--
  Created by IntelliJ IDEA.
  User: lguerin
  Date: 10/9/14
  Time: 1:36 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="demo" />
    <title>Demo Cookies Directive</title>
</head>
<body>

<cd:cookiesBar expires="7" displayDecline="true">
    <g:link controller="demo" action="demo1">link to demo1 page</g:link>
</cd:cookiesBar>

</body>
</html>