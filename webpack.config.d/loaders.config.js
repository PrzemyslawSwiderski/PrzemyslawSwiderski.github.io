config.module.rules.push(
        {
                resourceQuery: /raw/,
                type: 'asset/source',
        },
        {
                test: /\.ya?ml$/,
                use: 'yaml-loader'
        },
);
