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
    <button type="button" onclick="node_create()">Create</button>
    <button type="button" onclick="node_delete()">Delete</button>
    <button type="button" onclick="node_update()">Rename</button>
    <div id="node_tree"></div>


</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script>
    $(function() {
        $('#node_tree').jstree({
            "plugins": ["wholerow","json_data"],
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

    function node_create() {
        var ref = $('#node_tree').jstree(true);
        var select = ref.get_selected();
        alert(select);
        if(!select.length) {return false;}
        select = select[0];

        select = ref.create_node(select, {"type":"file"});
        if(select){
            ref.edit(select);
        }
    }

    function node_delete() {

    }

    function node_update(){

    }

</script>

</body>
</html>
