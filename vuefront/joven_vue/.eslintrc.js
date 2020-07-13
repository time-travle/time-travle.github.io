// https://eslint.org/docs/user-guide/configuring

module.exports = {
  root:true,
  parserOptions:{
    // parser指定解析器，默认的为espree。babel-eslint是一个Babel parser的包装器，这个包装器使得 Babel parser 可以和 ESLint 协调工作
    parser:'babel-eslint'
  },
  env:{
    browser:true,
  },
  // 使用eslint推荐的规则作为基础配置，可以在rules中覆盖
  extends:[
    // https://github.com/vuejs/eslint-plugin-vue#priority-a-essential-error-prevention
    // consider switching to `plugin:vue/strongly-recommended` or `plugin:vue/recommended` for stricter rules.
    'plugin:vue/essential',
    // https://github.com/standard/standard/blob/master/docs/RULES-en.md
    'standard'
  ],
  // required to lint *.vue files
  // vue是eslint-plugin-vue的简写，此插件的作用是可以让eslint识别.vue中的script代码
  plugins:[
    'vue'
  ],
  // add your custom rules here
  // 0或者off表示规则关闭，出错也被忽略；1或者warn表示如果出错会给出警告(不会导致程序退出)；2或者error表示如果出错会报出错误(会导致程序退出，退出码是1)
  rules:{
    // allow async-await
    'generator-star-spacing':'off',
    // allow debugger during development -- 禁用 debugger
    'no-debugger':process.env.NODE_ENV === 'production' ? 'error' : 'off',
    // 不允许空格和 tab 混合缩进
    "no-mixed-spaces-and-tabs":2,
    // 禁止 function 标识符和括号之间出现空格
    "no-spaced-func":2,
    // 强制在对象字面量的属性中键和值之间使用一致的间距
    "key-spacing":[2, {
      "beforeColon":false, // 不允许在对象文字中的键和冒号之间使用空格
      "afterColon":false // 禁止冒号和对象字面值之间的空格
    }],
    // 不允许多个空行
    "no-multiple-empty-lines":[2, {"max":2}],
    // 强制在块之前使用一致的空格
    "space-before-blocks":["error", {"functions":"never", "keywords":"never", "classes":"always"}],
    // off:禁用空格缩进规则
    "indent":[0, 2],
    // 多行模式必须带逗号，单行模式不能带逗号
    "comma-dangle":[1, "only-multiline"],
    // 强制在 function的左括号之前使用一致的空格
    "space-before-function-paren":["error", {
      "anonymous":"never", // 用于匿名函数
      "named":"never",  // 用于命名函数
      "asyncArrow":"never" // 用于异步箭头函数
    }],

  }
}
