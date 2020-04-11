function refreshContent(choose) {
debugger;
    if (!choose) {
        return;
    }
    var target = '/blog/' + choose + '/' + choose + '.html';
    $("#content").load(location.web.staticpath + target);
}

function refreshMediaContent(mediaType) {
    if (!mediaType) {
        return;
    }
    var target = '/blog/mediatype/' + mediaType + '.html';
    $("#content").load(location.web.staticpath + target);
}