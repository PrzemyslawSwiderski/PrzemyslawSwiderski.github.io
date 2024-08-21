---
title: "How this blog was created"
created: 2024-08-13
---

## Introduction

Initially my personal blog page was using the [Jekyll](https://jekyllrb.com/) static site generator.
It was very well-supported by GitHub at that time (Jan 28, 2016).

Lately I decided to refresh it. I reinstalled my local system, so I did not have Jekyll Gem package installed anymore.
So because of my laziness, I created a whole new site generation engine in Kotlin 😅.
Other reason was simply that I am more familiar with Kotlin than Ruby and wanted to try something new especially
since there was the new [`2.0` Kotlin](https://kotlinlang.org/docs/whatsnew20.html) version released 😉.

To keep it simple, I wanted the new generation engine to work in a similar way as Jekyll.
It is processing the Markdown files and generates HTML code based on those.

In this post I would like to show some technical details of how it was done 🙂.

## Building with Gradle

[Gradle](https://gradle.org/) is the build system [widely used](https://www.jetbrains.com/lp/devecosystem-2023/java/) in
many JVM projects.
Since it is supporting writing build scripts with the Kotlin DSL and has got out off the box Kotlin plugins
it was pretty clear to use it for the Kotlin project.

What I like the most is that only the Java RE is required to run the build.
It can be invoked by simply calling:

`./gradlew build`

script in the project directory.
