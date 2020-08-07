<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <title>Tree</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
    <script src="${pageContext.request.contextPath}script/createTree.js"></script>
</head>
<body>
<h1>Tree</h1>
    <div id="node_tree">

    </div>
<%--<c:if test="${!empty listParent}">
    <c:forEach items="${listParent}" var="node">
        <form:form>
            <tr>
                <c:if test="${node.hasChild}">
                    <td>+${node.name}</td>
                        &lt;%&ndash;<ul>
                            <c:forEach items="${childList}" var="child">
                                <li>${child.name}</li>
                            </c:forEach>
                        </ul>&ndash;%&gt;
                </c:if>

                <c:if test="${!node.hasChild}">
                    <td>${node.name}</td>
                </c:if>
            </tr>
        </form:form>
    </c:forEach>
</c:if>--%>


</body>
</html>
