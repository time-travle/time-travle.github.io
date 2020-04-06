function refreshContent(choose) {
debugger;
    if (!choose) {
        return;
    }
    var target = '/blog/' + choose + '/' + choose + '.html';
    $("#content").load(location.web.staticpath + target);
}

