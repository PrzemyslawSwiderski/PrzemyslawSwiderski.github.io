const MiniCssExtractPlugin = require("mini-css-extract-plugin");

config.plugins.push(new MiniCssExtractPlugin({
  chunkFilename: "main.css",
}));

config.module.rules.push(
  {
    resourceQuery: /raw/,
    type: "asset/source",
  },
  {
    test: /\.ya?ml$/,
    use: "yaml-loader",
  },
  {
    test: /\.(scss|sass)$/,
    use: [
      MiniCssExtractPlugin.loader,
      "css-loader",
      {
        loader: 'sass-loader',
        options: {
          sassOptions: {
            silenceDeprecations: ['mixed-decls', 'color-functions', 'global-builtin', 'import'],
          }
        }
      }
    ],
    include: [require("path").resolve(__dirname, "kotlin/scss/styles.scss")],
  },
);
