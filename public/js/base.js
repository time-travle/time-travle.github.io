window.onload = function () {
    // 顶部导航菜单
    $("#header").load(location.pathname+ 'blog/menupage/upmenu.html');
    $("#container").load(location.pathname + 'blog/contentpage/container.html');
    // 加载页脚
    $("#foot").load(location.pathname + "public/html/footer.html");
}

function backToHomePage() {
    $("#header").load(location.pathname + 'blog/menupage/upmenu.html');
    $("#container").load(location.pathname + 'blog/contentpage/container.html');
    $("#content").load(location.pathname + "public/html/blank.html");
}

function backToFirstPage() {
    location.href = location.pathname + 'index.html';
}

