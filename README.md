# Movie Web

## Project Introduction

[Course Assignment] 电影榜单页及详情页 Movie Rank List And Detail Page | Web Technology 2019 Spring

[已部署到github pages上](https://sarahwutx.github.io/MovieWeb/)

### 运行环境

前端：部署在github pages上

后端：部署在服务器上，java语言编写，使用spring boot框架

数据库：安装在服务器上，使用mongodb数据库

### 实现功能

- 解析json文件并展示电影列表，列表中展示电影海报图片
  - 其中，部分图片无法展示，是由于json文件中的图片url本身有问题
  - 点击"查看详情"按钮可查看电影详情
- 分页功能
  - 显示总页数、总条目数、能够输入页数进行跳转
- 电影详情页
  - 尽量展示了json文件中的所有内容
  - 点击"榜单"回到首页
- 搜索功能
  - 能够电影名、电影简介中出现关键字
  - 回车键或搜索按钮都可以
  - 搜索后仍然能分页
  - 空关键字搜索回到首页状态



## Project setup

```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Run your tests
```
npm run test
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).



