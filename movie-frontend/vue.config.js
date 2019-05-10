module.exports = {
  publicPath: process.env.NODE_ENV === "production" ? "/MovieWeb/" : "/",
  devServer: {
    port: 8081,
    proxy: {
      "/": {
        port: 8081,
        target: "http://122.152.221.44:8888",
        // ws: true,
        changeOrigin: true
        // secure: false
      }
    }
  }
};
