<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <style>
        .form-field {
            padding-bottom: 10px;
        }
        .form-field * {
            display: inline-block;
            vertical-align: top;
        }
        .paramLabel {
            min-width: 60px;
        }
        .paramTextArea, .paramSelect {
            min-width: 300px;
        }
        .button {
            background: #3498db;
            background-image: -webkit-linear-gradient(top, #3498db, #2980b9);
            background-image: -moz-linear-gradient(top, #3498db, #2980b9);
            background-image: -ms-linear-gradient(top, #3498db, #2980b9);
            background-image: -o-linear-gradient(top, #3498db, #2980b9);
            background-image: linear-gradient(to bottom, #3498db, #2980b9);
            -webkit-border-radius: 28;
            -moz-border-radius: 28;
            border-radius: 28px;
            color: #ffffff;
            font-size: 20px;
            padding: 10px 20px 10px 20px;
            text-decoration: none;
        }

        .button:hover {
            background: #3cb0fd;
            background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
            background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
            background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
            background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
            background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
            text-decoration: none;
        }
    </style>
</head>
<body>
    <c:choose>
        <c:when test="${not empty rawForm}">
            ${rawForm}
        </c:when>
        <c:otherwise>
            <h3>Your session details are</h3>
            <form:form id="postBack" modelAttribute="requestForm" action="/silent/enroll/result" method="post">
                <div class="form-field">
                    <form:label path="PaReq" for="PaReq" cssClass="paramLabel">Pareq</form:label>
                    <form:textarea id='PaReq' path='PaReq' readonly="true" cssClass="paramTextArea"/>
                </div>
                <div class="form-field">
                    <form:label path="TermUrl" for="TermUrl" cssClass="paramLabel">TermUrl</form:label>
                    <form:textarea id='TermUrl' path='TermUrl' readonly="true" cssClass="paramTextArea"/>
                </div>
                <div class="form-field">
                    <form:label path="MD" for="MD" cssClass="paramLabel">MD</form:label>
                    <form:textarea id='MD' path='MD' readonly="true" cssClass="paramTextArea"/>
                </div>
                <h3>How would you like to proceed?</h3>
                <div class="form-field">
                    <form:label path="choice" for="choice" cssClass="paramLabel">Result</form:label>
                    <form:select path="choice" items="${choices}" cssClass="paramSelect" />
                </div>
                <input type="submit" value="Submit" class="button"/>
            </form:form>
        </c:otherwise>
    </c:choose>
</body>
</html>