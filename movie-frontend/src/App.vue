<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">榜单</router-link>
    </div>
    <keep-alive exclude="detail">
      <router-view/>
    </keep-alive>
  </div>
</template>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #404040;
}
#nav {
  padding: 30px;
}

a {
  color: #404040;
  text-decoration: none;
}

p a:hover {
  color: gray;
  text-decoration: underline;
}

#nav a.router-link-exact-active {
  color: #409eff;
}

::selection {
  background: #404040;
  color: whitesmoke;
}
</style>
