const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const devMode = process.env.NODE_ENV !== "production";

config.module.rules.push(
        {
                resourceQuery: /raw/,
                type: 'asset/source',
        },
        {
                test: /\.ya?ml$/,
                use: 'yaml-loader',
        },
        {
                test: /\.(sa|sc|c)ss$/i,
                use: [
                        MiniCssExtractPlugin.loader,
                        'css-loader',
                        'sass-loader',
                ],
        },
);

config.plugins.push(new MiniCssExtractPlugin());
