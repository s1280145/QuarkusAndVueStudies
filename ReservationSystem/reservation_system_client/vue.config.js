const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '^/reservations': {
        target: "http://127.0.0.1:3000",
        ws: true,
        changeOrigin: true
      }
    }
  }
})