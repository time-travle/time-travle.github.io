function showPersonPage(choose) {
    if (!choose) {
        return;
    }
    var target = '/personinfos/' + choose + '/' + choose + '.html';
    $("#content").load(location.web.staticpath + target);
    $("#left_side").load(location.web.staticpath +"/public/html/blank.html");
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