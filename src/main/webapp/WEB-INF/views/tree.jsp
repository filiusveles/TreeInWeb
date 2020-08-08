<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <title>Testing</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
</head>
<body>
<h1>Testing</h1>

<div class="container">
    <div id="node_tree"></div>


</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<%--<script src="../createTree.js"></script>--%>
<script>
    $(function() {
        $('#node_tree').jstree({
            "plugins": ["json_data"],
            'core':{
                'data':{
                    'url': function (node) {
                        return node.id === '#'? 'tree/node?id=0': "tree/node?id=" + node.id;
                    },
                    'data':function (node){
                        return {'id': node.id};
                    }
                }
            }
        });
    });
</script>

</body>
</html>
