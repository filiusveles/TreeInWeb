alert('test');
$(function () {
    $('#node_tree').jstree({
        'core':{
            'data':{
                'url': function (node) {
                    return 'tree?id=' + node.id;
                },
                'data':function (node){
                    return {'id': node.id};
                }
            }
        }
    });
})