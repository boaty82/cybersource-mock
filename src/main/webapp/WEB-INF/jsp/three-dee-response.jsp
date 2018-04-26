<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
    <form:form id="postBack" modelAttribute="responseForm" action="${redirectTo}" method="post">
        <form:input type='hidden' id='PaRes' path='PaRes'/>
        <form:input type='hidden' id='MD' path='MD'/>
    </form:form>
    <script type="text/javascript">
        window.onload = function () {
            document.forms['postBack'].submit();
        }
    </script>
</body>
</html>