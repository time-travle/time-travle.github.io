function refreshContent(choose) {
    if (!choose) {
        return;
    }
    var target = 'blog/' + choose + '/' + choose + '.html';
    $("#content").load(location.pathname + target);
}

function refreshMediaContent(mediaType) {
    if (!mediaType) {
        return;
    }
    var target = 'blog/mediatype/' + mediaType + '.html';
    $("#content").load(location.pathname + target);
}