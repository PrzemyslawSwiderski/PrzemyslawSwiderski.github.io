---
layout: post
title: "How I built this blog using Jekyll and white-paper template"
date: 2017-08-28
comments: true
category: articles
excerpt_separator: <!--more-->
---

In this article I would like to describe how did I bootstrapped this page.

<!--more-->
Firstly, I had to install Ubuntu virtual machine, because Linux is being better supported by Jekyll.
Installing Ubuntu with quest additions, tools and shared folder took quite a bit of time.
Whole setup looks like this:

- Windows as host system with Intelij IDE and shared folder with blog repo
- Ubuntu guest on VirtualBox with Jekyll and other necessary tools mentioned on Jekyll's [site](https://jekyllrb.com/docs/installation/)

Jekyll installation was done by following quick-start guide posted on Jekyll's documentation [site](https://jekyllrb.com/docs/quickstart/). However, instead of using bundle tool to strat preview server, I used Jekyll yum package.
Using directly Jekyll package lets you build server with `jekyll b` or start a server with `jekyll serve` shell command without the need of using bundle package.

Big advantage of using jekyll as blogging framework is that posts can be wrriten by using Markdown markup language. This allows to write, edit or delete posts directly from Github page:


![Github edit post page](/assets/2017-08-28-Jekyll-White-Paper-Template/githubEdit.PNG?raw=true "Github post editing page")


Moreover Github provides possibility to preview changes in "Preview changes" tab.


![Github preview changes](/assets/2017-08-28-Jekyll-White-Paper-Template/previewChanges.PNG?raw=true "Github preview changes")


Summarizing, using Github pages can be very helpful to host profile's blog page. Developers can start blog by using one of Jekyll's  open source templates and build page with a few shell commands. Managing blog is also easy because of Jekyll framework which is supporting Markdown language. Any changes done by blogger, can be easily added to page by commiting changes to profile page's repository. 


As mentioned before, this blog was made by using white-paper template, cloned
from [repository](https://github.com/vinitkumar/white-paper).
