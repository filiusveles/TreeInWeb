alert('createTree.js');
$(function () {
    $('#node_tree').jstree({
        'core':{
            'data':{
                'url': function (node) {
                    return node.id === '#'? 'node?id=0': "node?id=" + node.id;
                },
                'data':function (node){
                    return {'id': node.id};
                }
            }
        }
    })
});