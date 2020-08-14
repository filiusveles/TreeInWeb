<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <title>Testing</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
    <meta charset="UTF-8">
</head>
<body>
<h1>Testing</h1>

<div class="container">
    <button type="submit" onclick="node_create()">Create</button>
    <button type="button" onclick="node_delete()">Delete</button>
    <button type="button" onclick="node_rename()">Rename</button>
    <div id="node_tree"></div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>
<script>
    $(function() {
        $('#node_tree').jstree({
            "plugins": ["wholerow","json_data", "dnd"],
            'core':{
                "check_callback" : true,
                'data':{
                    'url': function (node) {
                        return node.id === '#'? 'tree/node?id=0': "tree/node?id=" + node.id;
                    },
                    'data':function (node){
                        return {'id': node.id};
                    }
                }
            }
        }).on('move_node.jstree', function (e, data) {
            send_rename_node(data.node);
        });
    });

    function get_ref(){
        return  $('#node_tree').jstree(true);
    }

    function node_create() {
        var ref = get_ref();
        var select = ref.get_selected();
        if(!select.length) {
            select = ref.create_node(null, "");
            ref.select_node(select);
        }else {
            select = select[0];
            select = ref.create_node(select, "");
        }
        if(select){
            ref.edit(select, null, function (node){
                send_add_changes(node);
            });
        }
    }

    function send_add_changes(new_node){
        var parent = new_node.parent;
        var text = new_node.text;
        $.ajax({
            url: "tree/add",
            method: 'post',
            contentType: 'application/json',
            data:JSON.stringify({
                "parent": parent,
                "text": text
            }),
            success: function (data){
                get_ref().set_id(new_node, data);
            }
        })

    }

    function node_delete() {
        var ref = get_ref();
        var selected = ref.get_selected();
        if(!selected.length) {return false;}
        send_delete_node(selected);
        ref.delete_node(selected);
    }

    function send_delete_node(node_id){
        $.ajax({
            url: "tree/delete",
            method: "delete",
            contentType: 'application/json',
            data:JSON.stringify({
                "id": node_id[0]
            }),
            success: function (){
                console.log(true);
            }
        })
    }

    function node_rename(){
        var ref = get_ref();
        var selected = ref.get_selected(true);
        if(!selected.length) {return false;}
        ref.edit(selected[0], null, function (){
            send_rename_node(selected[0]);
        })
    }

    function send_rename_node(node){
        $.ajax({
            url:'tree/rename',
            method:'put',
            contentType:'application/json',
            data:JSON.stringify({
              "id": node.id,
              "parent": node.parent,
              "text": node.text,
              "children": node.children.length > 0
            }),
            success:function (){
                console.log(true);
            }
        })
    }

</script>

</body>
</html>
