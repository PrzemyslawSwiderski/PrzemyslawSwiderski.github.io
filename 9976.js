"use strict";(this.webpackChunkpswidersk_page=this.webpackChunkpswidersk_page||[]).push([[9976],{9976:e=>{e.exports="<!DOCTYPE html>\n<html>\n<head>\n    <meta charset=\"utf-8\">\n    <title>Single Page Apps for GitHub Pages</title>\n    <script type=\"text/javascript\">\n        // Single Page Apps for GitHub Pages\n        // MIT License\n        // https://github.com/rafgraph/spa-github-pages\n        // This script takes the current url and converts the path and query\n        // string into just a query string, and then redirects the browser\n        // to the new url with only a query string and hash fragment,\n        // e.g. https://www.foo.tld/one/two?a=b&c=d#qwe, becomes\n        // https://www.foo.tld/?/one/two&a=b~and~c=d#qwe\n        // Note: this 404.html file must be at least 512 bytes for it to work\n        // with Internet Explorer (it is currently > 512 bytes)\n\n        // If you're creating a Project Pages site and NOT using a custom domain,\n        // then set pathSegmentsToKeep to 1 (enterprise users may need to set it to > 1).\n        // This way the code will only replace the route part of the path, and not\n        // the real directory in which the app resides, for example:\n        // https://username.github.io/repo-name/one/two?a=b&c=d#qwe becomes\n        // https://username.github.io/repo-name/?/one/two&a=b~and~c=d#qwe\n        // Otherwise, leave pathSegmentsToKeep as 0.\n        var pathSegmentsToKeep = 0;\n\n        var l = window.location;\n        l.replace(\n          l.protocol + '//' + l.hostname + (l.port ? ':' + l.port : '') +\n          l.pathname.split('/').slice(0, 1 + pathSegmentsToKeep).join('/') + '/?/' +\n          l.pathname.slice(1).split('/').slice(pathSegmentsToKeep).join('/').replace(/&/g, '~and~') +\n          (l.search ? '&' + l.search.slice(1).replace(/&/g, '~and~') : '') +\n          l.hash\n        );\n\n    <\/script>\n</head>\n<body>\n</body>\n</html>\n"}}]);