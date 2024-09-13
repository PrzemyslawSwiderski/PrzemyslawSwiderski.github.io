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
                        "sass-loader",
                ],
                include: [require("path").resolve(__dirname, "kotlin/scss/styles.scss")],
        },
);
