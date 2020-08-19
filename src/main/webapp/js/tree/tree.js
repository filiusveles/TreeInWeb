$(function() {
    $('#node_tree').jstree({
        "plugins": ["wholerow","json_data", "dnd"],
        'core':{
            "check_callback" : true,
            'data':{
                'url': function (node) {
                    return node.id === '#'? '/nodes?id=0': "/nodes?id=" + node.id;
                },
                'data':function (node){
                    return {'id': node.id};
                }
            }
        }
    }).on('move_node.jstree', function (e, data) {
        if(data.old_parent !== '#') send_update_node(get_ref().get_node(data.old_parent));
        send_update_node(data.node);
    });
});

function get_ref(){
    return  $('#node_tree').jstree(true);
}