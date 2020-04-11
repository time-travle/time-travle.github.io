function showPersonPage(choose) {
    if (!choose) {
        return;
    }
    debugger;
    if ("skillstack" == choose){
        $("#left_side").load(location.web.staticpath +'/blog/menupage/leftmenu.html');
    }else{
        $("#left_side").load(location.web.staticpath +"/public/html/blank.html");
    }
    var target = '/personinfos/' + choose + '/' + choose + '.html';
    $("#content").load(location.web.staticpath + target);
    $("#header").load(location.web.staticpath +'/blog/menupage/upmenu.html');
}
function showPicturePage(choose) {
    if (!choose) {
        return;
    }
    var target = '/picturepage/' + choose + '/' + choose + '.html';
    $("#content").load(location.web.staticpath + target);
    $("#left_side").load(location.web.staticpath +"/public/html/blank.html");
    $("#header").load(location.web.staticpath +'/blog/menupage/upmenu.html');
}
function showMediaPage(mediaType) {
    if (!mediaType) {
        return;
    }
    var target = '/blog/mediatype/' + mediaType + 'home.html';
    $("#content").load(location.web.staticpath + target);
    $("#left_side").load(location.web.staticpath +"/blog/mediatype/mediamenu/"+mediaType+"menu.html");
    $("#header").load(location.web.staticpath +'/blog/menupage/upmenu.html');
}