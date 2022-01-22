function refreshContent(choose) {
    if (!choose) {
        return;
    }
    var target = '/blog/' + choose + '/' + choose + '.html';
    $("#content").load(location.web.staticpath + target);
}

function refreshSpringCloudContent(choose) {
    if (!choose) {
        return;
    }
    var target = '/blog/springcloud/' + choose + '/' + choose + '.html';
    $("#content").load(location.web.staticpath + target);
}

function refreshSpringContent(choose) {
    if (!choose) {
        return;
    }
    var target = '/blog/spring/' + choose + '/' + choose + '.html';
    $("#content").load(location.web.staticpath + target);
}

function refreshMQContent(choose) {
    if (!choose) {
        return;
    }
    var target = '/blog/messagemq/' + choose + '/' + choose + '.html';
    $("#content").load(location.web.staticpath + target);
}

function refreshRedisContent(choose) {
    if (!choose) {
        return;
    }
    var target = '/blog/redis/' + choose + '/' + choose + '.html';
    $("#content").load(location.web.staticpath + target);
}

function refreshServiceContent(choose) {
    if (!choose) {
        return;
    }
    var target = '/blog/service/' + choose + '/' + choose + '.html';
    $("#content").load(location.web.staticpath + target);
}
function refreshDesignContent(choose) {
    if (!choose) {
        return;
    }
    var target = '/blog/itlearn/softdesign/' + choose + '/' + choose + '.html';
    $("#content").load(location.web.staticpath + target);
}
function refreshMediaContent(mediaType) {
    if (!mediaType) {
        return;
    }
    var target = '/blog/mediatype/' + mediaType + '.html';
    $("#content").load(location.web.staticpath + target);
}