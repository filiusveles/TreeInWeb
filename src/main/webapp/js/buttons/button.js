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
    ref.deselect_all();
}

function send_add_changes(new_node){
    var parent = new_node.parent;
    var text = new_node.text;
    $.ajax({
        url: "nodes/add",
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
        url: "nodes/delete",
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
        send_update_node(selected[0]);
    });
    ref.deselect_all();
}

function send_update_node(node){
    $.ajax({
        url:'nodes/update',
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