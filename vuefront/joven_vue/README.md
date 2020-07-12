# joven_vue

> A Vue.js project

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# run e2e tests
npm run e2e

# run all tests
npm test
```

For a detailed explanation on how things work, 
check out the [guide](http://vuejs-templates.github.io/webpack/) 
and [docs for vue-loader](http://vuejs.github.io/vue-loader).

#添加iView框架
简介：

    iView 框架是一套基于Vue .js 的开源UI 组件库，主要服务于PC 界面的中后台产品
        iView 框架特性：
        〉高质量、功能丰富
        〉友好的API ，自由灵活地使用空间
        〉细致、漂亮的UI
        〉事无巨细的文档
        〉可自定义主题
iView 框架安装
    cnpm install iview --save
    
    一save 与一save-dev 的区别
    一save 会把依赖包添加到package.json 文件dependencies 下
    一save-dev 会把依赖包添加到package.json 文件devDependencies 下。
    dependencies 是产品上线运行时的依赖， devDependencies 是产品开发时的依
    赖。devDependencies 下的模块是产品开发时用的，比如安装js 的压缩包gu lp-uglify
    时，采用“ cnpm install gulp-uglify …save-dev ”命令安装，因为在发布后就用不到
    这个插件包了，只是在开发中才用到它。dependencies 下的模块是产品发布后还需
    要依赖的棋块，比如router 插件库或者Vue 框架等，在产品开发完成后肯定还要依
    赖它们，否则就无法运行项目。
    
    执行以上安装命令之后，项目中已经安装了iView 框架。在项目中引入iView 框架，
    官方提供了两种方式， 一种是一次性将全部组件引入到项目中，这种方式短平快，可以
    很方便地解决问题，但是项目中不可能把UI 框架中的所有组件都用到，所以这种引入
    方式会造成文件体积过大、冗余代码过多等问题，但是使用起来相当简单，可以在项目的
    任意页面直接使用。具体引入方式一般是在Webpack 的入口页面main.j s 中做如下配置：
    import Vue from 'vue’;
    import VueRouter from 'vue-router';
    import App from ’components/app.vue';
    import Routers from './router.js’,
    import iView from ’iview’； ／／引入iView 框架
    import ’iview/dist/styles/iview.css’；／／引入iView 框架样式
    Vue. use(VueRouter );
    Vue.use(iView);
    // The routing configuration
    const RouterConfig = {
        routes: Routers
    };
    const router= new VueRouter(RouterConfig);
    new Vue({
        el: ’#app’,
        router: router,
        render: h => h(App)
    } );
    
    另外一种方式是按需引入组件， 也就是项目中需要什么组件，就引入什么组件，实
    现按需加载，减少文件体积。接下来看一下按需引入的加载方式：
    首先要安装一个插件：
    npm install babel-plugin” import --save-dev
    
    babel-plugin-import 插件可以从纽件库中引入需要的模块， 而不是把整个库都引入， 从而提高性能。
    
    然后在. babelrc 文件中添加代码：
    {
        ” plugins": [[”import" , {
            ” library Name”:”1view ”,
            ” library Directory 叭” src/components”
        } ]]
    }
    配置完成之后，下面便是具体的使用：
    import { Button, Table } f云·om ’iview ’ ;
    Vue.compor nt（’ Button叭Bu 忧on ）；’
    Vue.component(’Table’, Table);
    采用这种引入方式，如果项目规模比较大，这样去引用组件将非常烦琐，使用起来
    也比较难于管理。
