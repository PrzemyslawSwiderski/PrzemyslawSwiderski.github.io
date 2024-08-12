const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const devMode = process.env.NODE_ENV !== "production";

config.plugins.push(new MiniCssExtractPlugin());

//config.entry.main.push(require('path').resolve(__dirname, "kotlin\\scss\\styles.scss"));

config.module.rules.push(
        {
                resourceQuery: /raw/,
                type: 'asset/source',
        },
        {
                test: /\.ya?ml$/,
                use: 'yaml-loader',
        },
//        {
//                test: /\.(sa|sc|c)ss$/,
//                use: [
//                        MiniCssExtractPlugin.loader,
//                        "css-loader",
//                        "postcss-loader",
//                        "sass-loader",
//                ],
//        },
);
