window.onload = function() {

    var path_list = location.pathname.split('resources');
    var path_static = location.pathname.split('static');
    location.web={};
    location.web.projectname = '/ershuaizhang.github.io/';
    if (2==path_list.length){
        location.web.projectpath = path_list[0]+'resources';
    }
    if (2==path_static.length){
        location.web.staticpath = path_static[0]+'static';
    }
    // 顶部导航菜单
    $("#header").load(location.web.staticpath +'/blog/menupage/upmenu.html');
    $("#container").load(location.web.staticpath +'/blog/contentpage/container.html');
    // 加载页脚
    $("#foot").load(location.web.staticpath +"/public/html/footer.html");
}
function backToHomePage() {
    $("#content").load(location.web.staticpath +"/public/html/blank.html");
}
function backToFirstPage(){
    location.href = location.web.projectname+'index.html';
}

