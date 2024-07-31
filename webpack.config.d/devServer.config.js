// dev server
config.devServer = {
  port: 3000,
  historyApiFallback: true,
  open: true,
  static: [
    "kotlin"
  ],
  client: {
    overlay: {
      errors: true,
      warnings: false
    }
  }
};