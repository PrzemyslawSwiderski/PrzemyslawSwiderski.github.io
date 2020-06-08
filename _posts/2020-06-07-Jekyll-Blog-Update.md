---
layout: post
title: Jekyll Blog Update
date: 2020-06-07
comments: true
excerpt_separator: "<!--more-->"

---
Bootstrapping Blog by a new version of Jekyll (4.1.0).

<!--more-->
Since 2017 year there were some changes in [Jekyll](https://jekyllrb.com/) ecosystem.

Currently, Ruby can be installed on Windows system by [Chocolatey](https://chocolatey.org/) package manager.

This way Ruby can be installed by simply executing `choco install ruby`. 

Chocolatey installer will also add `bundle` executable which is needed to manage Ruby packages.
Now we can install `Jekyll` by the following command:
`bundle install jekyll`.
After successfully installing `Jekyll`, we can bootstrap new blog by: `jekyll new my-blog` which will have simple structure and default configuration.
Blog will be using new [`minima`](https://github.com/jekyll/minima) theme in `2.5.1` version.

After bootstrapping new blog, we should configure it by changing necessary properties in `_config.yml` file.
We should change page title or email.

To run blog on our development machine, run:
`bundle install` to install dependencies and `bundle exec jekyll serve` to run blog in watch mode.
New changes in posts will be automatically applied.
Note that to apply changes in `_config.yml` you must restart command, because this file isn't watched.

My personal blog's code is available by following this [link](https://github.com/PrzemyslawSwiderski/PrzemyslawSwiderski.github.io).


In conclusion, `Jekyll` blogging framework is easy to set up and free solution which can be deployed on private Github page.


Additionally, there is a nice web [editor](https://app.forestry.io/) which allows writing posts without need to run development environment.
    