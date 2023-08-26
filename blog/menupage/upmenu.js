function showPersonPage(choose) {
    if (!choose) {
        return;
    }
    if ("skillstack" === choose) {
        $("#left_side").load(location.pathname + 'blog/menupage/leftmenu.html');
        $("#right_side").load(location.pathname + 'blog/menupage/rightmenu.html');
    } else {
        $("#left_side").load(location.pathname + "public/html/blank.html");
        $("#right_side").load(location.pathname + "public/html/blank.html");
    }
    var target = 'personinfos/' + choose + '/' + choose + '.html';
    $("#content").load(location.pathname + target);
    $("#header").load(location.pathname + 'blog/menupage/upmenu.html');
}

function load(target) {
    $("#content").load(location.pathname + target);
    $("#left_side").load(location.pathname + "public/html/blank.html");
    $("#right_side").load(location.pathname + "public/html/blank.html");
    $("#header").load(location.pathname + 'blog/menupage/upmenu.html');
}

function showPicturePage(choose) {
    if (!choose) {
        return;
    }
    var target = 'picturepage/' + choose + '/' + choose + '.html';
    load(target);
}

function showMediaPage(mediaType) {
    if (!mediaType) {
        return;
    }
    var target = 'blog/mediatype/' + mediaType + 'home.html';
    $("#content").load(location.pathname + target);
    $("#left_side").load(location.pathname + "blog/mediatype/mediamenu/" + mediaType + "menu.html");
    $("#right_side").load(location.pathname + "public/html/blank.html");
    $("#header").load(location.pathname + 'blog/menupage/upmenu.html');
}

function showMeToolsPage(toolName) {
    if (!toolName) {
        return;
    }
    var target = 'blog/tools/' + toolName + '.html';
    load(target);
}

function showITLearnPage(learnName) {
    if (!learnName) {
        return;
    }
    var target = 'blog/itlearn/' + learnName + '/' + learnName + '.html';

    load(target);
}

function showMemoryPage(memory) {
    if (!memory) {
        return;
    }
    var target = 'memory/' + memory + '/' + memory + '.html';
    load(target);
}